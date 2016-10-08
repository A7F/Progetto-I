package UIlogin;

import java.awt.*;
import javax.swing.*;

/**
 * pannello centrale che contiene le due textfield ed i radiobuttons per la login
 * @see RadioPanel
 * @see FormPanel
 * @author Luca
 */
class MidPanel extends JPanel{
    
    RadioPanel pradio = new RadioPanel();
    FormPanel form = new FormPanel();
    
    protected MidPanel(){
        this.setLayout(new GridLayout(2,1));
        this.add(form);
        this.add(pradio);
    }
    
    /**
     * metodo getter per il radio panel
     * @return radio panel
     * @author Luca
     */
    public RadioPanel getRadioPanel(){
        return this.pradio;
    }
    
    /**
     * metodo getter per il formpanel contenente i campi di immissione testo
     * @return form panel
     * @author Luca
     */
    public FormPanel getFormPanel(){
        return this.form;
    }
}
