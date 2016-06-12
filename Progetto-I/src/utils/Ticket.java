/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import menu.MenuElement;

/**
 * Gestisce la stampa dello scontrino
 * @author Fabio
 */
public class Ticket {

    HashMap<MenuElement, Integer> scontrino;

    public Ticket() {
        scontrino = new HashMap();
    }

    /**
     * Somma le quantità di ordini uguali
     * @author Fabio
     */
    public void elaboraTicket(ArrayList<Order> orders) {

        scontrino.clear();

        for (int i = 0; i < orders.size(); i++) {
            Order ordine = orders.get(i);

            if (scontrino.containsKey(ordine.getMenuElement())) {
                int qty = scontrino.get(ordine.getMenuElement()) + ordine.getQuantity();
                scontrino.put(ordine.getMenuElement(), qty);
            } else {
                scontrino.put(ordine.getMenuElement(), ordine.getQuantity());
            }
        }
    }
    
    /**
     *
     * @author Fabio
     */
    public HashMap<MenuElement, Integer> getScontrino() {
        return scontrino;
    }
    /**
     * Calcola il conto totale
     * @author Fabio
     */
    public double calcCheck() {

        double totalCheck = 0.0;

        for (Map.Entry<MenuElement, Integer> entry : scontrino.entrySet()) {
            MenuElement key = entry.getKey();
            Integer quantity = entry.getValue();

            totalCheck += quantity * key.getPrizeElement();
        }
        
        return totalCheck;
    }

    /**
    * Stampa scontrino
    * @author Fabio
    */
    @Override
    public String toString() {
        String string = new String();

        for (Map.Entry<MenuElement, Integer> entry : scontrino.entrySet()) {
            MenuElement key = entry.getKey();
            Integer quantity = entry.getValue();

            string += quantity + "\t* " + key.getPrizeElement() + "\t" + key.getNameElement() + "\t" + (key.getPrizeElement() * quantity) + "\n";
        }
        
        string += "--------------------------------------------\n";
        string += "TOTALE: \t " + calcCheck() + "\n";

        return string;
    }
}
