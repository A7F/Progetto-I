package utils;

import java.sql.Statement;  //attenzione, deve essere java.sql, NON java.beans!!!
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * questa classe si occupa di aprire la connessione al server remoto SQL e di
 * gestire query-update sul server
 * @author Luca
 */
public class DatabaseManager {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306";
    private Connection connection = null;
    public static Statement statement = null;
    
    public DatabaseManager(){
        try {
            connection=DriverManager.getConnection(DATABASE_URL, "root", "");
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        }
    }
    
    public void initServer(){
        try{
            try{
                statement=(Statement) connection.createStatement();
                String db = "CREATE DATABASE IF NOT EXISTS Ristorante;";
                statement.executeUpdate(db);
                System.out.println(">> DATABASE CREATO");
                String use = "USE Ristorante;";
                statement.executeUpdate(use);
                System.out.println(">> USO DATABASE Ristorante");
                String table="CREATE TABLE IF NOT EXISTS Menu(ID int NOT NULL AUTO_INCREMENT,Name VARCHAR(200),Description VARCHAR(1000),Price FLOAT(4,2),Tipo VARCHAR(10), PRIMARY KEY(ID));";
                statement.executeUpdate(table);
                System.out.println(">> TABELLA MENU CREATA");
                table="CREATE TABLE IF NOT EXISTS Impiegati(ID int NOT NULL AUTO_INCREMENT,username VARCHAR(20) UNIQUE,password VARCHAR(20) UNIQUE,ruolo VARCHAR(10),status BIT DEFAULT 0,PRIMARY KEY(ID));";
                statement.executeUpdate(table);
                System.out.println(">> TABELLA IMPIEGATI CREATA");
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
            System.err.println("Non riesco a chiudere la connessione");
        }
    }
    
    public void runQuery(String query){
        try{
            statement=(Statement) connection.createStatement();
            statement.executeQuery(query);
        }catch(SQLException e){
            System.err.println("Guarda non sono riuscito ad eseguire la tua query");
        }
    }
    
    public void runUpdate(String update){
        try{
            statement=(Statement) connection.createStatement();
            statement.executeUpdate(update);
        }catch(SQLException e){
            System.err.println("Guarda non sono riuscito ad eseguire il tuo update");
        }
    }
    
    public Connection getConnection(){
        return this.connection;
    }
    
    public void populateUserDatabase(){
        try{
            statement=(Statement) connection.createStatement();
            String command="USE ristorante;";
            statement.executeUpdate(command);
            command="INSERT INTO impiegati(username,password,ruolo) VALUES('cuoco1','passwordcuoco1','CUOCO');";
            statement.executeUpdate(command);
            command="INSERT INTO impiegati(username,password,ruolo) VALUES('cuoco2','passwordcuoco2','CUOCO');";
            statement.executeUpdate(command);
            command="INSERT INTO impiegati(username,password,ruolo) VALUES('cameriere1','passwordcameriere1','CAMERIERE');";
            statement.executeUpdate(command);
            command="INSERT INTO impiegati(username,password,ruolo) VALUES('cameriere2','passwordcameriere2','CAMERIERE');";
            statement.executeUpdate(command);
            command="INSERT INTO impiegati(username,password,ruolo) VALUES('cassa1','passwordcassa1','CASSA');";
            statement.executeUpdate(command);
            command="INSERT INTO impiegati(username,password,ruolo) VALUES('cassa2','passwordcassa2','CASSA');";
            statement.executeUpdate(command);
            command="INSERT INTO impiegati(username,password,ruolo) VALUES('principale1','passwordcapo1','CAPO');";
            statement.executeUpdate(command);
        }catch(SQLException e){
            System.err.println("Guarda non sono riuscito ad eseguire il tuo update");
        }
    }
    
}
