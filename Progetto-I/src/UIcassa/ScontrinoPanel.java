package UIcassa;

import UICameriere.*;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import restaurant.Order;
import restaurant.Restaurant;

/**
 * Contiene lo scontrino
 * @author Luca
 */
public class ScontrinoPanel extends JPanel{

    private TablePanel tablePanel;
    private Restaurant restaurant;
    private ArrayList<Order> scontrino;
    JScrollPane pane;
    DefaultListModel model=new DefaultListModel();
    private JList list;
    int selectedIndex;    

    public ScontrinoPanel(TablePanel tablePanel, Restaurant restaurant) {
        this.setPreferredSize(new Dimension(300,240));
        this.tablePanel = tablePanel;
        this.restaurant = restaurant;
        this.setBorder(new TitledBorder("Scontrino"));
        init();
    }

    private void init(){
        
        list=new JList(model);
        
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pane = new JScrollPane(list);
        pane.setPreferredSize(new Dimension(290,200));
        this.add(pane);
    }
    
    public void removeElement(){
        model.remove(list.getSelectedIndex());
    }
    
     public void removeElement(int index){
        model.remove(index);
    }
     
    public void removeAllElement(){
        model.removeAllElements();
    }
    
    public void addElement(Order ord){
        model.addElement(ord);
    }
    
    public int getSelectedIndex(){
        return list.getSelectedIndex();
    }
    
    public String calculateCurrentTot(){
        double tot = 0;
        for(int i=0;i<model.size();i++){
            Order ord = (Order) model.getElementAt(i);
            double price = ord.getPrice();
            tot = tot+price;
        }
        return String.valueOf(tot+" $");
    }
    
    public DefaultListModel getModel(){
        return this.model;
    }
    
    public String getScontrinoElements(){
        StringBuilder str = new StringBuilder();
        for(int i=0;i<model.size();i++){
            Order ord = (Order) model.getElementAt(i);
            str.append(ord.getNameEl()).append("\t").append(String.valueOf(ord.getPrice())).append("\n");
        }
        return str.toString();
    }
}
