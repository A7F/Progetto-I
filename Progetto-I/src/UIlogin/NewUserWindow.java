package UIlogin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import utils.DatabaseManager;

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
    DatabaseManager mgr = new DatabaseManager();
    
    public NewUserWindow(){
        initComponents();
    }
    
    private void initComponents(){
        frame.setLayout(new BorderLayout());
        frame.add(form,BorderLayout.CENTER);
        
        //TODO: aggiungere action listener dei bottoni modificando il costruttore
        
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                mgr.runUpdate("INSERT IGNORE INTO Impiegati(username,password) VALUES("+form.getUsernameInserted()+","+form.getPasswordInserted()+";");
                
            }
        });
        
        p1.add(b1);
        p1.add(b2);
        
        frame.add(p1,BorderLayout.SOUTH);
        
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
