package UIcassa;

import utils.commonGraphics.TablePanel;
import java.awt.Dimension;
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
        selectedIndex= tablePanel.getSelectedTable()-1;
        
        for(int i=0; i<restaurant.getTables().get(selectedIndex).getOrdersArray().size();i++){
            model.addElement(restaurant.getTables().get(selectedIndex).getOrdersArray().get(i));
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
     * Azioni da eseguire quando observable cambia stato: 
     * refresh dei bottoni facendo attenzione a quando si elimina il tavolo che è anche quello
     * correntemente selezionato. Il try-catch gestisce possibili out of bounds: il tavolo che
     * viene rimosso non può più essere quello correntemente selezionato dunque passa la selezione
     * sul tavolo immediatamente precedente.
     * @author Luca
     * @see TablePanel
     */
    public void update(Observable o, Object arg) {
        
        model.removeAllElements();
        
        try{
            selectedIndex= tablePanel.getSelectedTable()-1;
            for(int i=0; i<restaurant.getTables().get(selectedIndex).getOrdersArray().size();i++){
                model.addElement(restaurant.getTables().get(selectedIndex).getOrdersArray().get(i));
            }
        }catch(IndexOutOfBoundsException ex){
            selectedIndex= restaurant.getTables().size()-1;
            for(int i=0; i<restaurant.getTables().get(selectedIndex).getOrdersArray().size();i++){
                model.addElement(restaurant.getTables().get(selectedIndex).getOrdersArray().get(i));
            }
        }        
        
    }
    
    /**
     * metodo per rimuovere l'ordine selezionato dall' arraylist di ordini del tavolo selezionato
     * @author Luca
     */
    public void removeOrder(){
        restaurant.getTables().get(selectedIndex).getOrdersArray().remove(list.getSelectedIndex());
        model.remove(list.getSelectedIndex());
    }
    
    /**
     * metodo per rimuovere uno specifico ordine dall' arraylist di ordini del tavolo selezionato
     * @param ord l'ordine da eliminare
     * @author Luca
     */
    public void removeOrder(Order ord){
        restaurant.getTables().get(selectedIndex).getOrdersArray().remove(ord);
        model.remove(list.getSelectedIndex());
    }
    
    /**
     * metodo per cancellare tutti gli ordini sul tavolo selezionato
     * @author Luca
     */
    public void removeAllOrders(){
        restaurant.getTables().get(selectedIndex).removeAllOrder();
        model.removeAllElements();
    }
    
    /**
     * metodo per rimuovere l'ordine di indice specificato sul tavolo selezionato
     * @param index l'indice dell' ordine da eliminare
     * @author Luca
     */
    public void removeOrder(int index){
        restaurant.getTables().get(selectedIndex).getOrdersArray().remove(index);
        model.remove(index);
    }
    
    /**
     * aggiunge un ordine al tavolo selezionato
     * @param ord l'ordine da aggiungere sul tavolo selezionato
     * @author Luca
     */
    public void addOrder(Order ord){
        
        restaurant.getTables().get(selectedIndex).getOrdersArray().add(ord);
        model.removeAllElements();
        
        for(int i=0; i<restaurant.getTables().get(selectedIndex).getOrdersArray().size();i++){
            model.addElement(restaurant.getTables().get(selectedIndex).getOrdersArray().get(i));
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
