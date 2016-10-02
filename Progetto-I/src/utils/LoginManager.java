package utils;

import UICameriere.CameriereMainFrame;
import UICassa.CassaMainFrame;
import UIcapo.CapoUI;
import restaurant.Restaurant;
import UIcook.CookUI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * questa classe si occupa di fornire i metodi per la gestione del login, inoltre
 * fa comparire popup di errato/avvenuto login
 * @author Luca
 */
public class LoginManager {
    
    Map<Integer,String> rel = new HashMap<>();
    DatabaseManager dbm = new DatabaseManager();
    Restaurant restaurant;
    int userId = 0;
    JFrame frame = new JFrame();
    
    public LoginManager(Restaurant r){
        this.restaurant = r;
        rel.put(1, "CASSA");
        rel.put(2, "CUOCO");
        rel.put(3, "CAMERIERE");
        rel.put(4, "KING");
    }

    /**
     * costruttore usato per la menubar: non serve il resto, basta solo l'userId.
     * @param userId 
     * @author Luca
     */
    LoginManager(int userId) {
    this.userId=userId;
    }
    
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
    
    public Integer getSelectedKey(String selected){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("CASSA");
        strings.add("CUOCO");
        strings.add("CAMERIERE");
        strings.add("KING");
        
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
    
    public void insertValue(String username, String password, String selected) throws SQLException{
        int val = getSelectedKey(selected);
        
        if(checkCredentials(username,password,selected)){
            if(val==4){
                
                PreparedStatement ps = dbm.getConnection().prepareStatement("INSERT INTO Impiegati(username,password,ruolo) VALUES(?,?,?);");
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, selected);
                ps.executeUpdate();
                
                dbm.runUpdate("INSERT INTO Impiegati(username,password) VALUES("+username+","+password+";");
                JOptionPane.showMessageDialog(frame,"Inserito con successo utente "+username+".","Database",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
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
                CapoUI win = new CapoUI(userId);
                break;
            default:
                System.out.println("Eh, qui niente :( ");
        }
        }
    }

    public int getUserId() {
        return userId;
    }
    
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
