package utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import menu.MenuElement;

/**
 * questa classe facilita l'accesso (in lettura e scrittura) al database ristorante
 * in particolare, alla tabella menu.
 * @author Luca
 */
public class MenuManager {
    DatabaseManager mgr = new DatabaseManager();
    ArrayList<MenuElement> menuElements = new ArrayList<>();
    
    public MenuManager(){
        init();
    }

    private void init(){
        try {
            PreparedStatement ps = mgr.getConnection().prepareStatement(DatabaseStrings.useDatabase);
            ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<MenuElement> listFromMenuTable(){
        try {
            PreparedStatement ps = mgr.getConnection().prepareStatement(DatabaseStrings.getAllMenu);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                MenuElement el = new MenuElement(rs.getString("Name"),rs.getDouble("Price"),rs.getString("Tipo"),rs.getString("Description"));
                menuElements.add(el);
            }
        } catch (SQLException ex){
            Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.menuElements;
    }
    
    public void addMenuElementToDatabase(String name,double price, String tipo, String description){
        MenuElement newElement = new MenuElement(name,price,tipo,description);
        try {
            PreparedStatement ps = mgr.getConnection().prepareStatement("INSERT INTO Menu(Name,Description,Price,Tipo) VALUES(?,?,?,?);");
            ps.setString(1, newElement.getNameElement());
            ps.setString(2, newElement.getDescription());
            ps.setDouble(3, newElement.getPrizeElement());
            ps.setString(4, newElement.getTypeElement());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void changeElementPrice(int id,double newPrice){
        try {
            PreparedStatement ps = mgr.getConnection().prepareStatement("ALTER TABLE Menu SET Price=? WHERE id=?;");
            ps.setDouble(1, newPrice);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void changeElementName(int id,String newName){
        try {
            PreparedStatement ps = mgr.getConnection().prepareStatement("ALTER TABLE Menu SET Name=? WHERE id=?;");
            ps.setString(1, newName);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void changeElementDescription(int id,String newDesc){
        try {
            PreparedStatement ps = mgr.getConnection().prepareStatement("ALTER TABLE Menu SET Description=? WHERE id=?;");
            ps.setString(1, newDesc);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void changeElementType(int id,String newType){
        try {
            PreparedStatement ps = mgr.getConnection().prepareStatement("ALTER TABLE Menu SET Tipo=? WHERE id=?;");
            ps.setString(1, newType);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
