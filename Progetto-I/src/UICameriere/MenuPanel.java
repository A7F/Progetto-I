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
    private ArrayList<String> elementName = new ArrayList<>();
    private JList list;
    private JScrollPane pane;
        
    public MenuPanel(Restaurant res){
        this.restaurant=res;
        init();
    }
    
    private void init(){
        el=restaurant.getMenu();
        
        for(int i=0;i<el.size();i++){
            elementName.add(el.get(i).getNameElement());
        }
        
        list=new JList(elementName.toArray());
        pane=new JScrollPane(list);
        this.add(pane);
    }
    
    protected menu.MenuElement getSelectedElement(){
        return el.get(list.getSelectedIndex());
    }
}
