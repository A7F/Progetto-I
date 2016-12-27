package UIlogin;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

/**
 * questa classe è un pannello di radiobuttons fini alla gestione della login corretta per
 * l'utente corretto: i radiobuttons, oltre a controllare che un utente non stia tentando di
 * accedere con le credenziali (username, password) corrette alla interfaccia sbagliata, istanziano
 * la grafica corretta passando il valore selezionato al LoginManager.
 * @see utils.LoginManager
 * @author Luca
 */
class RadioPanel extends JPanel{
    
    JRadioButton r1 = new JRadioButton("CASSA");
    JRadioButton r2 = new JRadioButton("CUOCO");
    JRadioButton r3 = new JRadioButton("CAMERIERE");
    JRadioButton r4 = new JRadioButton("CAPO");
    ButtonGroup group = new ButtonGroup();
    
    
    protected RadioPanel(){
        this.setLayout(new FlowLayout());
        group.add(r1);
        group.add(r2);
        group.add(r3);
        group.add(r4);
        
        this.add(r1);
        this.add(r2);
        this.add(r3);
        this.add(r4);
        r1.setSelected(true);
    }
    
    /**
     * getter per ottenere il nome del radiobutton selezionato
     * @return  etichetta del radiobutton scelto
     * @author Luca
     */
    public String getSelectedButton(){
        String name = null;
        
        Enumeration<AbstractButton> rgroup=group.getElements();  
        
        while(rgroup.hasMoreElements()){  
            JRadioButton temp=(JRadioButton)rgroup.nextElement();  
            if(temp.isSelected()){  
                name = temp.getText();  
            }  
        }
        
        return name;
    }
}
