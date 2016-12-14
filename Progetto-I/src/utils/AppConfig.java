package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

/**
 * questa classe agisce da tramite con il file di configurazione del programma.
 * la classe è un singleton poichè non possono esisterne copie.
 * @author Luca
 */
public class AppConfig {

    private static AppConfig instance;
    private String myJson;
    JSONObject json;
    
    private String projectName;
    private String appName;
    private int tableNumber;
    private String menuPath;
    private String restaurantName;

    private AppConfig(){
        myJson = readFile();
        json = new JSONObject(myJson);
        projectName = json.getString("project_name");
        appName = json.getString("app_name");
        tableNumber = json.getInt("table_number");
        menuPath = json.getString("menu_path");
        restaurantName = json.getString("restaurant_name");
    }
    
    public static AppConfig getInstance(){
        if(instance==null){
            instance = new AppConfig();
        }
        return instance;
    }

    public static String readFile() {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("./data/appConfig.json"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
        } catch (Exception e) {
            e.toString();
        }
        return result;
    }
    
    private void writeFile() {
        try {
            FileWriter file = new FileWriter("./data/appConfig.json");
            String jsonString = json.toString();
            file.write(jsonString);
            file.flush();
            System.out.println("Config file updated");
            file.close();
        } catch (IOException ex) {
            System.out.println("Cannot update your config file");
            ex.toString();
        }
    }

    public String getProjectName(){
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
        json.put("project_name", projectName);
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
        json.put("app_name", appName);
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
        json.put("table_number", tableNumber);
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
        json.put("menu_path", menuPath);
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
        json.put("restaurant_name", restaurantName);
    }
    
    public void commitChanges(){
        writeFile();
    }

}
