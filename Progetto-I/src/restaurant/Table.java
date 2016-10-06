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
    
    public ArrayList<Order> getOrdersArray(){
        return this.orders;
    }
    
    public Boolean getIsTaken(){
        return isTaken;
    }
    
    public void takeTable(){
        isTaken = true;
    }
    
    public void setFreeTable(){
        isTaken = false;
    }
    
    public void addOrder(Order order){
        orders.add(order);
    }
   
    /**
     * Rimuove il singolo elemento dell'ordine
     * @author FabioTagliani
     */
    public void removeOrder(Order order) {
        orders.remove(order);
    }
        
    public int getTableId() {
        return tableId;
    }
      
    @Override
    public String toString() {
        return "tavolo: "+(tableId+1)+" || prenotato: "+isTaken+ " || ordini: " + orders +"\n";
    }
}
