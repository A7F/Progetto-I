package utils;

import java.sql.Statement;  //attenzione, deve essere java.sql, NON java.beans!!!
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * questa classe si occupa di aprire la connessione al server remoto SQL e di
 * gestire query-update sul server
 * @author Luca
 */
public class DatabaseManager {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/ristorante?relaxAutoCommit=true";
    private Connection connection = null;
    public static Statement statement = null;
    JFrame frame = new JFrame();
    
    public DatabaseManager(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection(DATABASE_URL, "root", "");
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Crea il database e tutte le table necessarie al funzionamento del programma.
     * @author Luca
     */
    public void initServer(){
        try {
            statement=(Statement) connection.createStatement();
            statement.executeUpdate(DatabaseStrings.dropDatabase);
            statement.executeUpdate(DatabaseStrings.createDatabase);
            statement.executeUpdate(DatabaseStrings.useDatabase);
            statement.executeUpdate(DatabaseStrings.dropMenu);
            statement.executeUpdate(DatabaseStrings.createMenu);
            statement.executeUpdate(DatabaseStrings.createImpiegati);
            statement.executeUpdate(DatabaseStrings.createTavoli);
            statement.executeUpdate(DatabaseStrings.createPrenotazioni);
            statement.executeUpdate(DatabaseStrings.alterPrenotazioni);
            System.out.println(">> Database e tabelle generate con successo");
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
        }
    }
    
    /**
     * metodo per la chiusura connessione al database
     * @author Luca
     */
    public void closeConnection(){
        try{
            connection.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(frame,"Non riesco a chiudere la connessione","Errore",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * metodo di servizio per eseguire facilmente una query sul database del programma
     * @author Luca
     * @param query query da eseguire sul database ristorante
     */
    public void runQuery(String query){
        try{
            statement=(Statement) connection.createStatement();
            statement.executeQuery(query);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(frame,"Impossibile eseguire la tua query","Errore",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * metodo per eseguire un update sul database del programma
     * @author Luca
     * @param update update da eseguire sul database ristorante
     */
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
    
    /**
     * metodo di servizio per popolare automaticamente la table impiegati per eseguire test
     * del programma.
     * @author Luca
     */
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
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
            JOptionPane.showMessageDialog(frame,"Impossibile popolare il database","Errore",JOptionPane.ERROR_MESSAGE);
        }
    }
}
