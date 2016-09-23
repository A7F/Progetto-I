/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UICameriere;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import restaurant.Restaurant;

/**
 *
 * @author Fabio
 */
public class CameriereMainFrame extends JFrame{
    
    private Restaurant restaurant;
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    TablePanel tablePanel;
    MenuPanel menuPanel;
    
    public CameriereMainFrame(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.menuPanel = new MenuPanel(restaurant);
        tablePanel = new TablePanel(restaurant.getTables().size());
        initComponent();
    }

    private void initComponent() {
        this.add(tablePanel);
        this.add(menuPanel);
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