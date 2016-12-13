package UIcassa;

import utils.TablePanel;
import UICameriere.*;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import restaurant.Order;
import restaurant.Restaurant;

/**
 * Contiene lo scontrino
 * @author Luca
 */
public class ScontrinoPanel extends JPanel{

    private TablePanel tablePanel;
    private Restaurant restaurant;
    private ArrayList<Order> scontrino;
    JScrollPane pane;
    DefaultListModel model=new DefaultListModel();
    private JList list;
    int selectedIndex;    

    public ScontrinoPanel(TablePanel tablePanel, Restaurant restaurant) {
        this.setPreferredSize(new Dimension(300,240));
        this.tablePanel = tablePanel;
        this.restaurant = restaurant;
        this.setBorder(new TitledBorder("Scontrino"));
        init();
    }

    /**
     * metodo per inizializzare la grafica del pannello dello scontrino
     * @author Luca
     */
    private void init(){
        
        list=new JList(model);
        
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pane = new JScrollPane(list);
        pane.setPreferredSize(new Dimension(290,200));
        this.add(pane);
    }
    
    /**
     * metodo per rimuovere dal modello della lista, l'elemento all' indice selezionato
     * @author Luca
     */
    public void removeElement(){
        model.remove(list.getSelectedIndex());
    }
    
    /**
     * metodo per rimuovere dal modello della lista, l'elemento all' indice specificato
     * @author Luca
     * @param index indice del valore che si desidera rimuovere
     */
     public void removeElement(int index){
        model.remove(index);
    }
     
    /**
     * metodo per rimuovere velocemente tutti gli elementi dalla lista dello scontrino
     * @author Luca
     */
    public void removeAllElement(){
        model.removeAllElements();
    }
    
    /**
     * metodo per aggiungere l'ordine selezionato negli ordini allo scontrino
     * @see OrdiniPanel
     * @author Luca
     * @param ord l'ordine da aggiungere
     */
    public void addElement(Order ord){
        model.addElement(ord);
    }
    
    /**
     * metodo get per ottenere l'indice selezionato in lista scontrino
     * @return indice selezionato
     * @author Luca
     */
    public int getSelectedListIndex(){
        return list.getSelectedIndex();
    }
    
    /**
     * metodo get per ottenere il numero del tasto tavolo selezionato
     * @return numero del tavolo selezionato
     * @author Luca
     */
    public int getSelectedIndex(){
        return this.selectedIndex;
    }
    
    /**
     * metodo per controllare se il tavolo selezionato non possiede ordini
     * @return false se contiene ordini
     * @author Luca
     */
    public boolean getIsEmptyTable(){
        return restaurant.getTables().get(selectedIndex).getOrdersArray().isEmpty();
    }
    
    /**
     * metodo getter per ottenere il ristorante
     * @return ristorante
     * @author Luca
     */
    public Restaurant getRestaurant(){
        return this.restaurant;
    }
    
    /**
     * questo metodo calcola il totale di tutti gli elementi inseriti in scontrino
     * @return il totale già convertito in strings
     * @author Luca
     * @see ServicePanel
     */
    public String calculateCurrentTot(){
        double tot = 0;
        int discount = restaurant.getDiscount();
        for(int i=0;i<model.size();i++){
            Order ord = (Order) model.getElementAt(i);
            double price = ord.getPrice();
            tot = tot+price;
        }
        return String.valueOf(tot+" $");
    }
    
    /**
     * questo metodo calcola il totale considerando anche lo sconto del ristorante.
     * Lo sconto viene applicato sul totale.
     * @author Luca
     * @return totale con sconto applicato
     */
    public String calculateTotWithDiscount(){
        double tot = 0;
        int discount = restaurant.getDiscount();
        for(int i=0;i<model.size();i++){
            Order ord = (Order) model.getElementAt(i);
            double price = ord.getPrice();
            tot = tot+price;
        }
        tot = tot-(tot*discount/100);
        return String.valueOf(tot+" $");
    }
    
    /**
     * metodo per ottenere lo sconto da applicare sul totale
     * @see Restaurant
     * @return lo sconto da applicare, convertito in stringa
     * @author Luca
     */
    public String getRestaurantDiscount(){
        return String.valueOf(restaurant.getDiscount());
    }
    
    /**
     * metodo getter del modello implementato nella lista.
     * @return modello della JList
     * @author Luca
     */
    public DefaultListModel getModel(){
        return this.model;
    }
    
    /**
     * metodo che prepara il layout del totale dello scontrino concatenando le informazioni dei
     * vari ordini, letti tramite ciclo for.
     * @return il testo pronto per essere stampato
     */
    public String getScontrinoElements(){
        StringBuilder str = new StringBuilder();
        for(int i=0;i<model.size();i++){
            Order ord = (Order) model.getElementAt(i);
            str.append(ord.getNameEl()).append("\t").append(String.valueOf(ord.getPrice())).append("\n");
        }
        return str.toString();
    }
}
