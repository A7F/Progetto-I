package utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * questa classe facilita l'accesso alla table Tavoli del database del ristorante.
 * @author Luca
 */
public class TavoliManager {
    DatabaseManager mgr = new DatabaseManager();
    ArrayList<Integer> tavoliPrenotati = new ArrayList<>();
    
    public TavoliManager(){
        init();
    }

    /**
     * questo metodo inizializza il database impostandolo come in uso
     * @author Luca
     */
    private void init() {
        try {
            PreparedStatement ps = mgr.getConnection().prepareStatement(DatabaseStrings.useDatabase);
            ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * il metodo ritorna l'arraylist di tutti i tavoli prenotati presenti nel database
     * @author Luca
     * @return  ArrayList di tutti i tavoli prenotati
     */
    public ArrayList<Integer> getTavoliPrenotati(){
        try {
            PreparedStatement ps = mgr.getConnection().prepareStatement("SELECT * FROM tavoli WHERE PRENOTATO=?");
            ps.setBoolean(1, true);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                tavoliPrenotati.add(rs.getInt("NUMTAVOLO"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.tavoliPrenotati;
    }
    
    /**
     * metodo di utility usato in ciclo for che inserisce il tavolo con relativo id nella table Tavoli del database
     * @author Luca
     * @param tableId   l'ID del tavolo da inserire
     */
    public void populateTavoli(int tableId){
        try {
            PreparedStatement ps = mgr.getConnection().prepareStatement("SELECT * FROM Tavoli;");
            ResultSet rs =ps.executeQuery();
            if(!rs.next()){
                ps = mgr.getConnection().prepareStatement("INSERT INTO Tavoli(NUMTAVOLO) VALUES(?);");
                ps.setInt(1, tableId);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TavoliManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * metodo di utility per inserire un tavolo rispettando la progressione degli id dei tavoli.
     * @author Luca
     */
    public void insertNextTavolo(){
        try {
            PreparedStatement ps = mgr.getConnection().prepareStatement("SELECT * FROM Tavoli ORDER BY NUMTAVOLO DESC LIMIT 1;");
            ResultSet rs = ps.executeQuery();
            int max_id=0;
            while(rs.next()){
                max_id=rs.getInt("NUMTAVOLO");
            }
            ps = mgr.getConnection().prepareStatement("INSERT INTO Tavoli(NUMTAVOLO) VALUES(?);");
            ps.setInt(1, max_id+1);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TavoliManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * metodo per cancellare l'ultimo tavolo inserito, ovvero quello con numero più grande.
     * @author Luca
     */
    public void deleteLastTavolo(){
        try {
            PreparedStatement ps = mgr.getConnection().prepareStatement("SELECT * FROM Tavoli ORDER BY NUMTAVOLO DESC LIMIT 1;");
            ResultSet rs = ps.executeQuery();
            int max_id=0;
            while(rs.next()){
                max_id=rs.getInt("NUMTAVOLO");
            }
            ps = mgr.getConnection().prepareStatement("DELETE FROM Tavoli WHERE NUMTAVOLO=?;");
            ps.setInt(1, max_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TavoliManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dropTavoli(){
        try {
            //PreparedStatement ps = mgr.getConnection().prepareStatement(DatabaseStrings.dropTavoli);
            //ps.executeUpdate();
            PreparedStatement ps = mgr.getConnection().prepareStatement(DatabaseStrings.createTavoli);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TavoliManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
