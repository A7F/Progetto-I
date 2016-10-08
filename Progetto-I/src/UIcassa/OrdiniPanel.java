package UIcassa;

import UICameriere.*;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import restaurant.Order;
import restaurant.Restaurant;

/**
 * Contiene l'elenco degli ordini relativi al tavolo selezionato. La lista è 
 * popolata con Order.
 * @author Luca
 */
public class OrdiniPanel extends JPanel implements Observer{

    private TablePanel tablePanel;
    private Restaurant restaurant;
    private ArrayList<Order> orders;
    JScrollPane pane;
    DefaultListModel model=new DefaultListModel();
    private JList list;
    int selectedIndex;    

    public OrdiniPanel(TablePanel tablePanel, Restaurant restaurant) {
        this.setPreferredSize(new Dimension(300,240));
        this.tablePanel = tablePanel;
        this.restaurant = restaurant;
        this.setBorder(new TitledBorder("Ordini"));
        init();
    }

    private void init(){
        selectedIndex= tablePanel.getSelectedTable();
        orders = restaurant.getOrderTable(selectedIndex);
        
        for(int i=0; i<restaurant.getTables().get(selectedIndex -1).getOrdersArray().size();i++){
            model.addElement(restaurant.getTables().get(selectedIndex -1).getOrdersArray().get(i));
        }
        
        list=new JList(model);
        
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pane = new JScrollPane(list);
        pane.setPreferredSize(new Dimension(290,200));
        this.add(pane);
        restaurant.addObserver(this);
    }

    @Override
    /**
     * Azioni da eseguire quando è segnalato che observable ha cambiato stato
     * @author Luca
     */
    public void update(Observable o, Object arg) {
        selectedIndex= tablePanel.getSelectedTable();
        model.removeAllElements();
        
        for(int i=0; i<restaurant.getTables().get(selectedIndex -1).getOrdersArray().size();i++){
            model.addElement(restaurant.getTables().get(selectedIndex -1).getOrdersArray().get(i));
        }
    }
    
    /**
     * metodo per rimuovere l'ordine selezionato dall' arraylist di ordini del tavolo selezionato
     * @author Luca
     */
    public void removeOrder(){
        restaurant.getTables().get(selectedIndex-1).getOrdersArray().remove(list.getSelectedIndex());
        model.remove(list.getSelectedIndex());
    }
    
    /**
     * metodo per rimuovere uno specifico ordine dall' arraylist di ordini del tavolo selezionato
     * @param ord l'ordine da eliminare
     * @author Luca
     */
    public void removeOrder(Order ord){
        restaurant.getTables().get(selectedIndex-1).getOrdersArray().remove(ord);
        model.remove(list.getSelectedIndex());
    }
    
    /**
     * metodo per cancellare tutti gli ordini sul tavolo selezionato
     * @author Luca
     */
    public void removeAllOrders(){
        restaurant.getTables().get(selectedIndex-1).removeAllOrder();
        model.removeAllElements();
    }
    
    /**
     * metodo per rimuovere l'ordine di indice specificato sul tavolo selezionato
     * @param index l'indice dell' ordine da eliminare
     * @author Luca
     */
    public void removeOrder(int index){
        restaurant.getTables().get(selectedIndex-1).getOrdersArray().remove(index);
        model.remove(index);
    }
    
    /**
     * aggiunge un ordine al tavolo selezionato
     * @param ord l'ordine da aggiungere sul tavolo selezionato
     * @author Luca
     */
    public void addOrder(Order ord){
        
        restaurant.getTables().get(selectedIndex-1).getOrdersArray().add(ord);
        model.removeAllElements();
        
        for(int i=0; i<restaurant.getTables().get(selectedIndex -1).getOrdersArray().size();i++){
            model.addElement(restaurant.getTables().get(selectedIndex -1).getOrdersArray().get(i));
        }
    }
    
    /**
     * metodo per ottenere l'indice del valore selezionato nella lista ordini
     * @return indice dell' ordine selezionato
     * @author Luca
     */
    public int getSelectedIndex(){
        return list.getSelectedIndex();
    }
    
    /**
     * metodo getter per ottenere il modello della lista ordini
     * @return modello della lista ordini
     * @author Luca
     */
    public DefaultListModel getModel(){
        return this.model;
    }
}
