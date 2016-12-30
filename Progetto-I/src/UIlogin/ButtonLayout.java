package UIlogin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import utils.LoginManager;

/**
 * Questa classe crea il pulsante Login, con relativo listener
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
                
        this.add(b1);
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
