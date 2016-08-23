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
     * @author FabioTagliani
     */
    void removeOrder(Order order) {
        orders.remove(order);
    }
    
    public int getTableId() {
        return tableId;
    }
    /**
     * @author FabioTagliani
     */
    public Ticket getTicket() {

        Ticket ticket = new Ticket();
        ticket.elaboraTicket(orders);

        return ticket;
    }
        
    @Override
    public String toString() {
        return "tavolo: "+(tableId+1)+" || prenotato: "+isTaken+ " || ordini: " + orders +"\n";
    }
}
