package users;

import produits.Catalogue;
import produits.ProduitLaitier;

import java.sql.Connection;
import java.sql.SQLException;

public class AgentCommerciale extends Utilisateur {
    public AgentCommerciale(String nomUtilisateur, String motDePasse) {
        super(nomUtilisateur, motDePasse);
    }

    public void attribuerPrix ( Catalogue catalogue, int reference, double coutRevien, double gain ) {
        for ( ProduitLaitier produitLaitier : catalogue.getList() ) {
            if ( produitLaitier.getReference() == reference ) {
                produitLaitier.setCoutRevien( coutRevien );
                produitLaitier.setGain( gain );
            }
        }
    }
    public void modifierProduit ( Connection con, ProduitLaitier produit, double coutRevien, double gain) throws SQLException {
        produit.setCoutRevien( coutRevien );
        produit.setGain( gain );
        produit.updateProduct( con );
    }
}
