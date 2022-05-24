package documents;

import produits.ProduitLaitier;

public class ProduitQuantite {
    private ProduitLaitier produit;
    private int quantite;

    public ProduitLaitier getProduit () {
        return produit;
    }

    public int getQuantite () {
        return quantite;
    }

    public ProduitQuantite ( ProduitLaitier produit, int quantite ) {
        this.produit = produit;
        this.quantite = quantite;
    }
}
