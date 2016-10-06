/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIcapo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import restaurant.Restaurant;

/**
 * Pannello per applicare lo sconto al ristorante
 * @author Fabio
 */
public class DiscountPage extends JPanel{
    
    private Restaurant restaurant;
    private JButton discountButton;
    private DiscountPanel discountPanel;

    public DiscountPage(Restaurant restaurant) {
        this.restaurant = restaurant;
        initComponent();
    }
    
    private void initComponent(){
        
        discountPanel = new DiscountPanel();
        
        discountButton  = new JButton("APPLICA");
        discountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurant.setDiscount((Integer)discountPanel.getSpinnerDiscount());
                // DEBUG 
                //System.err.println("sconto in ristorante: " + restaurant.getDiscount());
            }
        });
        
        this.setLayout(new BorderLayout());
        this.add(discountPanel,BorderLayout.CENTER);
        this.add(discountButton,BorderLayout.SOUTH);
    }
}
