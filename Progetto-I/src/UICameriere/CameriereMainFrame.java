package UICameriere;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import restaurant.Restaurant;
import utils.MenuBar;

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
    int userId;
    
    public CameriereMainFrame(Restaurant restaurant,int userId){
        this.userId=userId;
        this.restaurant = restaurant;
        this.menuPanel = new MenuPanel(restaurant);
        tablePanel = new TablePanel(restaurant);
        ordersPanel = new OrdersPanel(tablePanel, restaurant);
       
        initComponent();
    }

    private void initComponent(){
        this.setLayout(new BorderLayout());
        this.setJMenuBar(new MenuBar(this,userId));
        JPanel formatPane = new JPanel();
        formatPane.setLayout(new FlowLayout());
        formatPane.add(ordersPanel);
        formatPane.add(menuPanel);
        this.add(tablePanel.getPanel(), BorderLayout.NORTH);
        this.add(formatPane,BorderLayout.CENTER);
        this.add(new LowPanel(restaurant,menuPanel,ordersPanel),BorderLayout.SOUTH);
        
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
