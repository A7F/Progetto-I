package UIcapo;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import restaurant.Restaurant;

/**
 * Pannello per applicare lo sconto al ristorante
 * @author Fabio
 */
public class DiscountPage extends JPanel{
    
    private Restaurant restaurant;
    private JButton discountButton;
    private DiscountPanel discountPanel;

    public DiscountPage(Restaurant restaurant) {
        this.restaurant = restaurant;
        initComponent();
    }
    
    private void initComponent(){
        
        discountPanel = new DiscountPanel();
        
        discountButton  = new JButton("APPLICA");
        discountButton.setSize(new Dimension(100,100));
        
        discountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurant.getDiscount().setAttributes((Integer)discountPanel.getSpinnerDiscount(), 0);
            }
        });
        
        GridLayout myLayout = new GridLayout(2,1);
        myLayout.setHgap(20);
        myLayout.setVgap(20);
        
        this.setLayout(myLayout);
        this.setSize(new Dimension(200,200));
        this.add(discountPanel);
        this.add(discountButton);
        
    }
}
