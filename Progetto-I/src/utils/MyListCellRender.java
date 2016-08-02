/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * questa classe ridefinisce il render della jlist estendendolo, così posso cambiare i colori
 * dei singoli elementi.
 * sono troppo avanti... niente da fare.
 * @author Luca
 */
public class MyListCellRender extends JLabel implements ListCellRenderer{
    
    ArrayList<Order> orders = new ArrayList<Order>();
    
    public MyListCellRender(ArrayList<Order> ord){
        setOpaque(true);
        this.orders=ord;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
        if(orders.get(index).getOrderDone()){
            System.out.println("SELEZIONATO: "+index+"\tSET: "+orders.get(index).toString());
            setBackground(isSelected ? Color.red : Color.white);
        }
        
        setBackground(isSelected ? Color.red : Color.white);
        setForeground(isSelected ? Color.white : Color.black);
        return this;
    }
    
}
