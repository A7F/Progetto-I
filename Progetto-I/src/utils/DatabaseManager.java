package utils;

import java.sql.Statement;  //attenzione, deve essere java.sql, NON java.beans!!!
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * questa classe si occupa di aprire la connessione al server remoto SQL e di
 * gestire query-update sul server
 * @author Luca
 */
public class DatabaseManager {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/boh";
    private Connection connection = null;
    public static Statement statement = null;
    
    public void initServer(){
        try{
            Class.forName(DRIVER).newInstance();
            try{
                String cmd = "CREATE DATABASE IF NOT EXISTS RestaurantMenu";
                connection=DriverManager.getConnection(DATABASE_URL, "username", "password");
                statement=(Statement) connection.createStatement();
                statement.executeUpdate(cmd);
                String table="CREATE TABLE Menu(ElementID int,Name varchar(200),Description varchar(1000),Price int,Tipo varchar(10))";
                statement.executeUpdate(table);
            }catch(SQLException e){
                System.err.println("SQLException: " + e.getMessage());
                System.err.println("SQLState: " + e.getSQLState());
                System.err.println("VendorError: " + e.getErrorCode());
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public void closeConnection(){
        try{
            connection.close();
        }catch(SQLException e){
            System.err.println("Cannot release connection to database");
        }
    }
    
    public void runQuery(String query){
        try{
            statement.executeQuery(query);
        }catch(SQLException e){
            System.err.println("Guarda non sono riuscito ad esegire la tua query");
        }
    }
    
    public void runUpdate(String update){
        try{
            statement.executeUpdate(update);
        }catch(SQLException e){
            System.err.println("Guarda non sono riuscito ad esegire il tuo update");
        }
    }
}
