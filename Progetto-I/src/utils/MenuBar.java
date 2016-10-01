package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * questa è la menubar in cui si può fare logout. Disponibile in tutte le finestre.
 * @author Luca
 */
public class MenuBar extends JMenuBar{
    JMenu menu = new JMenu("File");
    
    public MenuBar(){
        JMenuItem menuItem = new JMenuItem("Logout",new ImageIcon("data/icons/logout32x32.png"));
        menuItem.setMnemonic(KeyEvent.VK_L);
        menuItem.addActionListener(new LogoutActionListener());
        menu.add(menuItem);
        this.add(menu);
    }

    private static class LogoutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("gestisci qui il logout!");
        }
    }
}
