package menu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DatabaseManager;
import utils.DatabaseStrings;


/**
 *
 * @author Javier
 */
public class LoadFileText {
    
    /**
     * 
     * @param String
     * @return ArrayList of MenuElements
     * @throws FileNotFoundException
     * @throws IOException 
     * Questo metodo prende un file di testo che deve essere formattato in questo modo:
     * String(TAB)double(TAB)menuElementType(TAB)String
     */
    public static ArrayList<MenuElement> loadFile (String pathFile) throws FileNotFoundException, IOException{
        ArrayList<MenuElement> menuElements = new ArrayList<>(); 
        FileReader fr = new FileReader(pathFile);
        BufferedReader br = new BufferedReader(fr);
        while(br.ready()){
            String line = br.readLine();
            String[] splittedLine = line.split("\t");
            Double price = new Double(splittedLine[1]);
            menuElements.add(new MenuElement(splittedLine[0], price, splittedLine[2],splittedLine[3]));
        }
        return menuElements;
    }
    
    /**
     * questo metodo prende gli elementi dal file e li mette nel database
     * @author Luca
     * @param pathFile
     * @throws java.io.FileNotFoundException
     */
    public static void fillDatabase(String pathFile) throws FileNotFoundException, IOException{
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
        mgr.closeConnection();
        System.out.println(">> CONNESSIONE CHIUSA");
    }
    
}
