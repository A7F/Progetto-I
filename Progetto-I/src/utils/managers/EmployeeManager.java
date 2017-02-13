package utils.managers;


import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import restaurant.Employee;
import utils.DatabaseStrings;

/**
 * questa classe fa da utility per ottenere i dati degli impiegati ed operare sulla relativa table
 * @author Luca
 */
public class EmployeeManager {
    DatabaseManager manager = DatabaseManager.getInstance();
    ArrayList<Employee> impiegati = new ArrayList<>();
    Connection connection = manager.getConnection();
    
    public EmployeeManager(){
        init();
    }

    private void init(){
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement(DatabaseStrings.useDatabase);
            ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        getEmployee();
    }
    
    /**
     * metodo per inizializzare l'arraylist di impiegati, prendendo i valori dal database table impiegati
     * @author Luca
     */
    public void getEmployee(){
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement("SELECT * FROM impiegati;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("ID");
                String username=rs.getString("username");
                String ruolo=rs.getString("ruolo");
                boolean status=rs.getBoolean("status");
                String password=rs.getString("password");
                Employee imp = new Employee(id,username,ruolo,status,password);
                impiegati.add(imp);
            }
        } catch (SQLException ex) {
            System.out.println("errore query su database");
        }
    }
    
    /**
     * metodo per ottenere il contenuto della table impiegati come resultset
     * @see graphics.capo.EmployeePanel
     * @author Luca
     * @return resultset table impiegati
     */
    public CachedRowSet getEmployeeResultSet(){
        ResultSet rs = null;
        CachedRowSet crs = null;
        try {
            connection = manager.getConnection();
            PreparedStatement ps = manager.getConnection().prepareStatement("USE ristorante;");
            ps.executeQuery();
            ps = manager.getConnection().prepareStatement("SELECT * FROM impiegati;");
            rs = ps.executeQuery();
            crs = new CachedRowSetImpl();
            crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
            crs.setUsername("root");
            crs.setPassword("");
            crs.setUrl("jdbc:mysql://localhost:3306/ristorante?relaxAutoCommit=true");//jdbc:mysql://localhost:3306/test?relaxAutoCommit=true
//            crs.setCommand("USE ristorante");
//            crs.execute(connection);
            crs.setCommand("SELECT * FROM impiegati");
            crs.execute(connection);
            crs.populate(rs);
        } catch (SQLException ex){
            ex.toString();
        }
        return crs;
    }
    
    /**
     * metodo per aggiornare l'arrayList impiegati agli ultimi valori del database table impiegati
     * @author Luca
     */
    public void reloadImpiegati(){
        impiegati.removeAll(impiegati);
        getEmployee();
    }
    
    /**
     * aggiunge un nuovo impiegato alla table impiegati del database
     * @author Luca
     * @param impiegato l'oggetto impiegato da inserire
     * @param pass la password dell' impiegato da aggiungere
     */
    public void addEmployee(Employee impiegato,String pass){
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
     * Rimuove un impiegato dalla table impiegato del databse
     * @param id id del' impiegato da rimuovere
     * @author FabioT
     */
    public void removeEmployee(int id){
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement("DELETE FROM impiegati WHERE id = " + id + ";");
            ps.executeUpdate();
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
    public Employee getEmployeeById(int id){
        Employee result = null;
        for (Employee impiegati1 : impiegati) {
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
    public Employee getEmployeeByUsername(String user){
        Employee result = null;
        for (Employee impiegati1 : impiegati) {
            if (impiegati1.getUsername().equals(user)){
                result = impiegati1;
                break;
            }
        }
        return result;
    }
    
    public Connection getConnection(){
        return manager.getConnection();
    }
}
