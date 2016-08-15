/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIcook;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import utils.Cook;
import utils.Order;

/**
 * Finestra principale
 * @author fabio
 */
public class CookMainFrame extends JFrame{
    
    private Cook cook;
    private Order order;

    public CookMainFrame(Cook cook, Order order) {
        this.cook = cook;
        this.order = order;
        initComponent();
    }
    
    private void initComponent(){
        ButtonPanel buttonPanel = new ButtonPanel(cook,order);
        MainPanel mainPanel = new MainPanel(cook);
       
        this.setTitle("Cucina");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setLayout(new BorderLayout()); 
        this.add(buttonPanel,BorderLayout.NORTH);
        this.add(mainPanel,BorderLayout.CENTER);
        this.setVisible(true);
    }   
}

