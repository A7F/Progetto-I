package UICassa;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import utils.Restaurant;

/**
 *
 * @author Fabio
 */
public class MainPanel extends JPanel{
    
    private Restaurant restaurant;
    private PanelTable panelTable;
    private PrintTicket printTicket;
   
    public MainPanel(Restaurant restaurant) {
        this.restaurant = restaurant;
       
        initComponent();
    }
    
    private void initComponent(){
    
        panelTable = new PanelTable(restaurant);
        
        printTicket = new PrintTicket(restaurant, panelTable);
        
//        this.setLayout(new BorderLayout()); 
//        this.add(panelTable,BorderLayout.NORTH);
//        this.add(printTicket,BorderLayout.CENTER);
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx =0;
        c.gridy =0;
        c.gridwidth = 2;
        c.gridheight = 1;
        this.add(panelTable,c);
        
        c.gridx =0;
        c.gridy =1;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.add(printTicket,c);
        
        
//        this.add(panelTable);
//        this.add(printTicket);
              
    }
    
}
