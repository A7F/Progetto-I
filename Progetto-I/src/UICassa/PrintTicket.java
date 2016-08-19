package UICassa;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import utils.Restaurant;

/**
 *
 * @author Fabio
 */
public class PrintTicket extends JPanel{
    
    private final Restaurant restaurant;
    private PanelTable panelTable;
    private JButton button;
    private JTextArea areaStampa;
    

    public PrintTicket(Restaurant restaurant ,PanelTable panelTable) {
        this.restaurant = restaurant;
        this.panelTable = panelTable;
        initComponent();
    }
    
    private void initComponent(){
    
        areaStampa= new JTextArea();
        JScrollPane jsp = new JScrollPane(areaStampa);
        jsp.setPreferredSize(new Dimension(300, 200));
        
        button = new JButton("Stampa scontrino");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                              
                int nTable = panelTable.getTableNumber();
                if(nTable !=0){
                     areaStampa.setText(restaurant.getTicket(nTable).toString());     
                }else{
                    areaStampa.setText("ATTENZIONE DEVI PRIMA SELEZIONARE UN TAVOLO");
                }
            }
        });
        
        this.setLayout(new BorderLayout()); 
        this.add(button,BorderLayout.NORTH);
        this.add(jsp,BorderLayout.CENTER);

    }
}