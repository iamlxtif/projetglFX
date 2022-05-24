package users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Client {
    private int numeroCl;
    private String designationCl;
    private String adresseCl;
    private String emailCl;
    private boolean isRevendeur;
    public static ArrayList<Client> listeClient ;


    public int getNumeroCl() {
        return numeroCl;
    }

    public String getDesignationCl() {
        return designationCl;
    }

    public void setDesignationCl( String designationCl) {
        this.designationCl = designationCl;
    }

    public String getAdresseCl() {
        return adresseCl;
    }

    public String getEmailCl() {
        return emailCl;
    }
    
    public boolean getIsRevendeur() {
        return isRevendeur;
    }

    public void setIsRevendeur(boolean revendeur) {
        isRevendeur = revendeur;
    }

    public void setNumeroCl ( int numeroCl ) {
        this.numeroCl = numeroCl;
    }

    public void setAdresseCl ( String adresseCl ) {
        this.adresseCl = adresseCl;
    }

    public void setEmailCl ( String emailCl ) {
        this.emailCl = emailCl;
    }

    public Client ( String designationCl, String adresseCl, String emailCl, boolean isRevendeur ) {
        this.designationCl = designationCl;
        this.adresseCl = adresseCl;
        this.emailCl = emailCl;
        this.isRevendeur = isRevendeur;
    }

    public Client( int numeroCl, String designationCl, String adresseCl, String emailCl, boolean isRevendeur) {
        this.numeroCl = numeroCl;
        this.designationCl = designationCl;
        this.adresseCl = adresseCl;
        this.emailCl = emailCl;
        this.isRevendeur = isRevendeur;
    }
//    insert client into database
    public void insertClient( Connection con) throws Exception {
        String query = "insert into clients ( designation, adresse, email, isRevendeur) values(?,?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement( query);
        preparedStatement.setString(1, designationCl);
        preparedStatement.setString(2, adresseCl);
        preparedStatement.setString(3, emailCl);
        preparedStatement.setBoolean(4, isRevendeur);
        preparedStatement.executeUpdate();
//        get numeroCl from database and set it to this.numeroCl
        query = "select numero from clients where designation=? and adresse=? and email=? and isRevendeur=?";
        preparedStatement = con.prepareStatement( query);
        preparedStatement.setString(1, designationCl);
        preparedStatement.setString(2, adresseCl);
        preparedStatement.setString(3, emailCl);
        preparedStatement.setBoolean(4, isRevendeur);
        ResultSet resultSet = preparedStatement.executeQuery();
        if ( resultSet.next()) {
            numeroCl = resultSet.getInt(1);
        }
    }

//    update client in database
    public void updateClient( Connection con) throws Exception {
        String query = "update clients set designation=?, adresse=?, email=?, isRevendeur=? where numero=?";
        PreparedStatement preparedStatement = con.prepareStatement( query);
        preparedStatement.setString(1, designationCl);
        preparedStatement.setString(2, adresseCl);
        preparedStatement.setString(3, emailCl);
        preparedStatement.setBoolean(4, isRevendeur);
        preparedStatement.setInt(5, numeroCl);
        preparedStatement.executeUpdate();
    }

//    delete client from database
    public void deleteClient( Connection con) throws Exception {
        String query = "delete from clients where numero=?";
        PreparedStatement preparedStatement = con.prepareStatement( query);
        preparedStatement.setInt(1, numeroCl);
        preparedStatement.executeUpdate();
    }

//    get client from database
    public static Client getClient( Connection con, int numeroCl) throws Exception {
        String query = "select * from clients where numero=?";
        PreparedStatement preparedStatement = con.prepareStatement( query);
        preparedStatement.setInt(1, numeroCl);
        Client client = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            client = new Client(
                    resultSet.getInt("numero"),
                    resultSet.getString("designation"),
                    resultSet.getString("adresse"),
                    resultSet.getString("email"),
                    resultSet.getBoolean("isRevendeur")
            );
        }
        return client;
    }

//    get all clients from database
    public static ArrayList<Client> getAllClients( Connection con) throws Exception {
        String query = "select * from clients";
        PreparedStatement preparedStatement = con.prepareStatement( query);
        ArrayList<Client> clients = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Client client = new Client(
                    resultSet.getInt("numero"),
                    resultSet.getString("designation"),
                    resultSet.getString("adresse"),
                    resultSet.getString("email"),
                    resultSet.getBoolean("isRevendeur")
            );
            clients.add(client);
        }
        listeClient = clients;
        return clients;
    }


//    get all clients from database where isRevendeur
    public static ArrayList<Client> getAllClientsByRevendeur( Connection con) throws Exception {
        String query = "select * from clients where isRevendeur=?";
        PreparedStatement preparedStatement = con.prepareStatement( query);
        preparedStatement.setBoolean(1, true);
        ArrayList<Client> clients = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Client client = new Client(
                    resultSet.getInt("numero"),
                    resultSet.getString("designation"),
                    resultSet.getString("adresse"),
                    resultSet.getString("email"),
                    resultSet.getBoolean("isRevendeur")
            );
            clients.add(client);
        }
        return clients;
    }
//    get all clients from database where not isRevendeur
    public static ArrayList<Client> getAllClientsByNotRevendeur( Connection con) throws Exception {
        String query = "select * from clients where isRevendeur=?";
        PreparedStatement preparedStatement = con.prepareStatement( query);
        preparedStatement.setBoolean(1, false);
        ArrayList<Client> clients = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Client client = new Client(
                    resultSet.getInt("numero"),
                    resultSet.getString("designation"),
                    resultSet.getString("adresse"),
                    resultSet.getString("email"),
                    resultSet.getBoolean("isRevendeur")
            );
            clients.add(client);
        }
        return clients;
    }

}
