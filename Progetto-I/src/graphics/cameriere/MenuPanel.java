package graphics.cameriere;

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
    
    /**
     * avvia la grafica del cameriere disponendo i componenti nel frame
     * @author Luca
     */
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
    
    /**
     * metodo per ottenere il menuelement relativo all' elemento menu slezionato
     * @return MenuElement
     * @author Luca
     * @see MenuElement
     */
    protected menu.MenuElement getSelectedElement(){
        return el.get(list.getSelectedIndex());
    }
    
    /**
     * metodo per ottenere l'indice dell' elemento selezionato nella lista menu
     * @return indice selezionato menu
     * @author Luca
     */
    public int getSelectedIndex(){
        return list.getSelectedIndex();
    }
}
