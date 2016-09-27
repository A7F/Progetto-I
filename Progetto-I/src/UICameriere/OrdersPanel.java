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
import javax.swing.JTextArea;
import restaurant.Order;
import restaurant.Restaurant;

/**
 *
 * @author Fabio
 */
public class OrdersPanel extends JTextArea implements Observer{

    private TablePanel tablePanel;
    private Restaurant restaurant;
    private ArrayList<Order> orders;
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
        
        
        
        this.setText(restaurant.getOrderTable(selectedIndex).toString());
        restaurant.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
       
        selectedIndex= tablePanel.getSelectedTable();
        System.out.println("selectadeIndexUPDATE : " + selectedIndex);
        this.setText(restaurant.getTables().get(selectedIndex -1).getOrdersArray().toString());
         
        //this.setText(restaurant.getOrderTable(selectedIndex).toString());
    }
    
    
}
