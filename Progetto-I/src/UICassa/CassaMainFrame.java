package UICassa;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import utils.Restaurant;

/**
 * Finestra principale 
 * @author FabioTagliani
 */
public class CassaMainFrame extends JFrame{
    
    private Restaurant restaurant;

    public CassaMainFrame(Restaurant restaurant) {
        this.restaurant = restaurant;
        initComponent();
    }

    private void initComponent(){
        
        MainPanel mainPanel = new MainPanel(restaurant);

        this.add(mainPanel);
        this.setTitle("Cassa");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
    }
}
