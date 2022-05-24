package produits;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DeriveLait extends ProduitLaitier{

    private String ingredients;

    public String getIngredients () {
        return ingredients;
    }

    public void setIngredients ( String ingredients ) {
        this.ingredients = ingredients;
    }

    public DeriveLait ( String designation, double coutRevien, double gain, int valuerNutrition, Date dateProduction, Date datePeremption, int poidNet, String ingredients ) {
        super( designation, coutRevien, gain, valuerNutrition, dateProduction, datePeremption, poidNet );
        this.ingredients = ingredients;
    }

    public DeriveLait( int reference, String designation, double coutRevien, double gain, int valuerNutrition, Date dateProduction, Date datePeremption, int poidNet, String ingredients) {
        super(reference, designation, coutRevien, gain, valuerNutrition, dateProduction, datePeremption, poidNet);
        this.ingredients = ingredients;
    }

    public void insertProduct (Connection con ) throws SQLException {
        String sql = "Insert into catalogue " + "( designation, coutRevien, gain, valeurNutrition, dateProduction, DatePeremption, PoidNet, type, ingredients )" + "values (?,?,?,?,?,?,?,?,?);";
        PreparedStatement stmt = con.prepareStatement( sql );
        stmt.setString( 1, this.getDesignation() );
        stmt.setDouble( 2, this.getCoutRevien() );
        stmt.setDouble( 3, this.getGain() );
        stmt.setInt( 4, this.getValuerNutrition() );
        stmt.setDate( 5, (java.sql.Date) this.getDateProduction() ) ;
        stmt.setDate( 6, (java.sql.Date) this.getDatePeremption() ) ;
        stmt.setInt( 7, this.getPoidNet() );
        stmt.setString( 8, this.getClass().getSimpleName());
        stmt.setString( 9, this.ingredients );
        stmt.executeUpdate();

    }

    public void updateProduct ( Connection con ) throws SQLException {
        String sql = "Update catalogue set designation = ?, coutRevien = ?, gain = ?, valeurNutrition = ?, dateProduction = ?, DatePeremption = ?, PoidNet = ?, ingredients = ? where reference = ?;";
        PreparedStatement stmt = con.prepareStatement( sql );
        stmt.setString( 1, this.getDesignation() );
        stmt.setDouble( 2, this.getCoutRevien() );
        stmt.setDouble( 3, this.getGain() );
        stmt.setInt( 4, this.getValuerNutrition() );
        stmt.setDate( 5, (java.sql.Date) this.getDateProduction() ) ;
        stmt.setDate( 6, (java.sql.Date) this.getDatePeremption() );
        stmt.setInt( 7, this.getPoidNet() );
        stmt.setString( 8, this.ingredients );
        stmt.setInt( 9, this.getReference() );
        stmt.executeUpdate();
    }

    // fetch product from database by refrence
    public static DeriveLait fetchProduct ( Connection con, int reference ) throws SQLException {
        String sql = "Select * from catalogue where reference = ?;";
        PreparedStatement stmt = con.prepareStatement( sql );
        stmt.setInt( 1, reference );
        DeriveLait deriveLait = null;
        ResultSet rs = stmt.executeQuery();
        if ( rs.next() ) {
            deriveLait = new DeriveLait( rs.getInt( "reference" ), rs.getString( "designation" ), rs.getDouble( "coutRevien" ), rs.getDouble( "gain" ), rs.getInt( "valeurNutrition" ), rs.getDate( "dateProduction" ), rs.getDate( "DatePeremption" ), rs.getInt( "PoidNet" ), rs.getString( "ingredients" ) );
        }
        return deriveLait;
    }
}
