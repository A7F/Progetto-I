package UICameriere;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import restaurant.Restaurant;

/**
 *
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
        JPanel formatPane = new JPanel();
        formatPane.setLayout(new FlowLayout());
        formatPane.add(ordersPanel);
        formatPane.add(menuPanel);
        this.add(tablePanel.getPanel(), BorderLayout.NORTH);
        this.add(formatPane,BorderLayout.CENTER);
        this.add(new LowPanel(restaurant),BorderLayout.SOUTH);
        
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
