/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIcook;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextArea;
import utils.Cook;

/**
 * Pannello centrale contenente l'elenco degli ordini
 * @author fabio
 */
public class MainPanel extends JTextArea implements Observer{
    
    private Cook cook;
    
    public MainPanel(Cook cook) {
        this.cook = cook;
        initComponent();
    }

    private void initComponent(){
    
        this.setText(cook.getOrdersCopy().toString());
        cook.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setText(cook.getOrdersCopy().toString());
    }
    
    
}   
