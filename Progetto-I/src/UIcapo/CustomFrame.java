package UIcapo;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

/**
 *
 * @author Luca
 */
public class CustomFrame extends JFrame{
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    
    public CustomFrame(String name){
        this.setName(name);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        //this.setResizable(false);
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    
}
