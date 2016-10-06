package UIcassa;

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
    public int getSelectedIndex(){
        return list.getSelectedIndex();
    }
    
    /**
     * questo metodo calcola il totale di tutti gli elementi inseriti in scontrino
     * @return il totale già convertito in strings
     * @author Luca
     */
    public String calculateCurrentTot(){
        double tot = 0;
        for(int i=0;i<model.size();i++){
            Order ord = (Order) model.getElementAt(i);
            double price = ord.getPrice();
            tot = tot+price;
        }
        return String.valueOf(tot+" $");
    }
    
    /**
     * metodo getter del modello implementato nella lista.
     * @return model
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
