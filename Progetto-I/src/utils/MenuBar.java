package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * questa è la menubar in cui si può fare logout. Disponibile in tutte le finestre.
 * @author Luca
 */
public class MenuBar extends JMenuBar{
    JMenu menu = new JMenu("File");
    JFrame frameref;
    int userId;
    LoginManager mgr;
    
    public MenuBar(JFrame frame,int userId){
        this.frameref=frame;
        this.userId=userId;
        JMenuItem menuItem = new JMenuItem("Logout",new ImageIcon("data/icons/logout32x32.png"));
        menuItem.setMnemonic(KeyEvent.VK_L);
        menuItem.addActionListener(new LogoutActionListener());
        menu.add(menuItem);
        this.add(menu);
    }

    /**
     * classe privata di action listener custom che disconnette un utente dal database quando viene premuta
     * l'opzione logout della barra menu
     * @author Luca
     */
    private class LogoutActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            mgr=new LoginManager(userId);
            frameref.setVisible(false);
            mgr.disconnectUser(userId);
        }
    }
    
    /**
     * classe privata action listener custom che aggiunge un nuovo tavolo sul ristorante e sul database
     * @author Luca
     */
    private class AddTavoloActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("qui aggiunge un nuovo tavolo al ristorante così come al database");
        }
        
    }
    
    /**
     * classe privata action listener custom che rimuove un nuovo tavolo sul ristorante e sul database
     * @author Luca
     */
    private class RemTavoloActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("qui rimuove un nuovo tavolo sul ristorante così come sul database");
        }
    }
    
    /**
     * questo metodo serve per aggiungere all' occorrenza l'opzione aggiuntiva della barra
     * menu che permette di gestire l'aggiunta o la rimozione di tavoli. Questo è permesso alla cassa.
     * @see CassaMainFrame
     * @author Luca
     */
    public void addTableEntry(){
        JMenu menuTables = new JMenu("Tavoli");
        JMenuItem tavoliAddItem = new JMenuItem("Aggiungi tavolo");
        tavoliAddItem.addActionListener(new AddTavoloActionListener());
        JMenuItem tavoliRemItem = new JMenuItem("Rimuovi tavolo");
        tavoliRemItem.addActionListener(new RemTavoloActionListener());
        menuTables.add(tavoliAddItem);
        menuTables.add(tavoliRemItem);
        this.add(menuTables);
    }
}