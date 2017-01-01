package menu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.managers.DatabaseManager;
import utils.DatabaseStrings;


/**
 *
 * @author Javier
 */
public class LoadFileText {
    
    /**
     * questo metodo prende gli elementi dal file e li mette nel database. Il file di testo deve essere
     * formattato nel seguente modo:
     * String(TAB)double(TAB)menuElementType(TAB)String
     * @author Luca
     * @param pathFile percorso del file contenente il menu
     * @return arraylist di menuelement
     * @throws java.io.FileNotFoundException file di menu non trovato
     * @throws IOException file di menu non trovato
     */
    public static ArrayList<MenuElement> menuFromDatabase(String pathFile) throws FileNotFoundException, IOException{
        ArrayList<MenuElement> menuElements = new ArrayList<>(); 
        FileReader fr = new FileReader(pathFile);
        BufferedReader br = new BufferedReader(fr);
        DatabaseManager mgr = new DatabaseManager();
        mgr.initServer();
        mgr.runUpdate(DatabaseStrings.dropMenu);
        mgr.runUpdate(DatabaseStrings.createMenu);
        
        while(br.ready()){
            String line = br.readLine();
            String[] splittedLine = line.split("\t");
            Double price = new Double(splittedLine[1]);
            
            try {
                PreparedStatement ps = mgr.getConnection().prepareStatement("INSERT INTO Menu(Name,Description,Price,Tipo) VALUES(?,?,?,?);");
                ps.setString(1, splittedLine[0]);
                ps.setString(2, splittedLine[3]);
                ps.setDouble(3, price);
                ps.setString(4, splittedLine[2]);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("SQLException: " + e.getMessage());
                System.err.println("SQLState: " + e.getSQLState());
                System.err.println("VendorError: " + e.getErrorCode());
            }
        }
        
        try {
            PreparedStatement ps = mgr.getConnection().prepareStatement("SELECT * FROM Menu");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                menuElements.add(new MenuElement(rs.getInt("ID"),rs.getString("Name"),rs.getDouble("Price"),rs.getString("Description"),rs.getString("Tipo")));
            }
        } catch (SQLException ex) {
            ex.toString();
        }
        
        mgr.closeConnection();
        return menuElements;
    }
}
