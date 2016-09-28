package UICameriere;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import restaurant.Order;
import restaurant.Restaurant;

/**
 * Contiene l'elenco degli ordini relativi al tavolo selezionato
 * @author Fabio
 */
public class OrdersPanel extends JPanel implements Observer{

    private TablePanel tablePanel;
    private Restaurant restaurant;
    private ArrayList<Order> orders;
    JScrollPane pane;
    DefaultListModel model=new DefaultListModel();
    private JList list;
    int selectedIndex;
    

    public OrdersPanel(TablePanel tablePanel, Restaurant restaurant) {
        this.tablePanel = tablePanel;
        this.restaurant = restaurant;
        init();
    }

    private void init(){
        selectedIndex= tablePanel.getSelectedTable();
        orders = restaurant.getOrderTable(selectedIndex);
        
        System.out.println("selectedIndex in  orders panel:  " + selectedIndex);
        
        for(int i=0; i<restaurant.getTables().get(selectedIndex -1).getOrdersArray().size();i++){
            model.addElement(restaurant.getTables().get(selectedIndex -1).getOrdersArray().get(i));
        }
        
        list=new JList(model);
        pane = new JScrollPane(list);
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
        System.out.println("selectedIndexUPDATE : " + selectedIndex);
        model.removeAllElements();
        
        for(int i=0; i<restaurant.getTables().get(selectedIndex -1).getOrdersArray().size();i++){
            model.addElement(restaurant.getTables().get(selectedIndex -1).getOrdersArray().get(i));
        }
    }
}
