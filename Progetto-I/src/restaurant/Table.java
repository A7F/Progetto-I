package restaurant;


import java.util.ArrayList;

/**
 *
 * @author federicovitro
 */
public class Table {
    private int tableId;
    private Boolean isTaken;
    private ArrayList<Order> orders;
    
    /**
     * @author Federico Vitrò
     * @param tableId id del tavolo
     */
    
    public Table(int tableId){
        this.tableId = tableId;
        isTaken = false;
        orders = new ArrayList<>();
    }
    
    /**
     * Rimuove tutti gli ordini di un tavolo
     * @author Fabio Tagliani
     */
    public void removeAllOrder(){
        ArrayList<Order> ordersToBeRemoved = new ArrayList<>();
        
        for (Order order : orders) {
            ordersToBeRemoved.add(order);
        }
        orders.removeAll(ordersToBeRemoved);
    }
    
    /**
     * ritorna l'array di ordini
     * @author Federico Vitrò
     * @return ordini fatti al tavolo
     */
    
    public ArrayList<Order> getOrdersArray(){
        return this.orders;
    }
    
    /**
     * verifica che un tavolo sia prenotato o meno
     * @author Federico Vitrò
     * @return true se il tavolo è stato prenotato
     */
    
    public Boolean getIsTaken(){
        return isTaken;
    }
    
    /**
     * prenota il tavolo
     * @author Federico Vitrò
     */
    
    public void takeTable(){
        isTaken = true;
    }
    
    /**
     * libera il tavolo
     * @author Federico Vitrò
     */
    
    public void setFreeTable(){
        isTaken = false;
    }
    
    /**
     * aggiunge un ordine
     * @author Federico Vitrò
     * @param order ordine da aggiungere
     */
    
    public void addOrder(Order order){
        orders.add(order);
    }
   
    /**
     * Rimuove il singolo elemento dell'ordine
     * @author FabioTagliani
     * @param order ordine da rimuovere
     */
    public void removeOrder(Order order) {
        orders.remove(order);
    }
    
    /**
     * restituisce l'id di un tavolo
     * @author Federico Vitrò
     * @return id del tavolo
     */
    public int getTableId() {
        return tableId;
    }
    
    /**
     * @author Federico Vitrò
     * @return tavolo, stato ed ordini
     */
    @Override
    public String toString() {
        return "tavolo: "+(tableId+1)+" || prenotato: "+isTaken+ " || ordini: " + orders +"\n";
    }
}
