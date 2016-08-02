package utils;

import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JList;

/**
 * Gestione in cucina
 * ordersCopy è una copia dell' arraylist orders siccome non voglio che il cuoco elimini
 * elementi di ordine sul server. In sostanza funziona solo da promemoria
 * @author Luca :3
 * @param name,orders
 */
public class Cook extends Observable{
    private ArrayList<Order> ordersCopy;
    private String cookName;
    private ArrayList<Order> ordersDeleted;
    private JList jList1;
    
    
    public Cook(String name,ArrayList<Order> orders){
        this.cookName = name;
        ordersCopy = new ArrayList<>();
        ordersCopy = orders;
        ordersDeleted = new ArrayList<>();
        initComponent();
    }
    
    private void initComponent(){
    
        jList1 = new JList(ordersToStrings(ordersCopy).toArray());
        jList1.setCellRenderer(new MyListCellRender());
        jList1.setFont(new java.awt.Font("Dialog", 1, 25));
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
       
    }
    
    private ArrayList<String> ordersToStrings(ArrayList<Order> orders){
        ArrayList<String> s = new ArrayList<String>();
        for(int i=0;i<orders.size();i++){
            s.add(orders.get(i).getNameEl());
        }
        return s;
    }

    public JList getjList1() {
        return jList1;
    }
        
    /**
     * Imposta come eseguito un ordine
     * @author Luca ^^
     * @param order 
     */
    public void setDone(Order order){
        
        ordersCopy.remove(order);
        ordersDeleted.add(order);
        setChanged();
        notifyObservers(ordersCopy);
    }
    
    /**
     * Ripristina l'ultimo ordine segnato come eseguito. Gestione FIFO
     * @author Luca :)
     */
    public void restoreDone(){
        Order tmp;
        tmp = ordersDeleted.get(ordersDeleted.size()-1);
        ordersDeleted.remove(tmp);
        ordersCopy.set(1, tmp);
    }

    public ArrayList<Order> getOrdersCopy() {
        return ordersCopy;
    }
    
}
