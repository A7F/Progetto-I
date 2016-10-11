package tests;

import UIcapo.CapoUI;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import newUIcapo.ImpiegatiFrame;

/**
 * classe di test per la grafica del capo
 * @see CapoUI
 * @author Luca
 */
public class TestCapoUI {
    public static void main(String[] args){
        //CapoUI c = new CapoUI(0);
        ImpiegatiFrame fr = new ImpiegatiFrame();
        fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
        fr.pack();
        fr.setVisible(true);
    }
}
