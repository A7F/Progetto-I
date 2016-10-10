package utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import restaurant.Impiegato;

/**
 * questa classe fa da utility per ottenere i dati degli impiegati ed operare sulla relativa table
 * @author Luca
 */
public class ImpiegatiManager {
    DatabaseManager manager = new DatabaseManager();
    ArrayList<Impiegato> impiegati = new ArrayList<>();
    
    public ImpiegatiManager(){
        init();
    }

    private void init(){
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement(DatabaseStrings.useDatabase);
            ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        getImpiegati();
    }
    
    /**
     * metodo per inizializzare l'arraylist di impiegati, prendendo i valori dal database table impiegati
     * @author Luca
     */
    public void getImpiegati(){
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement("SELECT * FROM impiegati;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("ID");
                String username=rs.getString("username");
                Ruoli ruolo=Ruoli.valueOf(rs.getString("ruolo"));
                boolean status=rs.getBoolean("status");
                String password=rs.getString("password");
                Impiegato imp = new Impiegato(id,username,ruolo,status,password);
                impiegati.add(imp);
            }
        } catch (SQLException ex) {
            System.out.println("errore query su database");
        }
    }
    
    /**
     * metodo per aggiornare l'arrayList impiegati agli ultimi valori del database table impiegati
     * @author Luca
     */
    public void reloadImpiegati(){
        impiegati.removeAll(impiegati);
        getImpiegati();
    }
    
    /**
     * aggiunge un nuovo impiegato alla table impiegati del database
     * @author Luca
     * @param impiegato l'oggetto impiegato da inserire
     * @param pass la password dell' impiegato da aggiungere
     */
    public void addImpiegato(Impiegato impiegato,String pass){
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement("INSERT INTO impiegati(username,password,ruolo) VALUES(?,?,?);");
            ps.setString(1, impiegato.getUsername());
            ps.setString(2, pass);
            pass=null;
            ps.setString(3, impiegato.getRuolo().toString());
            ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("errore update sul database, update table impiegati");
        }
    }
    
    /**
     * metodo per ottenere l'impiegato con relativo id
     * @param id id da cercare
     * @return oggetto Impiegato corrispondente all' id cercato
     * @author Luca
     */
    public Impiegato getImpiegatoById(int id){
        Impiegato result = null;
        for (Impiegato impiegati1 : impiegati) {
            if (impiegati1.getId() == id) {
                result = impiegati1;
                break;
            }
        }
        return result;
    }
    
    /**
     * metodo per ottenere l'impiegato con relativo username
     * @param user username da cercare
     * @return oggetto Impiegato corrispondente all' username cercato
     * @author Luca
     */
    public Impiegato getImpiegatoByUsername(String user){
        Impiegato result = null;
        for (Impiegato impiegati1 : impiegati) {
            if (impiegati1.getUsername().equals(user)){
                result = impiegati1;
                break;
            }
        }
        return result;
    }
}
