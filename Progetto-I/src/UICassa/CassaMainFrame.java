package UICassa;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import restaurant.Restaurant;
import utils.MenuBar;

/**
 * Finestra principale 
 * @author FabioTagliani
 */
public class CassaMainFrame extends JFrame{
    
    private Restaurant restaurant;
    int userId;

    public CassaMainFrame(Restaurant restaurant,int userId) {
        this.userId=userId;
        this.restaurant = restaurant;
        initComponent();
    }

    private void initComponent(){
        
        MainPanel mainPanel = new MainPanel(restaurant);
        
        this.setJMenuBar(new MenuBar(this,userId));
        this.add(mainPanel);
        this.setTitle("Cassa");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(800, 500);
        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
    }
}
