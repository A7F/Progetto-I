package UICameriere;

import utils.TablePanel;
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
import restaurant.Order;
import restaurant.Restaurant;

/**
 * Contiene l'elenco degli ordini relativi al tavolo selezionato
 * @author Luca
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
        selectedIndex= tablePanel.getSelectedTable()-1;
        orders = restaurant.getOrderTable(selectedIndex);
        
        for(int i=0; i<restaurant.getTables().get(selectedIndex).getOrdersArray().size();i++){
            model.addElement(restaurant.getTables().get(selectedIndex).getOrdersArray().get(i));
        }
        
        list=new JList(model);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selection=list.getSelectedIndex();
                if(list.isSelectionEmpty()){
                    selection=0;
                }
                if(restaurant.getTables().get(selectedIndex).getOrdersArray().isEmpty()){
                    footBar.getReadLabel().setText("Nessun ordine");
                    footBar.getDoneLabel().setText("Nessun ordine");
                }else{
                    if(restaurant.getTables().get(selectedIndex).getOrdersArray().get(selection).getRead()){
                        footBar.getReadLabel().setText("SI");
                    }else{
                        footBar.getReadLabel().setText("NO");
                    }
                    if(restaurant.getTables().get(selectedIndex).getOrdersArray().get(selection).getDone()){
                        footBar.getDoneLabel().setText("SI");
                    }else{
                        footBar.getDoneLabel().setText("NO");
                    }
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
        selectedIndex= tablePanel.getSelectedTable()-1;
        model.removeAllElements();
        
        for(int i=0; i<restaurant.getTables().get(selectedIndex).getOrdersArray().size();i++){
            model.addElement(restaurant.getTables().get(selectedIndex).getOrdersArray().get(i));
        }
    }
    
    /**
     * il metodo rimuove l'ordine relativo all' indice selezionato
     * @author Luca
     */
    public void removeOrder(){
        restaurant.getTables().get(selectedIndex).getOrdersArray().remove(list.getSelectedIndex());
        model.remove(list.getSelectedIndex());
    }
    
    /**
     * il metodo rimuove l'ordine scelto dalla lista
     * @author Luca
     * @param ord l'ordine da aggiungere
     */
    public void addOrder(Order ord){
        
        restaurant.getTables().get(selectedIndex).getOrdersArray().add(ord);
        model.removeAllElements();
        
        for(int i=0; i<restaurant.getTables().get(selectedIndex).getOrdersArray().size();i++){
            model.addElement(restaurant.getTables().get(selectedIndex).getOrdersArray().get(i));
        }
    }
    
    /**
     * metodo per ottenere l'indice dell' elemento selezionato nella lista ordini
     * @return indice elemento di ordini
     * @author Luca
     */
    public int getSelectedIndex(){
        return list.getSelectedIndex();
    }
    
    public JPanel getTablePanel(){
        return this.tablePanel.getPanel();
    }
}
