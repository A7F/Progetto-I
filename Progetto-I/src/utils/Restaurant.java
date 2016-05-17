package utils;


import java.io.IOException;
import java.util.ArrayList;
import menu.Menu;
import menu.MenuElement;

/**
 *
 * @author federicovitro
 */
public class Restaurant {
    
    private String name;
    private Room room;
    private Menu menu;
    
    public Restaurant(String name, int numberOfTables, String pathFile) throws IOException{
        this.name = name;
        initRoom(numberOfTables);
        initMenu(pathFile);
        initGUI();
    }
    
    private void initMenu(String pathFile) throws IOException{
        menu = new Menu(pathFile);
        
    }
    
    private void initRoom(int numberOfTables){
        room = new Room(numberOfTables);
        
    }
    
    /**
     * Aggiunge un ordine al tavolo prescelto
     * @param tableId
     * @param order
     * @author FabioTagliani
     */
    public void addOrder(int tableId, Order order){
        
        room.addOrder(tableId, order); 
        
    }
    
    public void removeOrder(int tableId, Order order){
    
        room.removeOrder(tableId, order);
    }
    
    public void setReserved(int tableId){
    
        room.setReservedTable(tableId);
    }
    
    /**
     * Restituisce i tavoli della sala
     * @author fabiotagliani
     */
    public ArrayList<Table> getTables(){
    
        return room.getTables();
      
    }
        
    /**
    * Restituisce i tavoli non prenotati
    * @author fabiotagliani
    */
    public ArrayList<Table> getFreeTable(){
    
        return room.getFreeTable();
        
    }
    
    public ArrayList<MenuElement> getMenu(){
    
        return menu.getMenu();
    }
    
    public ArrayList<MenuElement> getMenuByName(String elementName){
        return menu.getElementByName(elementName);
    }
    
    public ArrayList<MenuElement> getElementByType(String type){
        return menu.getElementByType(type);
    }
    
    public ArrayList<MenuElement> getElementByPrice(double cash){
        return menu.getElementByPrice(cash);
    }
    
    private void initGUI(){
        //TODO da fare quando penseremo all'interfaccia grafica.
    }
    
}
