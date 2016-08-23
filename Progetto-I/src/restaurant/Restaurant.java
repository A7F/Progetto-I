package restaurant;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
    }
    
    private void initMenu(String pathFile) throws IOException{
        menu = new Menu(pathFile);
        
    }
    
    private void initRoom(int numberOfTables){
        room = new Room(numberOfTables);
    }
    
    public ArrayList<Order> getOrdersArray(){
        return room.getOrdersArray();
    }
    
    public String getRestaurantName(){
        return this.name;
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
    
    public void addTables(){
       room.addtables();
    }
    
    public void removeOrder(int tableId, Order order){
    
        room.removeOrder(tableId, order);
    }
    
    public void setReserved(int tableId){
    
        room.setReservedTable(tableId -1);
    }
    
    /**
     * Restituisce i tavoli della sala
     * @author fabiotagliani
     */
    public ArrayList<Table> getTables(){
    
        return room.getTables();
      
    }
    /**
     * Restituisce lo scontrino del tavolo 
     * @param tableID
     * @return Ticket
     * @author FabioTagliani
     */
    public Ticket getTicket(int tableID){
    
       return room.getTicket(tableID);
    }
    
    /**
    * Restituisce i tavoli non prenotati
    * @author FabioTagliani
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
    
     public ArrayList<Table> getTakenTable(){
        
        return room.getTakenTable();
    }

    public void createTicket(int selectedTable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
