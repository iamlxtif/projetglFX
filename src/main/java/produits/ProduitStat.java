package produits;

public class ProduitStat {
    private ProduitLaitier produit;
    private int quantiteTotal;
    private double gainTotal;
    private double coutRevienTotal;

    public ProduitLaitier getProduit() {
        return produit;
    }

    public void setQuantiteTotal ( int quantiteTotal ) {
        this.quantiteTotal = quantiteTotal;
    }

    public int getQuantiteTotal() {
        return quantiteTotal;
    }

    public double getGainTotal() {
        return gainTotal;
    }

    public double getCoutRevienTotal() {
        return coutRevienTotal;
    }

    public double getCA() {
        return CA;
    }

    public ProduitStat ( ProduitLaitier produit ) {
        this.produit = produit;
    }

    private double CA;
    public ProduitStat(ProduitLaitier produit, int quantiteTotal, double gainTotal, double coutRevienTotal, double CA) {
        this.produit = produit;
        this.quantiteTotal = quantiteTotal;
        this.gainTotal = gainTotal;
        this.coutRevienTotal = coutRevienTotal;
        this.CA = CA;
    }

    public void calculerStat() {
        calculerGainTotal();
        calculerCoutRevienTotal();
        calculerCA();
    }
    private void calculerGainTotal(){
        this.gainTotal = this.quantiteTotal * this.produit.getPrix();
    }
    private void calculerCoutRevienTotal(){
        this.coutRevienTotal = this.quantiteTotal * this.produit.getCoutRevien();
    }
    private void calculerCA(){

    }
}
