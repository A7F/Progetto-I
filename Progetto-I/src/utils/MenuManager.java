package utils;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import menu.MenuElement;

/**
 * questa classe facilita l'accesso (in lettura e scrittura) al database ristorante
 * in particolare, alla tabella menu.
 * @author Luca
 */
public class MenuManager {
    DatabaseManager mgr = new DatabaseManager();
    ArrayList<MenuElement> menuElements = new ArrayList<>();
    Connection connection = mgr.getConnection();
    
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
     * metodo per inizializzare l'arraylist di menu, prendendo i valori dal database table menu
     * @author FabioT
     */
    public void getMenu(){
        try {
            PreparedStatement ps = mgr.getConnection().prepareStatement("SELECT * FROM menu;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("ID");
                String name=rs.getString("Name");
                String description=rs.getString("Description");
                double price=rs.getDouble("Price");
                String typeElement=rs.getString("Tipo");
                
                MenuElement me  = new MenuElement(id, name, price, typeElement, description);
                menuElements.add(me);
            }
        } catch (SQLException ex) {
            System.out.println("errore query su database");
        }
    }
    
    /**
     * metodo per ottenere il contenuto della table impiegati come resultset
     * @see ImpiegatiFrame
     * @author Luca
     * @return resultset table impiegati
     */
    public CachedRowSet getMenuResultSet(){
        ResultSet rs = null;
        CachedRowSet crs = null;
        try {
            connection = mgr.getConnection();
            PreparedStatement ps = mgr.getConnection().prepareStatement("USE ristorante;");
            ps.executeQuery();
            ps = mgr.getConnection().prepareStatement("SELECT * FROM menu;");
            rs = ps.executeQuery();
            crs = new CachedRowSetImpl();
            crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
            crs.setUsername("root");
            crs.setPassword("");
            crs.setUrl("jdbc:mysql://localhost:3306/ristorante?relaxAutoCommit=true");
            crs.setCommand("SELECT * FROM menu");
            crs.execute(connection);
            crs.populate(rs);
        } catch (SQLException ex){
            ex.toString();
        }
        return crs;
    }
    
    /**
     * Rimuove un elementoMenu dalla table menu del databse
     * @param id 
     * @author FabioT
     */
    public void removeMenuElement(int id){
        try {
            PreparedStatement ps = mgr.getConnection().prepareStatement("DELETE FROM menu WHERE id = " + id + ";");
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("errore update sul database, update table menu");
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
                MenuElement el = new MenuElement(rs.getInt("id"),rs.getString("Name"),rs.getDouble("Price"),rs.getString("Tipo"),rs.getString("Description"));
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
        }catch(SQLException ex){
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

    /**
     * Verifica se esiste un menuELement con lo stesso id passato come parametro
     * @param id
     * @return flag di tipo booleano per verificare se presente o meno l'elemento all'interno del database
     * @author Fabio
     */
    public boolean checkMenuElementById(int id){
        PreparedStatement ps;
        boolean check=false;
        
        try {
            ps = mgr.getConnection().prepareStatement("SELECT * FROM menu WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                check= true;
            }else{
                check= false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Impossibile contattare il database", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        
        return check;
    }
    
    public Connection getConnection() {
        return connection;
    }    
}
