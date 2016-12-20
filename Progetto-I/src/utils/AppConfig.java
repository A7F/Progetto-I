package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 * questa classe agisce da tramite con il file di configurazione del programma.
 * la classe è un singleton poichè non possono esisterne copie.
 * @author Luca
 */
public class AppConfig {

    private static AppConfig instance;
    JSONObject json;
    private String configPath = "./data/appConfig.json";
    
    private String appName;
    private int tableNumber;
    private String menuPath;
    private String restaurantName;

    private AppConfig(){
        json = new JSONObject(readFile());
        configPath = json.getString("this_path");
        appName = json.getString("app_name");
        tableNumber = json.getInt("table_number");
        menuPath = json.getString("menu_path");
        restaurantName = json.getString("restaurant_name");
    }
    
    /**
     * metodo singleton, ritorna istanza di classe
     * @return istanza della classe
     * @author Luca
     */
    public static AppConfig getInstance(){
        if(instance==null){
            instance = new AppConfig();
        }
        return instance;
    }

    /**
     * metodo per leggere il file di configurazione dell'applicazione. La stringa di ritorno
     * verrà convertita in JSONObject
     * @return file come String
     * @author Luca
     */
    private String readFile() {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(configPath));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "File di configurazione mancante: ne sarà generato uno nuovo.", "Info", JOptionPane.INFORMATION_MESSAGE);
            buildFile();
        }
        return result;
    }
    
    /**
     * metodo per salvare i cambiamenti (eventuali) sul file json
     * @author Luca
     */
    private void writeFile() {
        try {
                FileWriter file = new FileWriter(configPath);
                String jsonString = json.toString();
                file.write(jsonString);
                file.flush();
                System.out.println("Config file updated");
                file.close();

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(), "Errore di scrittura sul file.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * metodo per creare il file di configurazione qualora non esistesse
     * @author Luca
     */
    private void buildFile(){
        json = new JSONObject();
        json.put("app_name", "non ho un nome");
        json.put("table_number", 3);
        json.put("menu_path", "./data/menu.txt");
        json.put("this_path", configPath);
        json.put("def_dbuser", "root");
        json.put("def_dbpw", "");
        json.put("def_dburl", "jdbc:mysql://localhost:3306/ristorante?relaxAutoCommit=true");
        json.put("def_lang", "IT");
        json.put("restaurant_name", "Diablo");
        
        writeFile();
    }


    /**
     * @author Luca
     * @return nome dell'applicazione
     */
    public String getAppName() {
        return appName;
    }

    /**
     * @author Luca
     * @param appName nome dell'applicazione
     */
    public void setAppName(String appName) {
        this.appName = appName;
        json.put("app_name", appName);
    }

    /**
     * @author Luca
     * @return numero dei davoli di default
     */
    public int getTableNumber() {
        return tableNumber;
    }

    /**
     * @author Luca
     * @param tableNumber numero dei tavoli di default
     */
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
        json.put("table_number", tableNumber);
    }

    /**
     * @author Luca
     * @return percorso del file del menu
     */
    public String getMenuPath() {
        return menuPath;
    }

    /**
     * @author Luca
     * @param menuPath percorso file del menu
     */
    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
        json.put("menu_path", menuPath);
    }

    /**
     * @author Luca
     * @return nome del ristorante
     */
    public String getRestaurantName() {
        return restaurantName;
    }

    /**
     * @author Luca
     * @param restaurantName nome del ristorante
     */
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
        json.put("restaurant_name", restaurantName);
    }
    
    /**
     * metodo per scrivere le modifiche su file di configurazione
     * @author Luca
     * @see writeFile
     */
    public void commitChanges(){
        writeFile();
    }

}
