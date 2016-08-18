package utils;

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
    
    public LoginManager(){
        rel.put(1, "CASSA");
        rel.put(2, "CUOCO");
        rel.put(3, "CAMERIERE");
        rel.put(4, "KING");
    }
    
    public boolean checkCredentials(String username, String password, String selected) throws SQLException{
        int val = getSelectedKey(selected);
        
        dbm.runUpdate("CREATE TABLE IF NOT EXISTS Impiegati(username VARCHAR(20) UNIQUE,password VARCHAR(20) UNIQUE);");
        PreparedStatement ps;
        ResultSet rs = null;
        try {
            ps = dbm.getConnection().prepareStatement("SELECT * FROM impiegati WHERE username=? AND pass=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERRORE QUERY SQL LINEA 40");
        }
        
        if(val==0){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,"Non è stato selezionato nulla.","Errore",JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            JFrame frame = new JFrame();
            if(rs.next()){
                JOptionPane.showMessageDialog(frame,"Login effettuato con successo.","Login",JOptionPane.INFORMATION_MESSAGE);
                return true;
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
                JFrame frame = new JFrame();
                
                PreparedStatement ps = dbm.getConnection().prepareStatement("INSERT INTO Impiegati(username,password) VALUES(?,?);");
                ps.setString(1, username);
                ps.setString(2, password);
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
                System.out.println("qui parte la grafica della cucina");
                break;
            case 2:
                System.out.println("qui parte la grafica del cuoco");
                break;
            case 3:
                System.out.println("qui parte la grafica del cameriere");
                break;
            case 4:
                System.out.println("qui parte la grafica del boss");
                break;
            default:
                System.out.println("Eh, qui niente :( ");
        }
        }
    }
}
