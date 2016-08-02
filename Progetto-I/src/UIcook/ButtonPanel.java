/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIcook;

import java.awt.GridLayout;
import javax.swing.JPanel;
import utils.Cook;
import utils.Order;

/**
 * Pannello contenente i vari bottoni della parte superiore del pannello
 * @author fabio
 */
public class ButtonPanel extends JPanel{

    private Cook cook;
    private Order order;

    public ButtonPanel(Cook cook, Order order) {
        this.cook = cook;
        this.order = order;
        initComponent();
    }
    
    private void initComponent(){
    
        ButtonDone buttonDone = new ButtonDone(cook,order);
        ButtonRead buttonRead = new ButtonRead();
        this.setLayout(new GridLayout(1, 2));
        this.add(buttonDone);
        this.add(buttonRead);
        this.setVisible(true);
    }
}
