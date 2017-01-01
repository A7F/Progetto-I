package graphics.cook;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import restaurant.Order;

/**
 * pannello di pulsanti "letto" e "fatto".
 * @author Luca
 */
class ButtonPanel extends JPanel{
    
    ArrayList<Order> ord;
    JList list;
    
    public ButtonPanel(ArrayList<Order> orders,JList referencedList) {
        ord=orders;
        list=referencedList;
        this.setLayout(new FlowLayout());
        JButton b1 = new JButton("FATTO");
        b1.setPreferredSize(new Dimension(90,50));
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int i=list.getSelectedIndex();
                try{
                    Order order = (Order)list.getModel().getElementAt(i);
                    order.changeDone();
                }catch(IndexOutOfBoundsException ex){
                    ex.toString();
                }
                list.repaint();
            }
        });
        
        JButton b2 = new JButton("LETTO");
        b2.setPreferredSize(new Dimension(90,50));
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int i=list.getSelectedIndex();
                ord.get(i).changeRead();
                list.repaint();
            }
        });
        
        this.add(b1);
        this.add(b2);
    }
}