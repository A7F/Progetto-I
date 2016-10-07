package UICameriere;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;
import restaurant.Restaurant;

/**
 * Pannello contenente uno lista di bottoni corrispondenti ai tavoli presenti nel ristorante e relativi listener.
 * @author Fabio
 */
public class TablePanel implements Observer{
    
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
    
    /**
     * metodo per avviare la grafica del cameriere. Inizializza i bottoni dei tavoli collegando i relativi listener.
     * @author Luca
     */
    private void initComponent(){
        restaurant.addObserver(this);
    
        for (int i = 0; i < restaurant.getTables().size(); i++) {

            JButton button = new JButton(String.valueOf(i + 1));
            if(restaurant.getTables().get(i).getIsTaken()){
                button.setBackground(Color.YELLOW);
            }else{
                button.setBackground(Color.GREEN);
            }
            
            if(!restaurant.getTables().get(i).getOrdersArray().isEmpty()){
                button.setBackground(Color.red);
            }

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    JButton button = (JButton)e.getSource();
                    selectedTable = Integer.parseInt(button.getText());
                    
                    restaurant.notifyAllObservers();
                }
            });

            tableButtons.add(button);
            panel.add(button);
        }
    }

    /**
     * Ottieni il tavolo selezionato dalla pulsantiera. Di default ritorna il tavolo 1.
     * @author Luca
     * @return il numero del tavolo selezionato. 1 se non è selezionato nulla.
     */
    public int getSelectedTable() {
        if(selectedTable==0){
            selectedTable=1;
        }
        return selectedTable;
    }  

    /**
     * metodo getter per ottenere il jpanel contenente i pulsanti dei tavoli. Non sarebbe servito
     * se la classe avesse esteso JPanel.
     * @return jpanel contenente i pulsanti dei tavoli
     * @author Luca
     * @see OrdersPanel
     */
    public JPanel getPanel(){
        return panel;
    }

    /**
     * metodo di update dell' observer: ogni volta che si registra un cambiamento in ristorante,
     * in particolare quando si aggiunge o toglie ordini, i pulsanti dei tavoli vengono ri-colorati
     * in base allo stato del tavolo stesso. I colori usati sono:
     * ROSSO, se il tavolo è occupato (ovvero ha ordini)
     * GIALLO, se il tavolo è prenotato (ma non ha ordini registrati)
     * VERDE, se il tavolo è libero.
     * @param o observable
     * @param arg object
     * @author Luca
     * @see Restaurant
     */
    @Override
    public void update(Observable o, Object arg) {
        if(restaurant.getTables().get(selectedTable-1).getIsTaken()){
            tableButtons.get(selectedTable-1).setBackground(Color.YELLOW);
            }else{
                tableButtons.get(selectedTable-1).setBackground(Color.GREEN);
            }
        if(!restaurant.getTables().get(selectedTable-1).getOrdersArray().isEmpty()){
            tableButtons.get(selectedTable-1).setBackground(Color.red);
        }
        tableButtons.get(selectedTable-1).repaint();
    }
   
}
