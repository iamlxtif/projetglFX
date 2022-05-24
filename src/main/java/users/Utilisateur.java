package users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Utilisateur {
    private String nomUtilisateur;
    private String motDePasse;
    private String fonction;

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getFonction() {
        return this.getClass().getSimpleName();
    }

    public Utilisateur(String nomUtilisateur, String motDePasse) {
        this.nomUtilisateur=nomUtilisateur;
        this.motDePasse=motDePasse;
        this.fonction=this.getClass().getSimpleName();
    }

    // authenticate user from database
    public static String authenticate( String nomUtilisateur, String motDePasse, Connection con ) throws SQLException {
        // fetch user from database where nomUtilisateur = nomUtilisateur
        String sql = "SELECT * FROM utilisateurs WHERE nomUtilisateur = ?";
        PreparedStatement stmt = con.prepareStatement( sql );
        stmt.setString( 1, nomUtilisateur );
        ResultSet result = stmt.executeQuery();
        if (result.next()) {
            String mdp = result.getString( "mdp" );
            if (mdp.equals( motDePasse )) {
                return result.getString( "fonction" );
            }
            return "wrong password";
        }
        return "wrong nomUtilisateur";
    }

    // create user in database
    public void create( Connection con ) throws SQLException {
        // check if user already exists
        String sql = "SELECT * FROM utilisateurs WHERE nomUtilisateur = ?";
        PreparedStatement stmt = con.prepareStatement( sql );
        stmt.setString( 1, nomUtilisateur );
        ResultSet result = stmt.executeQuery();
        if ( result.next() ) {
            return;
        }
        // insert user in database
        sql = "INSERT INTO utilisateurs (nomUtilisateur, mdp, fonction) VALUES (?, ?, ?)";
        stmt = con.prepareStatement( sql );
        stmt.setString( 1, nomUtilisateur );
        stmt.setString( 2, motDePasse );
        stmt.setString( 3, this.getClass().getSimpleName() );
        stmt.executeUpdate();
    }

    // update user in database
    public void update( Connection con ) throws SQLException {
        // update user in database
        String sql = "UPDATE utilisateurs SET mdp = ?, fonction = ? WHERE nomUtilisateur = ?";
        PreparedStatement stmt = con.prepareStatement( sql );
        stmt.setString( 1, motDePasse );
        stmt.setString( 2, this.getClass().getSimpleName() );
        stmt.setString( 3, nomUtilisateur );
        stmt.executeUpdate();
    }

    // delete user from database
    public void delete( Connection con ) throws SQLException {
        // delete user from database
        String sql = "DELETE FROM utilisateurs WHERE nomUtilisateur = ?";
        PreparedStatement stmt = con.prepareStatement( sql );
        stmt.setString( 1, nomUtilisateur );
        stmt.executeUpdate();
    }
}
