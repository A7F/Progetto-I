package UICameriere;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * barra inferiore in grafica cameriere che descrive lo stato dell' ordine selezionato
 * @author Luca
 */
public class FootBar extends JPanel{
    
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JLabel readLabel = new JLabel("READ: ");
    JLabel read = new JLabel("Nessuna selezione");
    JLabel doneLabel = new JLabel("DONE: ");
    JLabel done = new JLabel("Nessuna selezione");
    
    public FootBar(){
        this.setLayout(new GridLayout(1,2));
        init();
    }

    private void init() {
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
