package UICameriere;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * barra inferiore in grafica cameriere che descrive lo stato dell' ordine selezionato
 * @author Luca
 * @see OrdersPanel
 */
public class FootBar extends JPanel{
    
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JLabel readLabel = new JLabel("LETTO: ");
    JLabel read = new JLabel("Nessuna selezione");
    JLabel doneLabel = new JLabel("FATTO: ");
    JLabel done = new JLabel("Nessuna selezione");
    
    public FootBar(){
        this.setLayout(new GridLayout(1,2));
        this.setBorder(new TitledBorder("Stato ordine"));
        init();
    }

    private void init() {
        readLabel.setForeground(Color.BLUE);
        readLabel.setFont(new Font("Calibri",Font.PLAIN,25));
        doneLabel.setForeground(Color.RED);
        doneLabel.setFont(new Font("Calibri",Font.PLAIN,25));
        p1.setLayout(new FlowLayout());
        p2.setLayout(new FlowLayout());
        p1.add(readLabel);
        p1.add(read);
        p2.add(doneLabel);
        p2.add(done);
        this.add(p1);
        this.add(p2);
    }
    
    public JLabel getReadLabel(){
        return this.read;
    }
    
    public JLabel getDoneLabel(){
        return this.done;
    }
}
