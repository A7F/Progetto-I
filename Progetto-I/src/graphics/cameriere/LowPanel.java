package graphics.cameriere;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import restaurant.Order;
import restaurant.Restaurant;

/**
 * questa classe è il pannello inferiore della grafica del cameriere. Contiene campo per note, i due pulsanti
 * ADD e REMOVE, lo spinner per aggiungere più ordini uguali.
 * @author Luca
 */
public class LowPanel extends JPanel{
    
    Restaurant restaurant;
    JPanel elementsPanel=new JPanel();
    JPanel editElementsPanel=new JPanel();
    JPanel infoElementMenu=new JPanel();
    JSpinner spinner;
    MenuPanel menuPanel;
    OrdersPanel ordersPanel;
    JTextField textNotes;
    
    public LowPanel(Restaurant res,MenuPanel menu, OrdersPanel orders){
        this.restaurant=res;
        this.menuPanel=menu;
        this.ordersPanel=orders;
        this.setLayout(new FlowLayout());
        init();
    }
    
    /**
     * avvio della creazione del pannello basso della grafica cameriere
     * @author Luca
     */
    private void init(){
        JButton addButton = new JButton("ADD");
        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int count = (int)spinner.getValue();
                if(menuPanel.getSelectedIndex()==-1){
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,"Nessun elemento menu selezionato!","Errore",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                for(int i=0;i<count;i++){
                    Order newOrder = new Order(1,menuPanel.getSelectedElement(),textNotes.getText());
                    ordersPanel.addOrder(newOrder);
                }
                ordersPanel.getTablePanel().repaint();
                restaurant.notifyCook();
            }
        });
        
        JButton remButton = new JButton("REM");
        remButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(ordersPanel.getSelectedIndex()==-1){
                    JOptionPane.showMessageDialog(new JFrame(),"Nessun elemento ordine selezionato!","Errore",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!(restaurant.getOrdersArray().get(ordersPanel.getSelectedIndex()).getDone())){
                    ordersPanel.getTablePanel().repaint();
                    ordersPanel.removeOrder();
                }else{
                    JOptionPane.showMessageDialog(new JFrame(),"Non è possibile rimuovere un ordine già evaso","Errore",JOptionPane.ERROR_MESSAGE);
                }
                
                restaurant.notifyCook();
            }
        });
        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1,1,30,1);
        spinner = new JSpinner(spinnerModel);
        spinner.setFont(new Font("Calibri",Font.ITALIC,25));
        textNotes = new JTextField("Ancora nessuna nota qui...");
        textNotes.setColumns(20);
        textNotes.setFont(new Font("Calibri",Font.PLAIN,20));
        textNotes.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                textNotes.setText("");
            }
        });
        
        elementsPanel.setLayout(new FlowLayout());
        elementsPanel.add(addButton);
        elementsPanel.add(remButton);
        
        editElementsPanel.setLayout(new FlowLayout());
        editElementsPanel.add(spinner);
        editElementsPanel.add(textNotes);
        
        this.add(elementsPanel);
        this.add(editElementsPanel);
    }

    public JPanel getElementsPanel() {
        return elementsPanel;
    }

    public JPanel getEditElementsPanel() {
        return editElementsPanel;
    }

    public JSpinner getSpinner() {
        return spinner;
    }
        
}
