package restaurant;

import java.util.ArrayList;
import utils.TavoliManager;

/**
 *
 * @author federicovitro
 */
public class Room {
    
    private ArrayList<Table> tables;
    
    /**
     * @author Federico Vitrò
     * @param numberOfTables 
     */    
    public Room(int numberOfTables){
        tables = new ArrayList<Table>();
        initTables(numberOfTables);
    }
    
    /**
     * inizializza i tavoli alla creazione della Room
     * @author Federico Vitrò
     * @param numberOfTables 
     */
    private void initTables(int numberOfTables){ 
        
        for (int i = 0; i<numberOfTables; i++){
            tables.add(new Table(i));
        }
    }
    
    /**
     * Aggiungi un nuovo tavolo con id incrementato automaticamente.
     * Aggiorna anche la table Tavoli del database Ristorante.
     * @author Luca
     */
    public void addNewTable(){
        TavoliManager mgr = new TavoliManager();
        mgr.insertNextTavolo();
        int nextId = tables.size()+1;
        tables.add(new Table(nextId));
    }
    
    /**
     * Rimuovi l'ultimo tavolo che è stato inserito.
     * Aggiorna anche la table Tavoli del database Ristorante.
     * @author Luca
     */
    public void removeLastTable(){
        TavoliManager mgr = new TavoliManager();
        mgr.deleteLastTavolo();
        tables.remove(tables.size());
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
     * @author Luca
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
    
    /**
     * dato l'id di un tavolo e un ordinazione aggiunge quest'ultima al tavolo
     * @author Federico Vitrò
     * @param tableID
     * @param order 
     */
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
     * @author Luca
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
     * restituisce il numero dei tavolo
     * @author Federico Vitrò
     * @return tables.size()
     */
       
    public int getNumberofTables(){
        return tables.size();
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
