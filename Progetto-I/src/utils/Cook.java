package utils;

import java.util.ArrayList;

/**
 * Gestione in cucina
 * ordersCopy è una copia dell' arraylist orders siccome non voglio che il cuoco elimini
 * elementi di ordine sul server. In sostanza funziona solo da promemoria
 * @author Luca :3
 * @param name,orders
 */
public class Cook {
    private ArrayList<Order> ordersCopy;
    private String cookName;
    private ArrayList<Order> ordersDeleted;
    
    public Cook(String name,ArrayList<Order> orders){
        this.cookName = name;
        ordersCopy = orders;
        CookUI c1 = new CookUI(orders);
    }
    
    /**
     * Imposta come eseguito un ordine
     * @author Luca ^^
     * @param order 
     */
    private void setDone(Order order){
        ordersCopy.remove(order);
        ordersDeleted.add(order);
    }
    
    /**
     * Ripristina l'ultimo ordine segnato come eseguito. Gestione FIFO
     * @author Luca :)
     */
    private void restoreDone(){
        Order tmp;
        tmp = ordersDeleted.get(ordersDeleted.size()-1);
        ordersDeleted.remove(tmp);
        ordersCopy.set(1, tmp);
    }
}
