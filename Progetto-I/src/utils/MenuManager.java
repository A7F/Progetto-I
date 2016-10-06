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

    /**
     * metodo di inizializzazione table menu
     * @author Luca
     */
    private void init(){
        try {
            PreparedStatement ps = mgr.getConnection().prepareStatement(DatabaseStrings.useDatabase);
            ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * metodo per ottenere tutti i tavoli da table menu del database ristorante
     * @return arraylist di menuelement
     * @author Luca
     */
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
    
    /**
     * metodo per inserire un nuovo menuelement nel database ristorante
     * @param name nome del menuelement
     * @param price prezzo del menuelement
     * @param tipo tipo del menuelement
     * @param description descrizione (ovvero gli ingredienti) del menuelement
     * @author Luca
     */
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
    
    /**
     * metodo per cambiare prezzo al menuelement con id specificato
     * @param id id del menuelement di cui cambiare prezzo
     * @param newPrice nuovo prezzo da assegnare al menuelement con id specificato
     * @author Luca
     */
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
    
    /**
     * metodo per cambiare nome al menuelement con id specificato
     * @param id id del menuelement di cui cambiare nome
     * @author Luca
     * @param newName nuovo nome da assegnare al menuelement
     */
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
    
    /**
     * metodo per cambiare nome al menuelement con id specificato
     * @param id id del menuelement di cui cambiare nome
     * @param newDesc nuova descrizione da assegnare al menuelement con id specificato
     * @author Luca
     */
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
    
    /**
     * metodo per cambiare tipo di elemento tramite suo id
     * @param id id del menuelement di cui cambiare nome
     * @param newType nuovo tipo da assegnare al menuelement con id specificato
     * @author Luca
     */
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
