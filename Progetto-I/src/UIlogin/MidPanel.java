/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIlogin;

import java.awt.*;
import javax.swing.*;

/**
 *
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
    
    
    public RadioPanel getRadioPanel(){
        return this.pradio;
    }
    
    public FormPanel getFormPanel(){
        return this.form;
    }
}
