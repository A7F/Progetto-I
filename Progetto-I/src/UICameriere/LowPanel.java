package UICameriere;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import restaurant.Restaurant;

/**
 *
 * @author Luca
 */
public class LowPanel extends JPanel{
    
    Restaurant restaurant;
    JPanel elementsPanel=new JPanel();
    JPanel editElementsPanel=new JPanel();
    JSpinner spinner;
    MenuPanel menuPanel;
    OrdersPanel ordersPanel;
    
    public LowPanel(Restaurant res,MenuPanel menu, OrdersPanel orders){
        this.restaurant=res;
        this.menuPanel=menu;
        this.ordersPanel=orders;
        this.setLayout(new FlowLayout());
        create();
    }
    
    public void create(){
        JButton addButton = new JButton("ADD");
        addButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
            }
        });
        
        JButton remButton = new JButton("REM");
        remButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ordersPanel.removeOrder();
            }
        });
        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1,1,30,1);
        spinner = new JSpinner(spinnerModel);
        final JTextField textNotes = new JTextField("Insert notes here...     ");
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
