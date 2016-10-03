package UICameriere;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import restaurant.Restaurant;

/**
 * Pannello contenente il menu del ristorante
 * @author Luca
 */
public class MenuPanel extends JPanel{
    
    private Restaurant restaurant;
    private ArrayList<menu.MenuElement> el;
    private ArrayList<String> elementName = new ArrayList<>();
    private JList list;
    private JScrollPane pane;
        
    public MenuPanel(Restaurant res){
        this.restaurant=res;
        this.setPreferredSize(new Dimension(300,240));
        this.setBorder(new TitledBorder("Menu"));
        init();
    }
    
    private void init(){
        el=restaurant.getMenu();
        
        for(int i=0;i<el.size();i++){
            elementName.add(el.get(i).getNameElement());
        }
        
        list=new JList(elementName.toArray());
        pane=new JScrollPane(list);
        pane.setPreferredSize(new Dimension(290,200));
        this.add(pane);
    }
    
    protected menu.MenuElement getSelectedElement(){
        return el.get(list.getSelectedIndex());
    }
    
    public int getSelectedIndex(){
        return list.getSelectedIndex();
    }
}
