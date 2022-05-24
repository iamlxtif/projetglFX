package documents;

import produits.Catalogue;
import produits.ProduitLaitier;
import produits.ProduitStat;

import java.sql.*;
import java.util.ArrayList;

public class Bilan {
    private ArrayList<ProduitStat> produits = new ArrayList<>();

    public Bilan () {
    }

    public Bilan( Catalogue catalogue) {
        for(ProduitLaitier p : catalogue.getList()) {
            produits.add(new ProduitStat(p));
        }
    }
    public ArrayList<ProduitLaitier> lireInfo(Catalogue catalogue) {
        return catalogue.getList();
    }

    public ArrayList<ProduitStat> getProduits () {
        return produits;
    }

    //    get quantity of each product from database table produitsCMD
    public void setStat ( Connection con, Date dateDebut, Date dateFin ) throws SQLException {
        for (ProduitStat p : produits) {
            int ref = p.getProduit().getReference();
            String sql = "SELECT COUNT(*) FROM produitsCMD join commandes c on c.codeCMD = produitsCMD.codeCMD WHERE produit = ? AND c.date BETWEEN ? AND ?";
            PreparedStatement stmt = con.prepareStatement( sql );
            stmt.setInt( 1, ref );
            stmt.setDate( 2, dateDebut );
            stmt.setDate( 3, dateFin );
            ResultSet rs = stmt.executeQuery();
            rs.next();
            p.setQuantiteTotal(rs.getInt(1));
            p.calculerStat();
        }
    }
//    function that retuns setStat of last month
    public void setMonthlyStat(Connection con) throws SQLException {
        java.util.Date date = new java.util.Date();
        java.sql.Date dateDebut = new java.sql.Date(date.getYear(), date.getMonth(), 1);
        java.sql.Date dateFin = new java.sql.Date(date.getYear(), date.getMonth()+1, 1);
        setStat(con, dateDebut, dateFin);
    }
//    function that returns setStat of last year
    public void setyearlyStat(Connection con) throws SQLException {
        java.util.Date date = new java.util.Date();
        java.sql.Date dateDebut = new java.sql.Date(date.getYear(), 0, 1);
        java.sql.Date dateFin = new java.sql.Date(date.getYear(), 12, 1);
        setStat(con, dateDebut, dateFin);
    }
//    functiono that returns setStat of last quarter
    public void setQuarterlyStat(Connection con) throws SQLException {
        java.util.Date date = new java.util.Date();
        java.sql.Date dateDebut = new java.sql.Date(date.getYear(), (date.getMonth()/3)*3, 1);
        java.sql.Date dateFin = new java.sql.Date(date.getYear(), (date.getMonth()/3)*3+3, 1);
        setStat(con, dateDebut, dateFin);
    }
//    function that returns stat of all time
    public void setAllTimeStat(Connection con) throws SQLException {
        java.sql.Date dateDebut = new java.sql.Date(0, 0, 1);
        java.sql.Date dateFin = new java.sql.Date(9999, 12, 1);
        setStat(con, dateDebut, dateFin);
    }
}
