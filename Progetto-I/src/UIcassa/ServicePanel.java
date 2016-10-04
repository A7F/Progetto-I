package UIcassa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Luca
 */
public class ServicePanel extends JPanel{
    
    ScontrinoPanel scontrinoPanel;
    GridLayout myLayout = new GridLayout(2,1);
    JPanel buttonsPanel;
    JPanel totPanel = new JPanel(new BorderLayout());   //così posso centrare la label nel quadrato
    JLabel total = new JLabel("NA");
    JPanel cassaPanel = new JPanel(new BorderLayout());
    JTextArea cassa = new JTextArea("In attesa di stampa...");
    
    public ServicePanel(ScontrinoPanel scontrinoPanel){
        this.scontrinoPanel = scontrinoPanel;
        GridLayout mainGridLayout = new GridLayout(1,3);
        mainGridLayout.setHgap(20);
        this.setLayout(mainGridLayout);
        init();
    }

    private void init() {
        myLayout.setHgap(3);
        myLayout.setVgap(3);
        buttonsPanel = new JPanel(myLayout);
        totPanel.setBorder(new TitledBorder("TOT"));
        total.setFont(new Font("Verdana",Font.ITALIC,27));
        totPanel.add(total,BorderLayout.CENTER);
        cassa.setEditable(false);
        cassaPanel.setBorder(new TitledBorder("Output"));
        cassaPanel.add(cassa,BorderLayout.CENTER);
        
        JButton stampaButton = new JButton("STAMPA");
        stampaButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String total = scontrinoPanel.calculateCurrentTot();
                String ticket = scontrinoPanel.getScontrinoElements();
                cassa.setText(ticket+"\n"+total);
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
    
    public void setJlabelText(String text){
        total.setText(text);
    }
}
