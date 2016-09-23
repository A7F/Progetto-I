/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UICameriere;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import restaurant.Order;
import restaurant.Restaurant;

/**
 *
 * @author Fabio
 */
public class OrdersPanel extends JPanel implements Observer{

    private TablePanel tablePanel;
    private Restaurant restaurant;
    private ArrayList<Order> orders;
    private JList list;
    

    public OrdersPanel(TablePanel tablePanel, Restaurant restaurant) {
        this.tablePanel = tablePanel;
        this.restaurant = restaurant;
        init();
    }

    private void init(){
    
        int selectedIndex= tablePanel.getSelectedTable();
        orders = restaurant.getTables().get(selectedIndex).getOrdersArray();
        
        list = new JList(orders.toArray());
        JScrollPane pane = new JScrollPane(list);
        
        this.add(pane);
    }

    @Override
    public void update(Observable o, Object arg) {
        
        System.out.println("--------------->>>  CIAO");
        list.repaint();
    }
    
    
}
