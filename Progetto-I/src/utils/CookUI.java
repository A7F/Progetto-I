package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 * UI per il cuoco.
 * @author Luca
 */
public class CookUI implements ActionListener{
    
    private JButton jButton1;
    private JButton jButton2;
    private JList jList1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private ArrayList<Order> orders;
    private JFrame f1;
    
    public CookUI(ArrayList<Order> orders){
        //ordersText = new ArrayList<String>();
        this.orders = orders;
        //ordersToStrings(orders);
        initUi();
    }
    
     private ArrayList<String> ordersToStrings(ArrayList<Order> orders){
        ArrayList<String> s = new ArrayList<String>();
        for(int i=0;i<orders.size();i++){
            s.add(orders.get(i).getNameEl());
        }
        return s;
    }

    private void initUi() {
        f1 = new JFrame("Cucina");
        jPanel1 = new JPanel();
        jButton2 = new JButton();
        jButton1 = new JButton();
        jScrollPane1 = new JScrollPane();
        jList1 = new JList(ordersToStrings(orders).toArray());
        jPanel1.setLayout(new java.awt.GridLayout());
        
        //pulsantiera in alto
        jButton1.setText("FATTO");
        jButton1.setFont(new java.awt.Font("Dialog", 1, 20));
        jButton2.setText("LETTO");
        jButton2.setFont(new java.awt.Font("Dialog", 1, 20));
        jPanel1.add(jButton1);
        jPanel1.add(jButton2);
        
        //lista centrale
        jList1.setCellRenderer(new MyListCellRender());
        jList1.setFont(new java.awt.Font("Dialog", 1, 25));
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);
        
        //JFrame, aggiungo elementi sopra e imposto le proprietà
        f1.setLayout(new BorderLayout());
        f1.add(jPanel1,BorderLayout.NORTH);
        f1.add(jScrollPane1,BorderLayout.CENTER);
        f1.setVisible(true);
        f1.setSize(800, 500);
        f1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //JButtons: aggancio gli action listeners
        jButton1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                jButton1ActionPerformed(evt);
            }
        });
        
        jButton2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                jButton2ActionPerformed(evt);
            }
        });
    }
    
    /**
     * a seguire, i due listener per i pulsanti.
     * @author Luca
     * @param evt 
     */
    private void jButton1ActionPerformed(ActionEvent evt){
        if(jList1.isSelectionEmpty()){
            System.out.println("NESSUN ELEMENTO SELEZIONATO: NON POSSO CAMBIARE IL COLORE!");
        }
        int val = jList1.getSelectedIndex();
        jList1.setSelectedIndex(val);
        //jList1.setForeground(Color.RED);
    }
    
    private void jButton2ActionPerformed(ActionEvent evt){
        if(jList1.isSelectionEmpty()){
            System.out.println("NESSUN ELEMENTO SELEZIONATO: NON POSSO CAMBIARE IL COLORE!");
        }
        int val = jList1.getSelectedIndex();
        jList1.setSelectedIndex(val);
        jList1.isSelectedIndex(val);
        //jList1.setForeground(Color.GREEN);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}   //questo boh, netbeans rompeva perchè doveva esserci per forza quindi ok...

}
