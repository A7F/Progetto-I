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
    
    RadioPanel refRadioPanel;
    FormPanel refFormPanel;
    LoginManager mgr;
    
    protected ButtonLayout(RadioPanel pane,FormPanel pane2, LoginManager manager){
        refRadioPanel=pane;
        refFormPanel=pane2;
        this.mgr = manager;
        this.setLayout(new FlowLayout());
        JButton b1 = new JButton("Login");
        b1.setSize(150, 100);
        
        b1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("SELECTED: "+refRadioPanel.getSelectedButton());
                    boolean status = mgr.checkCredentials(refFormPanel.getUsernameInserted(), refFormPanel.getPasswordInserted(), refRadioPanel.getSelectedButton());

                    mgr.graphicsDispatcher(status, (int)mgr.getSelectedKey(refRadioPanel.getSelectedButton()));
                } catch (SQLException ex) {
                    System.err.println("36: ECCEZIONE SQL NON RACCOLTA DALLA CLASSE IN CUI VIENE LANCIATA");
                }
            }
        });
        
        JButton b2 = new JButton("Nuovo");
        b2.setSize(150, 100);
        
        b2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int var = mgr.getSelectedKey(refRadioPanel.getSelectedButton());
                boolean flag = false;
                
                try {
                    flag = mgr.checkCredentials(refFormPanel.getUsernameInserted(), refFormPanel.getPasswordInserted(), refRadioPanel.getSelectedButton());
                } catch (SQLException ex) {
                    System.out.println("54: ECCEZIONE SQL NON RACCOLTA DALLA CLASSE IN CUI VIENE LANCIATA");
                }
                
                if(var==4){
                    if(flag){
                        System.out.println("FAI PARTIRE LA GRAFICA");
                        NewUserWindow win = new NewUserWindow();    //credenziali confermate quindi lancia utility aggiunta user al database
                    }
                }else{
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,"Questa funzione è disponibile solo al principale","Info",JOptionPane.INFORMATION_MESSAGE);
                    refFormPanel.wipePassword();
                }
            }
        });
        
        this.add(b1);
        this.add(b2);
    }
}
