<<<<<<< HEAD
package UIcook;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import utils.Order;

class ButtonPanel extends JPanel{
    
    ArrayList<Order> ord;
    JList list;
    
    public ButtonPanel(ArrayList<Order> orders,JList referencedList) {
        ord=orders;
        list=referencedList;
        this.setLayout(new FlowLayout());
        JButton b1 = new JButton("Done");
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int i=list.getSelectedIndex();
                ord.get(i).changeDone();
                System.out.println("KEY:\tDONE\tROW:"+i+"\tDONE:"+ord.get(i).getDone()+"\tREAD:"+ord.get(i).getRead());
                list.repaint();
            }
        });
        
        JButton b2 = new JButton("Read");
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int i=list.getSelectedIndex();
                ord.get(i).changeRead();
                System.out.println("KEY:\tREAD\tROW:"+i+"\tDONE:"+ord.get(i).getDone()+"\tREAD:"+ord.get(i).getRead());
                list.repaint();
            }
        });
        
        this.add(b1);
        this.add(b2);
    }
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIcook;

import java.awt.GridLayout;
import javax.swing.JPanel;
import utils.Cook;
import utils.Order;

/**
 * Pannello contenente i vari bottoni della parte superiore del pannello
 * @author fabio
 */
public class ButtonPanel extends JPanel{

    private Cook cook;
    private Order order;

    public ButtonPanel(Cook cook, Order order) {
        this.cook = cook;
        this.order = order;
        initComponent();
    }
    
    private void initComponent(){
    
        ButtonDone buttonDone = new ButtonDone(cook,order);
        ButtonRead buttonRead = new ButtonRead();
        this.setLayout(new GridLayout(1, 2));
        this.add(buttonDone);
        this.add(buttonRead);
        this.setVisible(true);
    }
}
>>>>>>> 45c89c034c0500c69020787f8ffcdd3a39eb9429
