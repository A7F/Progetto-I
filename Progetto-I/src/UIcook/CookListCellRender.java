package UIcook;

import java.awt.*;
import javax.swing.*;
import restaurant.Order;

/**
 * cell render della lista del cuoco. Segna di rosso gli ordini fatti, segna di verde gli
 * ordini solo letti.
 * @see CookUI
 * @author Luca
 */
class CookListCellRender extends JLabel implements ListCellRenderer<Order>{
    
    public CookListCellRender(){
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Order> list, Order value, int index, boolean isSelected, boolean cellHasFocus) {
        
        setText(value.toString());
        setFont(new Font("Arial", Font.ITALIC, 17));
        
        if (isSelected) {
            setBackground(Color.BLUE);
            setForeground(Color.BLUE);
        } else {
            setBackground(null);
            setForeground(null);
        }
        
        if(value.getRead()){
            setBackground(Color.GREEN);
        }else{
            setBackground(Color.WHITE);
        }
        
        if(value.getDone()){
            setBackground(Color.RED);
        }else{
            setBackground(Color.WHITE);
        }
        
        return this;
    }
}
