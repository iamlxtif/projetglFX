package documents;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class SauvegardeMonsuel {
    private double CA;
    private Date date;

    public double getCA() {
        return CA;
    }

    public Date getDate() {
        return date;
    }

    public SauvegardeMonsuel(Connection con) throws SQLException {
        calculerCA();
        sauvegarder( con );
    }

    public void calculerCA(){
        double CA = 0;
        for ( Commande commande : Historique.getCommandesLastMonth() ) {
            CA += commande.getTotal();
        }
        this.CA = CA;
    }
//    save sauvegardeMonsuel to database table sauvegarde_monsuel
    public void sauvegarder( Connection con ) throws SQLException {
        String query = "INSERT INTO sauvegarde_monsuel (CA, date) VALUES (?, ?)";
        java.sql.PreparedStatement stmt = con.prepareStatement(query);
        stmt.setDouble(1, this.CA);
        stmt.setDate(2, new java.sql.Date(this.date.getTime()));
        stmt.executeUpdate();
    }
}
