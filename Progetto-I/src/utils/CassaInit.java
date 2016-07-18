package utils;

import javax.swing.JFrame;
import static javax.swing.JFrame.*;

/**
 * questa classe apre un jframe con dentro il jpanel custom della cassa.
 * imposta anche le relative proprietà.
 * @author Luca
 */
public class CassaInit {
    public CassaInit(){
        CassaUI u = new CassaUI();
        JFrame f = new JFrame();
        f.add(u);
        f.setResizable(false);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}
