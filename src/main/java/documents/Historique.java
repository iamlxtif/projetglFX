package documents;

import produits.Catalogue;
import produits.ProduitLaitier;
import users.Client;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Historique {
    private static ArrayList<Commande> commandes;

    public static ArrayList<Commande> getCommandes() {
        return commandes;
    }
//    get commands from last month from commandes arraylist
    public static ArrayList<Commande> getCommandesLastMonth() {
        ArrayList<Commande> commandesLastMonth = new ArrayList<>();
        for (Commande commande : commandes) {
            if (commande.getDate().getMonth() == java.util.Calendar.getInstance().get(java.util.Calendar.MONTH)) {
                commandesLastMonth.add(commande);
            }
        }
        return commandesLastMonth;
    }

    public Historique(ArrayList<Commande> commandes) {
        this.commandes = commandes;
    }

    // get historique from database
    public static void createHistorique( Connection con, Catalogue catalogue ) throws Exception {
        String sql = "SELECT * FROM commandes order by date desc ";
        PreparedStatement stmt = con.prepareStatement(sql);
        commandes = new ArrayList<>();
        ResultSet result = stmt.executeQuery();
        while (result.next()) {
            int codeCMD = result.getInt("codeCMD");
            Date date = result.getDate("date");
            int client = result.getInt("client");
            String productQuery = "SELECT * FROM produitsCMD WHERE codeCMD = ?";
            PreparedStatement productStmt = con.prepareStatement(productQuery);
            ResultSet productResult = productStmt.executeQuery();
            ArrayList<ProduitQuantite> produits = new ArrayList<>();
            while (productResult.next()) {
                for(ProduitLaitier p : catalogue.getList()) {
                    if(p.getReference() == productResult.getInt("reference")) {
                        produits.add(new ProduitQuantite(p, productResult.getInt("quantite")));
                        break;
                    }
                }
            }
            Client.getAllClients(con) ;
            Client clientObj = null;
            for(Client c : Client.listeClient) {
                if(c.getNumeroCl() == client) {
                    clientObj = c;
                }
            }
            commandes.add(new Commande(produits, clientObj, date, codeCMD));
        }
    }
    }

