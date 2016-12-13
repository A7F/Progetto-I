/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIcassa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Seleziona il tipo di sconto da effettuare
 * @author Fabio
 */
public class DiscountMenu extends JMenuBar{
    
    private ProxyDiscount contextDiscount;

    public DiscountMenu(ProxyDiscount contextDiscount) {
        this.contextDiscount = contextDiscount;
        init();
    }
    
    private void init(){
    
        JMenu menuDiscount = new JMenu("Sconto");
        
        JMenuItem scontoPercentuale = new JMenuItem("Sconto percentuale sul totale");
        menuDiscount.add(scontoPercentuale);
               
        impostaSconto(scontoPercentuale, new PercentDiscount());
        
        add(menuDiscount);
    }
    
    private void impostaSconto(JMenuItem menuItem, final IDiscountStrategy discounStrategy){
    
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contextDiscount.setDiscountStrategy(discounStrategy);
            }
        });
    }
}
