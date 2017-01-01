package utils.managers;

import UICameriere.CameriereMainFrame;
import restaurant.Restaurant;
import UIcook.CookUI;
import UIcassa.CassaMainFrame;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import UIcapo.CapoUI;

/**
 * questa classe si occupa di fornire i metodi per la gestione del login, inoltre
 * fa comparire popup di errato/avvenuto login
 * @author Luca
 */
public class LoginManager {
    
    private Map<Integer,String> rel = new HashMap<>();
    private DatabaseManager dbm = new DatabaseManager();
    private Restaurant restaurant;
    private int userId = 0;
    private JFrame frame;
    
    public LoginManager(Restaurant r){
        this.frame = new JFrame();
        this.restaurant = r;
        rel.put(1, "CASSA");
        rel.put(2, "CUOCO");
        rel.put(3, "CAMERIERE");
        rel.put(4, "CAPO");
    }

    /**
     * costruttore usato per la menubar: non serve il resto, basta solo l'userId.
     * @param userId l'id utente che effettua login
     * @author Luca
     */
    public LoginManager(int userId) {
        this.frame = new JFrame();
    this.userId=userId;
    }
    
    /**
     * controlla le credenziali (username, password, valore selezionato) inserite da utente.
     * @param username  lo username inserito da utente
     * @param password  la password inserita da utente
     * @param selected  il valore selezionato del radiobutton
     * @return boolean che conferma o meno l'avvenuta autenticazione
     * @throws SQLException query non eseguita correttamente
     * @author Luca
     */
    public boolean checkCredentials(String username, String password, String selected) throws SQLException{
        int val = getSelectedKey(selected);
        
        PreparedStatement ps;
        ResultSet rs = null;
        try {
            dbm.runQuery("USE ristorante;");
            ps = dbm.getConnection().prepareStatement("SELECT * FROM impiegati WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
        }catch (SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
            JOptionPane.showMessageDialog(frame,"Username o Password errate.","Login",JOptionPane.ERROR_MESSAGE);
            return false;   //deve comunque fallire il login!
        }
        
        if(val==0){
            JOptionPane.showMessageDialog(frame,"Non è stato selezionato nulla.","Errore",JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            
            if(rs.next()){
                if(rs.getString("ruolo").equals(selected)){
                    JOptionPane.showMessageDialog(frame,"Login effettuato con successo.","Login",JOptionPane.INFORMATION_MESSAGE);
                    userId=rs.getInt("ID");
                    connectUser(userId);
                    return true;
                }
                JOptionPane.showMessageDialog(frame,"Username o password errate.","Login",JOptionPane.INFORMATION_MESSAGE);
                return false;
            }else{
                JOptionPane.showMessageDialog(frame,"Username o Password errate.","Login",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }
    
    /**
     * Questo metodo ottiene il valore selezionato nel pannello radio button convertendolo in un intero.
     * Utile per non operare continuamente con il testo del radiobutton selezionato, usando gli int è tutto più immediato.
     * @param selected  valore selezionato nel pannello radiobutton
     * @return Il valore selezionato convertito da stringa ad intero
     * @author Luca
     */
    public Integer getSelectedKey(String selected){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("CASSA");
        strings.add("CUOCO");
        strings.add("CAMERIERE");
        strings.add("CAPO");
        
        Set set = rel.entrySet();
        Iterator iterator = set.iterator();
        
        if(strings.contains(selected)){
            
            while(iterator.hasNext()){
                Map.Entry mentry = (Map.Entry)iterator.next();
                
                if(selected.equals(mentry.getValue())){
                    return (Integer)mentry.getKey();
                }
            }
        }
    
        return 0;
    }
    
    /**
     * questo metodo permette al capo di registrare facilmente un nuovo utente nel database
     * @author Luca
     * @param username  username da registrare
     * @param password  password da registrare
     * @param selected  ruolo selezionato nel pannello radiobutton
     * @throws SQLException update non eseguito correttamente
     */
    public void insertValue(String username, String password, String selected) throws SQLException{
        int val = getSelectedKey(selected);
        
        if(checkCredentials(username,password,selected)){
            if(val==4){
                
                PreparedStatement ps = dbm.getConnection().prepareStatement("INSERT INTO Impiegati(username,password,ruolo) VALUES(?,?,?);");
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, selected);
                ps.executeUpdate();
                
                ps = dbm.getConnection().prepareStatement("INSERT INTO Impiegati(username,password) VALUES(?,?);");
                ps.setString(1, username);
                ps.setString(2, password);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(frame,"Inserito con successo utente "+username+".","Database",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    /**
     * questo metodo si occupa di istanziare la grafica corretta a seconda dell' utente che ha
     * effettuato il login.
     * @param status conferma di login avvenuta con successo
     * @param selected  valore selezionato nel radiobutton e convertito ad intero
     * @see getSelectedKey
     * @see checkCredentials
     * @author Luca
     */
    public void graphicsDispatcher(boolean status,int selected){
        if(status){
            switch(selected){
            case 1:
                CassaMainFrame cassaFrame = new CassaMainFrame(restaurant,userId);
                break;
            case 2:
                CookUI ui = new CookUI(restaurant,userId);
                break;
            case 3:
                CameriereMainFrame cmf = new CameriereMainFrame(restaurant,userId);
                break;
            case 4:
                CapoUI win = new CapoUI(restaurant, userId);
                break;
            default:
                System.out.println("Eh, qui niente :( ");
        }
        }
    }

    public int getUserId() {
        return userId;
    }
    
    /**
     * questo metodo disconnette l'utente con userId specificato.
     * @author Luca
     * @param userId id dell' utente connesso
     */
    public void disconnectUser(int userId){
        try {
            PreparedStatement ps = dbm.getConnection().prepareStatement("use ristorante;");
            ps.executeQuery();
            ps = dbm.getConnection().prepareStatement("UPDATE impiegati SET status=? WHERE id=?;");
            ps.setBoolean(1, false);
            ps.setInt(2, this.userId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame,"Logout fallito. Qualcosa è andato storto!","Logout",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * questo metodo connette l'utente con relativo userId.
     * @author Luca
     * @param userId id dell'utente da connettere
     */
    public void connectUser(int userId){
        try {
            PreparedStatement ps = dbm.getConnection().prepareStatement("UPDATE impiegati SET status=? WHERE id=?;");
            ps.setBoolean(1, true);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame,"Login fallito. Qualcosa è andato storto!","Login",JOptionPane.ERROR_MESSAGE);
        }
    }
}
