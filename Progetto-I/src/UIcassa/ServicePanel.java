package UIcassa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * pannello inferiore nella grafica di cassa contenente pulsanti stampa e ordinazioni,
 * jlabel del totale e area di output per la stampa dello scontrino
 * @author Luca
 */
public class ServicePanel extends JPanel{
    
    ScontrinoPanel scontrinoPanel;
    GridLayout myLayout = new GridLayout(2,1);
    JPanel buttonsPanel;
    JPanel totPanel = new JPanel(new BorderLayout());   //così posso centrare la label nel quadrato
    JLabel total = new JLabel("NA.");
    JLabel discount = new JLabel("- 0%");
    JLabel lastTotal = new JLabel("NA.");
    JPanel cassaPanel = new JPanel(new BorderLayout());
    JTextArea cassa = new JTextArea("In attesa di stampa...");
    JPanel newTotPanel = new JPanel();
    
    public ServicePanel(ScontrinoPanel scontrinoPanel){
        this.scontrinoPanel = scontrinoPanel;
        GridLayout mainGridLayout = new GridLayout(1,3);
        mainGridLayout.setHgap(20);
        this.setLayout(mainGridLayout);
        init();
    }

    /**
     * metodo per inizializzare la grafica della cucina
     * @author Luca
     */
    private void init() {
        myLayout.setHgap(3);
        myLayout.setVgap(3);
        buttonsPanel = new JPanel(myLayout);
        
        totPanel.setBorder(new TitledBorder("TOT"));
        total.setFont(new Font("Verdana",Font.ITALIC,25));
        
        discount.setFont(new Font("Verdana",Font.ITALIC,25));
        discount.setForeground(Color.RED);
        
        lastTotal.setFont(new Font("Verdana",Font.ITALIC,25));
        lastTotal.setForeground(Color.MAGENTA);
        
        totPanel.add(total,BorderLayout.NORTH);
        totPanel.add(discount,BorderLayout.CENTER);
        totPanel.add(lastTotal,BorderLayout.SOUTH);
        cassa.setEditable(false);
        cassaPanel.setBorder(new TitledBorder("Output"));
        cassaPanel.add(cassa,BorderLayout.CENTER);
        
        JButton stampaButton = new JButton("STAMPA");
        stampaButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(scontrinoPanel.getIsEmptyTable()){
                    scontrinoPanel.getRestaurant().setFreeTable(scontrinoPanel.getSelectedIndex()-1);
                }
                String total = scontrinoPanel.calculateCurrentTotString();
                String discount= scontrinoPanel.getRestaurantDiscount();
                String ticket = scontrinoPanel.getScontrinoElements();
                String totalDiscounted = scontrinoPanel.calculateTotWithDiscount();
               cassa.setText(ticket+"\n"+total + "\n" + discount + "\n" + totalDiscounted);
                scontrinoPanel.removeAllElement();
            }
        });
        
        JButton prenotaButton = new JButton("PRENOTAZIONI");
        prenotaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PrenotazioneWindow win = new PrenotazioneWindow();
            }
        });
        
        stampaButton.setPreferredSize(new Dimension(80,70));
        prenotaButton.setPreferredSize(new Dimension(80,70));
        buttonsPanel.add(stampaButton);
        buttonsPanel.add(prenotaButton);
        cassaPanel.setPreferredSize(new Dimension(200,70));
        
        
        this.add(buttonsPanel);
        this.add(totPanel);
        this.add(cassaPanel);
    }
    
    /**
     * metodo per modificare il testo della jLabel del totale
     * @param text il nuovo testo che assumerà la JLabel
     * @see CassaMainFrame
     * @author Luca
     */
    public void setJlabelTotText(String text){
        total.setText(text);
    }
    
    /**
     * metodo per modificare il testo della jLabel del totale con sconto
     * @param text il testo che assumerà la JLabel
     * @see CassaMainFrame
     * @author Luca
     */
    public void setJlabelDiscountText(String text){
        discount.setText(text);
    }
    
    /**
     * metodo per modificare il testo della jLabel dello sconto
     * @param text il testo che assumerà la JLabel
     * @see CassaMainFrame
     * @author Luca
     */
    public void setJlabelTotDiscText(String text){
        lastTotal.setText(text);
    }
    
}
