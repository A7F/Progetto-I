/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIlogin;

import java.awt.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * questa classe è un pannello con cui il gestore può aggiungere nuovi impiegati al database
 * @author Luca
 */
class NewUserWindow {
    FormPanel form = new FormPanel();
    JFrame frame = new JFrame("Nuovo Utente");
    JButton b1 = new JButton("Inserisci");
    JButton b2 = new JButton("Annulla");
    JPanel p1 = new JPanel(new FlowLayout());
    
    public NewUserWindow(){
        initComponents();
    }
    
    private void initComponents(){
        frame.setLayout(new BorderLayout());
        frame.add(form,BorderLayout.CENTER);
        
        //TODO: aggiungere action listener dei bottoni modificando il costruttore
        
        p1.add(b1);
        p1.add(b2);
        
        frame.add(p1,BorderLayout.SOUTH);
        
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
