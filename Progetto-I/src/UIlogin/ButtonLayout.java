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
                    mgr.graphicsDispatcher(status, mgr.checkSelection(refPanel.getSelectedButton()));
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
                System.out.println("CHECK SE MASTER");
            }
            
        });
        
        this.add(b1);
        this.add(b2);
    }
}
