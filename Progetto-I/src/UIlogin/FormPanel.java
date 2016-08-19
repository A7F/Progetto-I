package UIlogin;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Luca
 */
class FormPanel extends JPanel{
    
    JLabel userLabel = new JLabel("User");
    JTextField userText = new JTextField(20);
    JLabel passwordLabel = new JLabel("Password");
    JPasswordField passwordText = new JPasswordField(20);
    JPanel wrapperPanel = new JPanel();
    
    protected FormPanel(){
        this.setLayout(new BorderLayout());
        
        wrapperPanel.setLayout(null);
        userLabel.setBounds(10, 10, 80, 25);
        wrapperPanel.add(userLabel);
        
        userText.setBounds(100, 10, 160, 25);
        wrapperPanel.add(userText);
        
        passwordLabel.setBounds(10, 40, 80, 25);
        wrapperPanel.add(passwordLabel);
        
        passwordText.setBounds(100, 40, 160, 25);
        wrapperPanel.add(passwordText);
        
        this.add(wrapperPanel,BorderLayout.CENTER);
    }
    
    public String getUsernameInserted(){
        return userText.getText();
    }
    
    public String getPasswordInserted(){
        char[] p = passwordText.getPassword();
        String str2 = new String(p);
        p=null; //questione di sicurezza: distruggo l'array di password
        return str2;
    }
    
    public void wipeUsername(){
        userText.setText("");
    }
    
    public void wipePassword(){
        passwordText.setText("");
    }
}
