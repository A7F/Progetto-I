package UIcook;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import restaurant.Order;

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