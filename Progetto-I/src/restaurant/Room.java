package restaurant;

import java.util.ArrayList;

/**
 *
 * @author federicovitro
 */
public class Room {
    
    private ArrayList<Table> tables;
        
    public Room(int numberOfTables){
        tables = new ArrayList<Table>();
        initTables(numberOfTables);
    }
    
    
    /**
     * inizializza i tavoli alla creazione della Room
     * @param numberOfTables 
     */
    private void initTables(int numberOfTables){ 
        
        for (int i = 0; i<numberOfTables; i++){
            tables.add(new Table(i));
        }
    }
    
    /**
     * Ritorna gli ordini del singolo tavolo
     * @param tableId
     * @return Order
     * @author FabioTagliani
     */
    public ArrayList<Order> getOrdersTable(int tableId){
    
        ArrayList<Order> ordersTable = new ArrayList<Order>();
        
        for (int i = 0; i < tables.size(); i++) {
            
            if (tables.get(i).getTableId() == tableId){
            
                ordersTable.addAll(tables.get(i).getOrdersArray());
            }
        }
//        for (Table t : tables) {
//            
//            if(t.getTableId() == tableId){
//            
//                ordersTable.addAll(t.getOrdersArray());
//            }
//        }
        return ordersTable;
    }
    
    /**
     * questo metodo ritorna tutti gli ordini di sala messi insieme!
     * @author Luca
     * @return roomOrders
     */
    public ArrayList<Order> getOrdersArray(){
        ArrayList<Order> roomOrders = new ArrayList<Order>();
        for(int i=0;i<tables.size();i++){
            roomOrders.addAll(tables.get(i).getOrdersArray());
        }
        return roomOrders;
    }
    
    /**
     * aggiunge alla room un tavolo
     */
    public void addtables(){
        tables.add(new Table(tables.size()));
    }
    
    /**
     * Riserva il tavolo 
     * @author fabiotagliani
     * @param tableId 
     */
    public void setReservedTable(int tableId){
    
        for (int i = 0; i < tables.size(); i++) {
            
            if(tables.get(i).getTableId() == tableId){
                if(!(tables.get(i).getIsTaken())){
                    tables.get(i).takeTable();
                }else{
                    System.out.println("EEEH NO EH IL TAVOLO E' GIA' PRENOTATO ZIOOOOO");
                }
            }
        }
    }
    
    /**
     * Metodo per liberare il tavolo dalla prenotazione
     * @param tableId
     * @author FabioTagliani
     */    
    public void setFreeTable(int tableId){
        
        for (int i = 0; i < tables.size(); i++) {
            if(tables.get(i).getTableId() == tableId && tables.get(i).getOrdersArray().isEmpty()){
                if((tables.get(i).getIsTaken())){
                    
                    System.out.println("sono in free table");
                    tables.get(i).setFreeTable();
                }else{
                    System.out.println("Il tavolo era già libero");
                }
            }
        }
    }
    /**
     * Restituisce i tavoli della sala
     * @author fabiotagliani
     * @return tables
     */
    public ArrayList<Table> getTables() {
        return tables;
    }
    
    /**
     * Restituisce i tavoli non prenotati 
     * @author fabiotagliani
     * @return tableFree
     */
    public ArrayList<Table> getFreeTable(){
        
        ArrayList<Table> tableFree = new ArrayList<Table>();
        for (int i = 0; i < tables.size(); i++) {
            if(tables.get(i).getIsTaken() == false){
                tableFree.add(tables.get(i));
            }
        }
        return tableFree;
    }

    /**
     * Restituisce i tavoli prenotati 
     * @author Luca :D
     * @return tableTaken
     */
    public ArrayList<Table> getTakenTable(){
        
        ArrayList<Table> tableTaken = new ArrayList<Table>();
        for (int i = 0; i < tables.size(); i++) {
            if(tables.get(i).getIsTaken() == true){
                tableTaken.add(tables.get(i));
            }
        }
        return tableTaken;
    }
    
    public void addOrder(int tableID, Order order) { 
        if(tables.get(tableID-1).getIsTaken() == false){
            tables.get(tableID-1).takeTable();
            
            // DEBUG
            System.err.println("Il tavolo non era prenotato. Verra prenotato.");
        }
        tables.get(tableID-1).addOrder(order);
    }
    /**
     * Rimuove il singolo ordine di un tavolo
     * @param tableID
     * @param order 
     */
    public void removeSingleOrder(int tableID, Order order) {
        tables.get(tableID-1).removeOrder(order);
    }
    
    /**
     * Rimuove tutti gli ordini di un tavolo
     * @param tableId
     * @author Fabio Tagliani
     */
    public void removeAllOrder(int tableId){
        for (int i = 0; i < tables.size(); i++) {
            if(tables.get(i).getTableId() == tableId){
                tables.get(i).removeAllOrder();
            }
        }
    }
    
    /**
     * @author FabioTagliani
     * @param tableID
     * @return 
     */
    public Ticket createTicket(int tableID){
        return tables.get(tableID -1).createTicket();
    }
    
    public Ticket getTicket(int tableID){
        return tables.get(tableID - 1).getTicket();
    }
    
    /**
     * @author FabioTagliani
     * @return ret
     */   
    @Override
    public String toString() {
        
        String ret="";
        for (int i=0; i<tables.size(); i++){
            ret+=tables.get(i).toString();
        }
        return ret;
    }    
}
