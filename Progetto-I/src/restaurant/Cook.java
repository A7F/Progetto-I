package restaurant;

import java.util.ArrayList;

/**
 * Gestione in cucina
 * @author Luca
 */
public class Cook {
    private ArrayList<Order> ordersCopy;
    private String cookName;
    private ArrayList<Order> ordersDeleted;
    
    public Cook(String name,ArrayList<Order> orders){
        this.cookName = name;
        ordersCopy = orders;
    }
    
    public ArrayList<Order> getOrdersCopy(){
        return this.ordersCopy;
    }
    
    
}
