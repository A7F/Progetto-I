package UICameriere;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import restaurant.Restaurant;

/**
 * Frame che contiene l'elenco dei tavoli, le ordinazioni di uno specifico 
 * tavolo e il menu del ristorante 
 * @author Fabio
 */
public class CameriereMainFrame extends JFrame{
    
    private Restaurant restaurant;
    private OrdersPanel ordersPanel;
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    TablePanel tablePanel;
    MenuPanel menuPanel;
    
    public CameriereMainFrame(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.menuPanel = new MenuPanel(restaurant);
        tablePanel = new TablePanel(restaurant);
        ordersPanel = new OrdersPanel(tablePanel, restaurant);
       
        initComponent();
    }

    private void initComponent() {
        this.setLayout(new BorderLayout());
        this.add(tablePanel.getPanel(), BorderLayout.NORTH);
        this.add(ordersPanel, BorderLayout.WEST);
        this.add(menuPanel,BorderLayout.EAST);
        
        
        this.setTitle("Cameriere");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.pack();
        this.setVisible(true);
    }
    
    public int getSelectedTable(){
        return tablePanel.getSelectedTable();
    }
}
