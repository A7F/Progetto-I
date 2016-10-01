/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UICassa;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import restaurant.Restaurant;

/**
 * Gestisce la prenotazione di un tavolo: riserva o libera il tavolo
 * @author Fabio
 */
public class TableReservation extends JPanel{
    
    private Restaurant restaurant;
    private PanelTable panelTable;
    private JButton buttonReservedTable;
    private JButton buttonFreeTable;

    public TableReservation(Restaurant restaurant, PanelTable panelTable) {
        this.restaurant = restaurant;
        this.panelTable = panelTable;
        initComponent();
    }

    private void initComponent(){
    
        buttonReservedTable = new JButton("Prenota tavolo");
        buttonReservedTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nTable = panelTable.getTableNumber();
                if(nTable !=0){
                    restaurant.setReserved(nTable);
                    //DEBUG
                    //System.out.println("tavolo prenotato:" + restaurant.getFreeTable());
                }else{
                    System.err.println("ATTENZIONE DEVI PRIMA SELEZIONARE UN TAVOLO");    
                }
            }
        });
        
        buttonFreeTable = new JButton("Libera Tavolo");
        buttonFreeTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nTable = panelTable.getTableNumber();
                if(nTable !=0){
                    restaurant.setFreeTable(nTable);  
                    //DEBUG
                    //System.out.println("tavolo prenotato:" + restaurant.getFreeTable());
                }else{
                    System.err.println("ATTENZIONE DEVI PRIMA SELEZIONARE UN TAVOLO");    
                }
            }
        });
        this.setLayout(new GridLayout(1, 2));
        this.add(buttonReservedTable);
        this.add(buttonFreeTable);
    }
    
    
}
