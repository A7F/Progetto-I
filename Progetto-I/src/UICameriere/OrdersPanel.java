package UICameriere;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import menu.MenuElement;
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
    FootBar footBar;
    

    public OrdersPanel(TablePanel tablePanel, Restaurant restaurant, FootBar fbar) {
        this.footBar = fbar;
        this.setPreferredSize(new Dimension(300,240));
        this.tablePanel = tablePanel;
        this.restaurant = restaurant;
        this.setBorder(new TitledBorder("Ordini"));
        init();
    }

    private void init(){
        selectedIndex= tablePanel.getSelectedTable();
        orders = restaurant.getOrderTable(selectedIndex);
        
        System.out.println("selectedIndex in orders panel:  " + selectedIndex);
        
        for(int i=0; i<restaurant.getTables().get(selectedIndex -1).getOrdersArray().size();i++){
            model.addElement(restaurant.getTables().get(selectedIndex -1).getOrdersArray().get(i));
        }
        
        list=new JList(model);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selection=list.getSelectedIndex();
                if(list.isSelectionEmpty()){
                    selection=0;
                }
                if(restaurant.getTables().get(selectedIndex-1).getOrdersArray().isEmpty()){
                    footBar.getReadLabel().setText("Nessun ordine");
                    footBar.getDoneLabel().setText("Nessun ordine");
                }else{
                    footBar.getReadLabel().setText(String.valueOf(restaurant.getTables().get(selectedIndex-1).getOrdersArray().get(selection).getRead()));
                    footBar.getDoneLabel().setText(String.valueOf(restaurant.getTables().get(selectedIndex-1).getOrdersArray().get(selection).getDone()));
                }
            }
        });
        
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
    
    public void removeOrder(){
        restaurant.getTables().get(selectedIndex-1).getOrdersArray().remove(list.getSelectedIndex());
        model.remove(list.getSelectedIndex());
    }
    
    public void addOrder(Order ord){
        
        restaurant.getTables().get(selectedIndex-1).getOrdersArray().add(ord);
        model.removeAllElements();
        
        for(int i=0; i<restaurant.getTables().get(selectedIndex -1).getOrdersArray().size();i++){
            model.addElement(restaurant.getTables().get(selectedIndex -1).getOrdersArray().get(i));
        }
    }
    
    public int getSelectedIndex(){
        return list.getSelectedIndex();
    }
}
