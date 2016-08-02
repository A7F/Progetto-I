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

/**
 *Bottone  "letto"
 * @author fabio
 */
public class ButtonRead extends JPanel{

    public ButtonRead() {
        JButton setReadButton = new JButton("LETTO");
         setReadButton.setFont(new java.awt.Font("Dialog", 1, 20));
    
        setReadButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        this.add(setReadButton);
    }
}
