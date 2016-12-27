package restaurant;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import utils.TavoliManager;

/**
 *
 * @author federicovitro
 */
public class Room {
    
    private ArrayList<Table> tables;
    TavoliManager tavoliManager = new TavoliManager();
    
    /**
     * @author Federico Vitrò
     * @param numberOfTables numero dei tavoli nella sala
     */    
    public Room(int numberOfTables){
        tables = new ArrayList<Table>();
        initTables(numberOfTables);
    }
    
    /**
     * inizializza i tavoli alla creazione della Room
     * @author Federico Vitrò
     * @param numberOfTables numero dei tavoli nella sala
     */
    private void initTables(int numberOfTables){ 
        
        for (int i = 0; i<numberOfTables; i++){
            tables.add(new Table(i));
            tavoliManager.populateTavoli(i+1);
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
        System.out.println("Aggiunto nuovo tavolo con id: "+nextId);
    }
    
    /**
     * Rimuovi l'ultimo tavolo che è stato inserito.
     * Aggiorna anche la table Tavoli del database Ristorante.
     * @author Luca
     */
    public void removeLastTable() {

        TavoliManager mgr = new TavoliManager();
        mgr.deleteLastTavolo();
        tables.remove(tables.size() - 1);
    }
    
    /**
     * Ritorna gli ordini del singolo tavolo
     * @param tableId id del tavolo di cui ottenere gli ordini
     * @return arraylist di ordini del tavolo
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
     * questo metodo ritorna tutti gli ordini di sala messi insieme
     * @author Luca
     * @return ordini di tutta la sala
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
     * @param tableId id del tavolo da riservare
     */
    public void setReservedTable(int tableId){
    
        for (int i = 0; i < tables.size(); i++) {
            
            if(tables.get(i).getTableId() == tableId){
                if(!(tables.get(i).getIsTaken())){
                    tables.get(i).takeTable();
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Attenzione, il tavolo è già prenotato!","Attenzione",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
    
    /**
     * Metodo per liberare il tavolo dalla prenotazione
     * @param tableId id del tavolo da liberare
     * @author FabioTagliani
     */    
    public void setFreeTable(int tableId){
        
        for (int i = 0; i < tables.size(); i++) {
            if(tables.get(i).getTableId() == tableId && tables.get(i).getOrdersArray().isEmpty()){
                if((tables.get(i).getIsTaken())){
                    tables.get(i).setFreeTable();
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Il tavolo era già libero","Info",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
    
    /**
     * Restituisce i tavoli della sala
     * @author fabiotagliani
     * @return tutti i tavoli della sala
     */
    public ArrayList<Table> getTables() {
        return tables;
    }
    
    /**
     * Restituisce i tavoli non prenotati 
     * @author fabiotagliani
     * @return tutti i tavoli liberi nella sala
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
     * @return tutti i tavoli prenotati della sala
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
     * @param tableID id del tavolo su cui aggiungere l'ordine
     * @param order ordine da aggiungere
     */
    public void addOrder(int tableID, Order order) { 
        if(tables.get(tableID-1).getIsTaken() == false){
            tables.get(tableID-1).takeTable();
        }
        tables.get(tableID-1).addOrder(order);
    }
    /**
     * Rimuove il singolo ordine di un tavolo
     * @author Luca
     * @param tableID id del tavolo da cui rimuovere l'ordine
     * @param order ordine da rimuovere
     */
    public void removeSingleOrder(int tableID, Order order) {
        tables.get(tableID-1).removeOrder(order);
    }
    
    /**
     * Rimuove tutti gli ordini di un tavolo
     * @param tableId id del tavolo da cui cancellare tutti gli ordini
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
     * @return numero dei tavoli nel ristorante
     */
    public int getNumberofTables(){
        return tables.size();
    }
    
    /**
     * @author FabioTagliani
     * @return tutti i tavoli
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
