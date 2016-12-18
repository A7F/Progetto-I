package UIlogin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import utils.LoginManager;

/**
 * questa classe crea i due pulsanti Login e Nuovo, con relativo listener
 * @author Luca
 */
class ButtonLayout extends JPanel{
    
    RadioPanel refRadioPanel;
    FormPanel refFormPanel;
    LoginManager mgr;
    private boolean status = false;
    
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
                    status = mgr.checkCredentials(refFormPanel.getUsernameInserted(), refFormPanel.getPasswordInserted(), refRadioPanel.getSelectedButton());

                    mgr.graphicsDispatcher(status, (int)mgr.getSelectedKey(refRadioPanel.getSelectedButton()));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(new JFrame(),"Username, password o ruolo errato.","Errore",JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(new JFrame(),"Username, password o ruolo errato.","Errore",JOptionPane.ERROR_MESSAGE);
                }
                
                if(var==4){
                    if(flag){
                        NewUserWindow win = NewUserWindow.getInstance();    //credenziali confermate quindi lancia utility aggiunta user al database
                    }
                }else{
                    JOptionPane.showMessageDialog(new JFrame(),"Questa funzione è disponibile solo al principale","Info",JOptionPane.INFORMATION_MESSAGE);
                    refFormPanel.wipePassword();
                }
            }
        });
        
        this.add(b1);
        this.add(b2);
    }
    
    /**
     * metodo get: ottiene lo status della login, se è true è avvenuta con successo.
     * @return  stato di login: true se avvenuto con successo.
     * @author Luca
     */
    protected boolean getFlag(){
        return this.status;
    }
}
