package graphics.login;

import java.awt.*;
import javax.swing.*;

/**
 * pannello di form per il login che contiene i campi login e password
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
    
    /**
     * metodo getter che ritorna il username digitato dall' utente
     * @return  username dell' utente
     * @author Luca
     */
    public String getUsernameInserted(){
        return userText.getText();
    }
    
    /**
     * metodo getter della password inserita dall' utente. Per questioni di sicurezza
     * viene distrutto l'array di char in input.
     * @return  password inserita da utente
     * @author Luca
     */
    public String getPasswordInserted(){
        char[] p = passwordText.getPassword();
        String str2 = new String(p);
        p=null; //questione di sicurezza: distruggo l'array di password
        return str2;
    }
    
    /**
     * questo metodo cancella il testo inserito come username
     * @author Luca
     */
    public void wipeUsername(){
        userText.setText("");
    }
    
    /**
     * questo metodo cancella la password inserita nella relativa textarea
     * @author Luca
     */
    public void wipePassword(){
        passwordText.setText("");
    }
}
