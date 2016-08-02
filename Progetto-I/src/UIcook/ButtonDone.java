/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIcook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import utils.Cook;
import utils.Order;

/**
 * Bottone "letto"
 * @author fabio
 */
public class ButtonDone extends JPanel {

    private Cook cook;
    private Order order;

    public ButtonDone(final Cook cook, final Order order) {
        this.cook = cook;
        this.order = order;
        
        JButton setDoneButton = new JButton("FATTO");
        setDoneButton.setFont(new java.awt.Font("Dialog", 1, 20));

        setDoneButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cook.setDone(order);
            }
        });

        this.add(setDoneButton);
    }
    
}
