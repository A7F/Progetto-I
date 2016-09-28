package UICameriere;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import restaurant.Restaurant;

/**
 *Pannello contenente uno lista di bottoni corrispondenti ai tavoli presenti 
 *nel ristorante
 * @author Fabio
 */
public class TablePanel{
    
    private ArrayList<JButton> tableButtons;
    private restaurant.Restaurant restaurant;
    private int selectedTable;
    private JPanel panel;
    
    public TablePanel(Restaurant restaurant) {
        this.restaurant = restaurant;
        tableButtons = new ArrayList<>();
        panel= new JPanel();
        initComponent();
    }
    
    private void initComponent(){
    
        for (int i = 0; i < restaurant.getTables().size(); i++) {

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
                    
                    //è grazie a questo metodo se viene notificato tutto, chiama il notify in getOrderTable! 
                    //(forse è meglio cambiarlo? Per adesso non ci disturba, fa da debug. Poi chiameremo il notify in modo più carino...)
                    System.out.println("tavolo: "+selectedTable+"\tOrdini: "+restaurant.getOrderTable(selectedTable).toString());
                    
                }
            });

            tableButtons.add(button);
            panel.add(button);
        }
    }

    /**
     * Ottieni il tavolo selezionato dalla pulsantiera. Di default ritorna il tavolo 1.
     * @author Luca
     * @return selectedTable
     */
    public int getSelectedTable() {
        if(selectedTable==0){
            selectedTable=1;
        }
        return selectedTable;
    }  

    public JPanel getPanel() {
        return panel;
    }
}
