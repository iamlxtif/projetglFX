package documents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Plan {
    private double CA;
    private Date date;
    public static ArrayList<Plan> plans = new ArrayList<>();

    public double getCA() {
        return CA;
    }

    public Date getDate() {
        return date;
    }

    public void setCA ( double CA ) {
        this.CA = CA;
    }

    public Plan( double CA, Date date )  {
        this.CA = CA;
        this.date = date;
    }

    public Plan ( double CA ) {
        this.CA = CA;
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        this.date = today.getTime();
    }

    // fetch all plan from database
    public static ArrayList<Plan> fetchAllPlans( Connection con ) throws SQLException {
        String query = "SELECT * FROM plans";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            plans.add(new Plan(rs.getDouble("CA"), rs.getDate("date")));
        }
        return plans;
    }

//    insert plan to database
    public void insertPlan(Connection con) throws SQLException {
        String query = "INSERT INTO plans(CA, date) VALUES(?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setDouble(1, this.CA);
        ps.setDate( 2, (java.sql.Date) this.date );
        ps.executeUpdate();
    }
//    update plan in database
    public void updatePlan(Connection con) throws SQLException {
        String query = "UPDATE plans SET CA = ?, date = ? WHERE CA = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setDouble(1, this.CA);
        ps.setDate( 2, (java.sql.Date) this.date );
        ps.setDouble(3, this.CA);
        ps.executeUpdate();
    }
//    delete plan from database
    public void deletePlan(Connection con) throws SQLException {
        String query = "DELETE FROM plans WHERE CA = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setDouble(1, this.CA);
        ps.executeUpdate();
    }
}
