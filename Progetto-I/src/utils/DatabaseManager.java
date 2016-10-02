package utils;

import java.sql.Statement;  //attenzione, deve essere java.sql, NON java.beans!!!
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
    JFrame frame = new JFrame();
    
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
        try {
            statement=(Statement) connection.createStatement();
            String db = DatabaseStrings.createDatabase;
            statement.executeUpdate(db);
            System.out.println(">> DATABASE CREATO");
            String use = DatabaseStrings.useDatabase;
            statement.executeUpdate(use);
            System.out.println(">> USO DATABASE Ristorante");
            String table=DatabaseStrings.createMenu;
            statement.executeUpdate(table);
            System.out.println(">> TABELLA MENU CREATA");
            table=DatabaseStrings.createImpiegati;
            statement.executeUpdate(table);
            System.out.println(">> TABELLA IMPIEGATI CREATA");
            table=DatabaseStrings.createTavoli;
            statement.executeUpdate(table);
            System.out.println(">> TABELLA TAVOLI CREATA");
            table=DatabaseStrings.createPrenotazioni;
            statement.executeUpdate(table);
            System.out.println(">> TABELLA PRENOTAZIONI CREATA");
            table=DatabaseStrings.alterPrenotazioni;
            statement.executeUpdate(table);
            System.out.println(">> TABELLA PRENOTAZIONI MODIFICATA");
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        }
    }
    
    public void closeConnection(){
        try{
            connection.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(frame,"Non riesco a chiudere la connessione","Errore",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void runQuery(String query){
        try{
            statement=(Statement) connection.createStatement();
            statement.executeQuery(query);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(frame,"Impossibile eseguire la tua query","Errore",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void runUpdate(String update){
        try{
            statement=(Statement) connection.createStatement();
            statement.executeUpdate(update);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(frame,"Impossibile eseguire il tuo update","Errore",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Connection getConnection(){
        return this.connection;
    }
    
    public void populateUserDatabase(){
        try{
            statement=(Statement) connection.createStatement();
            String command=DatabaseStrings.useDatabase;
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
            JOptionPane.showMessageDialog(frame,"Impossibile popolare il database","Errore",JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
