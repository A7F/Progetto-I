package utils.managers;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import utils.DatabaseStrings;

/**
 * questa classe si occupa di aprire la connessione al server remoto SQL e di
 * gestire query-update sul server
 * @author Luca
 */
public class DatabaseManager {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/?relaxAutoCommit=true";
    private static Connection connection = null;
    public static Statement statement = null;
    private static DatabaseManager mgr;
    
    private DatabaseManager(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection(DATABASE_URL, "root", "");
            this.initServer();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
            JOptionPane.showMessageDialog(new JFrame(),"Sembra non siano attive connessioni SQL sul tuo computer.\nPer favore avvia una connessione e riprova.","Errore",JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * singleton
     * @author Luca
     * @return istanza di DatabaseManager
     */
    public static DatabaseManager getInstance(){
        if(mgr==null){
            mgr=new DatabaseManager();
        }else{
            return mgr;
        }
        return mgr;
    }
    
    /**
     * Crea il database e tutte le table necessarie al funzionamento del programma.
     * @author Luca
     */
    public void initServer(){
        try {
            statement=(Statement) connection.createStatement();
            statement.executeUpdate(DatabaseStrings.createDatabase);
            statement.executeUpdate(DatabaseStrings.useDatabase);
            statement.executeUpdate(DatabaseStrings.createMenu);
            statement.executeUpdate(DatabaseStrings.createImpiegati);
            statement.executeUpdate(DatabaseStrings.createTavoli);
            statement.executeUpdate(DatabaseStrings.createPrenotazioni);
            statement.executeUpdate(DatabaseStrings.createSnapshot);
            statement.executeUpdate(DatabaseStrings.alterPrenotazioni);
            
            
            System.out.println("genero entrate, se esistenti...");
            try{
                statement.executeUpdate("INSERT INTO impiegati(username,password,ruolo) VALUES('cuoco1','passwordcuoco1','CUOCO');");
                statement.executeUpdate("INSERT INTO impiegati(username,password,ruolo) VALUES('cameriere1','passwordcameriere1','CAMERIERE');");
                statement.executeUpdate("INSERT INTO impiegati(username,password,ruolo) VALUES('cassa1','passwordcassa1','CASSA');");
                statement.executeUpdate("INSERT INTO impiegati(username,password,ruolo) VALUES('principale1','passwordcapo1','CAPO');");
                statement.executeUpdate("INSERT INTO impiegati(username,password,ruolo) VALUES('cuoco2','passwordcuoco2','CUOCO');");
                statement.executeUpdate("INSERT INTO impiegati(username,password,ruolo) VALUES('cameriere2','passwordcameriere2','CAMERIERE');");
            }catch(SQLException epc){
                System.out.println("Entrate demo già presenti; Le salto...");
            }

            System.out.println(">> Database e tabelle generate con successo");
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
            JOptionPane.showMessageDialog(new JFrame(),"SQLException: " + e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(new JFrame(),"Impossibile eseguire la tua query","Errore",JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(new JFrame(),"Impossibile eseguire il tuo update","Errore",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Connection getConnection(){
        return this.connection;
    }
}
