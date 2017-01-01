package utils.commonGraphics;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Luca
 */
public class MyFileChooser extends JFileChooser{
    
    private int selected;
    
    public MyFileChooser(){
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File di testo", "txt", "text");
        this.setFileFilter(filter);
    }
    
    public void show(){
        selected = this.showDialog(new JFrame(),"Scegli menu");
    }
    
    public int getSelected(){
        return this.selected;
    }
}
