package restaurant;

import java.util.ArrayList;

/**
 * Gestione in cucina
 * ordersCopy è una copia dell' arraylist orders siccome non voglio che il cuoco elimini
 * elementi di ordine sul server. In sostanza funziona solo da promemoria
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
