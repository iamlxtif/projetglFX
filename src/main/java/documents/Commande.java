package documents;

import users.Client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Commande {
        private ArrayList<ProduitQuantite> produits;
        private Client client;
        private Date date;
        private int codeCMD;

        public ArrayList<ProduitQuantite> getProduits() {
                return produits;
        }

        public Client getClient() {
                return client;
        }

        public Date getDate() {
                return date;
        }

        private double total;

        public double getTotal() {
                return total;
        }

        public int getCodeCMD () {
                return codeCMD;
        }

        public Commande () {
        }

        public Commande ( ArrayList<ProduitQuantite> produits, Client client ) {
                this.produits = produits;
                this.client = client;
//                this.date = today;
                Calendar today = Calendar.getInstance();
                today.set(Calendar.HOUR_OF_DAY, 0);
                this.date = today.getTime();
                double total = 0;
                for (ProduitQuantite produitQuantite : produits) {
                        total += produitQuantite.getProduit().getPrix() * produitQuantite.getQuantite();
                }
                float tva = client.getIsRevendeur() ? 0.2f : 0f;
                this.total = total + total * tva;
        }

        public Commande ( ArrayList<ProduitQuantite> produits, Client client, Date date ) {
                this.produits = produits;
                this.client = client;
                this.date = date;
                double total = 0;
                for (ProduitQuantite produitQuantite : produits) {
                        total += produitQuantite.getProduit().getPrix() * produitQuantite.getQuantite();
                }
                float tva = client.getIsRevendeur() ? 0.2f : 0f;
                this.total = total + total * tva;
        }

        public Commande( ArrayList<ProduitQuantite> produits, Client client, Date date, int codeCMD ) {
                this.produits = produits;
                this.client = client;
                this.date = date;
                double total = 0;
                for (ProduitQuantite produitQuantite : produits) {
                        total += produitQuantite.getProduit().getPrix() * produitQuantite.getQuantite();
                }
                float tva = client.getIsRevendeur() ? 0.2f : 0f;
                this.total = total + total * tva;
                this.codeCMD = codeCMD;
        }
//        create commande in database
        public void create ( Connection con ) throws SQLException {
                String sql = "INSERT INTO commandes(client, date) VALUES (?, ?)";
                java.sql.PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, client.getNumeroCl());
                statement.setDate(2, new java.sql.Date(date.getTime()));
                statement.execute();
                statement.close();
//                get commande code
                sql = "SELECT codeCMD FROM commandes WHERE client = ? AND date = ?";
                statement = con.prepareStatement(sql);
                ResultSet result = statement.executeQuery();
                result.next();
                codeCMD = result.getInt("codeCMD");
//                put produits in produitsCMD where codeCMD = codeCMD
                sql = "INSERT INTO produitsCMD(codeCMD, produit, qte) VALUES (?, ?, ?)";
                statement = con.prepareStatement(sql);
                for (ProduitQuantite produitQuantite : produits) {
                        statement.setInt(1, codeCMD);
                        statement.setInt(2, produitQuantite.getProduit().getReference());
                        statement.setInt(3, produitQuantite.getQuantite());
                }
                statement.execute();
                statement.close();
        }


}

