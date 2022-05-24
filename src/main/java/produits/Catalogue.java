package produits;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Catalogue {
    private ArrayList<ProduitLaitier> list;
    public Catalogue(ArrayList<ProduitLaitier> list){
        this.list = list;
    }

    public Catalogue () {
    }

    public ArrayList<ProduitLaitier> getList() {
        return list;
    }

    public void setList(ArrayList<ProduitLaitier> list) {
        this.list = list;
    }
    public void create ( Connection con ) throws SQLException {
        ArrayList<ProduitLaitier> produitList = new ArrayList<>();
        Statement stmt;
        ResultSet result;
        String sql;

        sql = "select * from catalogue where ingredients is null";
        result = getResultSet( con, sql );

        while ( result.next() ) {
            int reference = result.getInt( 1 );
            String designation = result.getString( 2 );
            double coutRevien = result.getDouble( 3 );
            double gain = result.getDouble( 4 );
            int valeurNutrition = result.getInt( 5 );
            java.util.Date dateProduction = result.getDate( 6 );
            java.util.Date datePeremption = result.getDate( 7 );
            int poidNet = result.getInt( 8 );

            Lait produit = new Lait( reference, designation, coutRevien, gain, valeurNutrition, dateProduction, datePeremption, poidNet );

            assert false;
            produitList.add( produit );
        }

        sql = "select * from catalogue where ingredients is not null";
        result = getResultSet( con, sql );

        while ( result.next() ) {
            int reference = result.getInt( 1 );
            String designation = result.getString( 2 );
            double coutRevien = result.getDouble( 3 );
            double gain = result.getDouble( 4 );
            int valeurNutrition = result.getInt( 5 );
            java.util.Date dateProduction = result.getDate( 6 );
            java.util.Date datePeremption = result.getDate( 7 );
            int poidNet = result.getInt( 8 );
            String ingredients = result.getString( 10 );

            DeriveLait produit = new DeriveLait( reference, designation, coutRevien, gain, valeurNutrition, dateProduction, datePeremption, poidNet, ingredients );

            assert false;
            produitList.add( produit );
        }
        //print les produits
        assert false;
        for ( ProduitLaitier produit : produitList ) {
            System.out.println( "" + produit.getReference() + " " + produit.getDesignation() + " " + produit.getClass() );
        }
        this.list = produitList;
    }


    // function that runs a query sql through connection con
    private static ResultSet getResultSet ( Connection con, String sql ) throws SQLException {
        Statement stmt;
        ResultSet result;
        stmt = con.createStatement();
        result = stmt.executeQuery( sql );
        return result;
    }

    // delete product from list
    public void delete ( int reference, Connection con ) throws SQLException {
        for ( int i = 0; i < list.size(); i++ ) {
            if ( list.get( i ).getReference() == reference ) {
                list.get(i).deleteProduct(con);
                list.remove( i );
                break;
            }
        }
    }

}
