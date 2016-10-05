package UIlogin;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

/**
 *
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
