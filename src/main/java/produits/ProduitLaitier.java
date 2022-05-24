package produits;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public abstract class ProduitLaitier {
    private int reference;
    private String designation;
    private double coutRevien;
    private double gain;
    private int valuerNutrition;
    private Date dateProduction;
    private Date datePeremption;
    private int poidNet;

    public int getReference() {
        return reference;
    }

    public String getDesignation() {
        return designation;
    }

    public double getCoutRevien() {
        return coutRevien;
    }

    public double getGain() {
        return gain;
    }

    public int getValuerNutrition() {
        return valuerNutrition;
    }

    public Date getDateProduction() {
        return dateProduction;
    }

    public Date getDatePeremption() {
        return datePeremption;
    }

    public int getPoidNet() {
        return poidNet;
    }

    public void setCoutRevien ( double coutRevien ) {
        this.coutRevien = coutRevien;
    }

    public void setGain ( double gain ) {
        this.gain = gain;
    }

    public void setDesignation ( String designation ) {
        this.designation = designation;
    }

    public void setValuerNutrition ( int valuerNutrition ) {
        this.valuerNutrition = valuerNutrition;
    }

    public void setDateProduction ( Date dateProduction ) {
        this.dateProduction = dateProduction;
    }

    public void setDatePeremption ( Date datePeremption ) {
        this.datePeremption = datePeremption;
    }

    public void setPoidNet ( int poidNet ) {
        this.poidNet = poidNet;
    }

    public ProduitLaitier ( int reference, String designation, int valuerNutrition, Date dateProduction, Date datePeremption, int poidNet ) {
        this.reference = reference;
        this.designation = designation;
        this.valuerNutrition = valuerNutrition;
        this.dateProduction = dateProduction;
        this.datePeremption = datePeremption;
        this.poidNet = poidNet;
    }

    public ProduitLaitier ( String designation, double coutRevien, double gain, int valuerNutrition, Date dateProduction, Date datePeremption, int poidNet ) {
        this.designation     = designation;
        this.coutRevien      = coutRevien;
        this.gain            = gain;
        this.valuerNutrition = valuerNutrition;
        this.dateProduction  = dateProduction;
        this.datePeremption  = datePeremption;
        this.poidNet         = poidNet;
    }

    public ProduitLaitier( int reference, String designation, double coutRevien, double gain, int valuerNutrition, Date dateProduction, Date datePeremption, int poidNet){
        this.reference=reference;
        this.designation=designation;
        this.coutRevien=coutRevien;
        this.gain=gain;
        this.valuerNutrition=valuerNutrition;
        this.dateProduction=dateProduction;
        this.datePeremption=datePeremption;
        this.poidNet=poidNet;
    }
    public abstract void insertProduct (  Connection con ) throws SQLException;

    //    delete product from database
    public void deleteProduct (  Connection con ) throws SQLException {
        String sql = "Delete from catalogue where reference = ?;";
        PreparedStatement stmt = con.prepareStatement( sql );
        stmt.setInt( 1, this.getReference() );
        stmt.executeUpdate();
    }
    //update product in database
    public abstract void updateProduct ( Connection con ) throws SQLException;

    public double getPrix () {
        return this.getCoutRevien() + this.getGain();
    }


}
