/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UICameriere;

import UIcapo.buttonLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import restaurant.Restaurant;

/**
 *
 * @author Fabio
 */
public class TablePanel extends JPanel{
    
    private ArrayList<JButton> tableButtons;
    private Restaurant restaurant;
    private int nTable;
    private int selectedTable;

    
    public TablePanel(int nTables) {
        this.nTable = nTables;
        tableButtons = new ArrayList<>();
        initComponent();
        
    }
    
    private void initComponent(){
    
        for (int i = 0; i < nTable; i++) {

            JButton button = new JButton(String.valueOf(i + 1));

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    if(selectedTable != 0){
                        tableButtons.get(selectedTable - 1).setBackground(null);
                    }
                    
                    JButton button = (JButton)e.getSource();
                    selectedTable = Integer.parseInt(button.getText());
                    button.setBackground(Color.red);
                }
            });

            tableButtons.add(button);
            this.add(button);
        }
    }

    public int getSelectedTable() {
        return selectedTable;
    }
    
}
