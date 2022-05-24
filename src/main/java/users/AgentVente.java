package users;

import documents.Commande;
import documents.Historique;
import documents.ProduitQuantite;
import produits.Catalogue;
import produits.DeriveLait;
import produits.ProduitLaitier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class AgentVente extends AgentCommerciale{
    public AgentVente(String nomUtilisateur, String motDePasse ) {
        super(nomUtilisateur, motDePasse);
    }

    public void passerCommande( Client client, ArrayList<ProduitQuantite> produits ){
        Commande commande = new Commande( produits, client);
    }
    public void enregistreDonneeClient (Connection con, String designation, String adresseCl, String emailCl, Boolean isRevendeur) throws Exception {
        Client client = new Client(designation, adresseCl, emailCl, isRevendeur);
        client.insertClient( con );

    }

    public void majDonneClient (Connection con, Client client, String designation, String adresseCl, String emailCl, Boolean isRevendeur) throws Exception {
        client.setDesignationCl( designation );
        client.setAdresseCl( adresseCl );
        client.setEmailCl( emailCl );
        client.setIsRevendeur( isRevendeur );
        client.updateClient( con );
    }
    public void archiverHistorique( Connection con, Catalogue catalogue ) throws Exception {
        Historique.createHistorique(con , catalogue);
    }
    public void ajouterProduit( Connection con, ProduitLaitier produit, Catalogue catalogue ) throws SQLException {
        catalogue.getList().add( produit );
        produit.insertProduct( con );
    }
    public void modifierProduit ( Connection con, ProduitLaitier produit, String designation, double coutRevien, double gain, int valeurNutrition, Date dateProduction, Date datePeremption, int poidNet) throws SQLException {
        produit.setDesignation( designation );
        produit.setCoutRevien( coutRevien );
        produit.setGain( gain );
        produit.setValuerNutrition( valeurNutrition );
        produit.setDateProduction( dateProduction );
        produit.setDatePeremption( datePeremption );
        produit.updateProduct( con );
    }
    public void modifierProduit ( Connection con, DeriveLait produit, String designation, double coutRevien, double gain, int valeurNutrition, Date dateProduction, Date datePeremption, int poidNet, String ingredients) throws SQLException {
        produit.setDesignation( designation );
        produit.setCoutRevien( coutRevien );
        produit.setGain( gain );
        produit.setValuerNutrition( valeurNutrition );
        produit.setDateProduction( dateProduction );
        produit.setDatePeremption( datePeremption );
        produit.setIngredients( ingredients );
        produit.updateProduct( con );
    }
    public void supprimerProudit(Connection con, int reference, Catalogue catalogue) throws SQLException {
        for ( ProduitLaitier produitLaitier : catalogue.getList() ) {
            if ( produitLaitier.getReference() == reference ) {
                catalogue.getList().remove( produitLaitier );
                produitLaitier.deleteProduct( con );
        }
    }

    }
    public ProduitLaitier rechercher(int reference, Catalogue catalogue) {
        for ( ProduitLaitier produitLaitier : catalogue.getList() ) {
            if ( produitLaitier.getReference() == reference ) {
                System.out.println( produitLaitier );
                return produitLaitier;
            }
        }
        return null;
    }
    public ProduitLaitier rechercher(String desingation, Catalogue catalogue) {
        for ( ProduitLaitier produitLaitier : catalogue.getList() ) {
            if ( produitLaitier.getDesignation().equals( desingation ) ) {
                System.out.println( produitLaitier);
                return produitLaitier;
            }
        }
        return null;
    }
}

