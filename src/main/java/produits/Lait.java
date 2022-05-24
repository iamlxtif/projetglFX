package produits;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Lait extends ProduitLaitier{


    public Lait ( String designation, double coutRevien, double gain, int valuerNutrition, Date dateProduction, Date datePeremption, int poidNet ) {
        super( designation, coutRevien, gain, valuerNutrition, dateProduction, datePeremption, poidNet );
    }

    public Lait( int reference, String designation, double coutRevien, double gain, int valuerNutrition, Date dateProduction, Date datePeremption, int poidNet) {
        super(reference, designation, coutRevien, gain, valuerNutrition, dateProduction, datePeremption, poidNet);
    }
    // function insert Product to db
    public void insertProduct (Connection con ) throws SQLException {
        String sql = "Insert into catalogue " + "( designation, coutRevien, gain, valeurNutrition, dateProduction, DatePeremption, PoidNet, type )" + "values (?,?,?,?,?,?,?,?);";
        PreparedStatement stmt = con.prepareStatement( sql );
        stmt.setString( 1, this.getDesignation() );
        stmt.setDouble( 2, this.getCoutRevien() );
        stmt.setDouble( 3, this.getGain() );
        stmt.setInt( 4, this.getValuerNutrition() );
        stmt.setDate( 5, (java.sql.Date) this.getDateProduction() ) ;
        stmt.setDate( 6, (java.sql.Date) this.getDatePeremption() ) ;
        stmt.setInt( 7, this.getPoidNet() );
        stmt.setString( 8, this.getClass().getSimpleName() );
        stmt.executeUpdate();

    }
    public void updateProduct ( Connection con ) throws SQLException {
        String sql = "Update catalogue set designation = ?, coutRevien = ?, gain = ?, valeurNutrition = ?, dateProduction = ?, DatePeremption = ?, PoidNet = ? where reference = ?;";
        PreparedStatement stmt = con.prepareStatement( sql );
        stmt.setString( 1, this.getDesignation() );
        stmt.setDouble( 2, this.getCoutRevien() );
        stmt.setDouble( 3, this.getGain() );
        stmt.setInt( 4, this.getValuerNutrition() );
        stmt.setDate( 5, (java.sql.Date) this.getDateProduction() ) ;
        stmt.setDate( 6, (java.sql.Date) this.getDatePeremption() );
        stmt.setInt( 7, this.getPoidNet() );
        stmt.setInt( 8, this.getReference() );
        stmt.executeUpdate();
    }

    // fetch product from database by reference
    public static Lait getProduct( Connection con, int reference ) throws SQLException {
        String sql = "Select * from catalogue where reference = ?;";
        PreparedStatement stmt = con.prepareStatement( sql );
        stmt.setInt( 1, reference );
        ResultSet result = stmt.executeQuery();
        result.next();
        return new Lait( reference, result.getString( 2 ), result.getDouble( 3 ), result.getDouble( 4 ), result.getInt( 5 ), result.getDate( 6 ), result.getDate( 7 ), result.getInt( 8 ) );
    }
}





