package UICassa;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import restaurant.Restaurant;

/**
 * Gestisce i pulsanti dei tavoli
 * @author Fabio
 */
public class PanelTable extends JPanel{
    

    private Restaurant restaurant;
    private ArrayList<JButton> tableList = new ArrayList<>();
    private int selectedTable;

    public PanelTable(Restaurant restaurant) {
        this.restaurant = restaurant;       
        initComponent();
    }
    
    private void initComponent(){
            
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
        for (int i = 0; i < restaurant.getTables().size(); i++) {
            
            JButton b = new JButton("" + (i + 1));
            b.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    if(selectedTable != 0){
                        tableList.get(selectedTable - 1).setBackground(null);
                    }
                    
                    JButton b = (JButton)e.getSource();
                    selectedTable = Integer.parseInt(b.getText());
                    b.setBackground(Color.red);
                    restaurant.createTicket(selectedTable);
                }
            });
            
            tableList.add(b);
            c.gridx =i;
            c.gridy =0;
            c.gridwidth = 1;
            c.gridheight = 1;
            this.add(b,c);
        }
    }

    public int getTableNumber() {    
        return selectedTable;
    }
}
