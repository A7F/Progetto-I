/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UICameriere;

import java.util.ArrayList;
import javax.swing.*;
import restaurant.Restaurant;

/**
 * Pannello contenente il menu del ristorante
 * @author Luca
 */
public class MenuPanel extends JPanel{
    
    private Restaurant restaurant;
    private ArrayList<menu.MenuElement> el;
    private JList list;
    private JScrollPane pane;
        
    public MenuPanel(Restaurant res){
        this.restaurant=res;
        init();
    }
    
    private void init(){
        el=restaurant.getMenu();
        list=new JList(el.toArray());
        pane=new JScrollPane(list);
        this.add(pane);
    }
    
    protected MenuElement getSelectedElement(){
        return (MenuElement)list.getSelectedValue();
    }
}
