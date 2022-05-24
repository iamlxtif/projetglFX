package database;

//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.*;
public class MyConnection {
    public static Connection getConnection(){

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/projet_gl", "root", "1234");
        } catch (Exception ex) {
            System.out.println(ex.getMessage()+ "problem");
        }

        return con;
    }

}

