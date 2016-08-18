package UIlogin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import utils.LoginManager;

/**
 *
 * @author Luca
 */
class ButtonLayout extends JPanel{
    
    RadioPanel refPanel;
    FormPanel refPanel2;
    LoginManager mgr = new LoginManager();
    
    protected ButtonLayout(RadioPanel pane,FormPanel pane2){
        refPanel=pane;
        this.setLayout(new FlowLayout());
        JButton b1 = new JButton("Login");
        b1.setSize(150, 100);
        
        b1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("SELECTED: "+refPanel.getSelectedButton());
                    boolean status = mgr.checkCredentials(refPanel2.getUsernameInserted(), refPanel2.getPasswordInserted(), refPanel.getSelectedButton());
                    mgr.graphicsDispatcher(status, mgr.getSelectedKey(refPanel.getSelectedButton()));
                } catch (SQLException ex) {
                    System.err.println("ECCEZIONE SQL");
                }
            }
        });
        
        JButton b2 = new JButton("Nuovo");
        b2.setSize(150, 100);
        
        b2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int var = mgr.getSelectedKey(refPanel.getSelectedButton());
                boolean flag = false;
                
                try {
                    flag = mgr.checkCredentials(refPanel2.getUsernameInserted(), refPanel2.getPasswordInserted(), refPanel.getSelectedButton());
                } catch (SQLException ex) {
                    System.out.println("ERRORE QUERY");
                }
                
                if(var==4){
                    if(flag){
                        System.out.println("FAI PARTIRE LA GRAFICA");
                        NewUserWindow win = new NewUserWindow();    //credenziali confermate quindi lancia utility aggiunta user al database
                    }else{
                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame,"Username o Password errata","Info",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,"Questa funzione è disponibile solo al principale","Info",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        this.add(b1);
        this.add(b2);
    }
}
