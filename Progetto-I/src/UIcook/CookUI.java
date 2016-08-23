package UIcook;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.JFrame.*;
import restaurant.Order;

/**
 * questa classe avvia la grafica del cuoco e ne imposta le proprietà
 * @author Luca
 */
public class CookUI {
    
    JList list;
    ArrayList<Order> elements;
    
    public CookUI(ArrayList<Order> el){
        elements=el;
        initUi();
    }

    private void initUi() {
        JFrame frame = new JFrame("Cucina");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        list =new JList(elements.toArray());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayout(new FlowLayout());
        list.setCellRenderer(new CookListCellRender());
        
        JScrollPane panel = new JScrollPane(list);
        panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        ButtonPanel buttonPanel = new ButtonPanel(elements,list);
        buttonPanel.setLayout(new FlowLayout());
        
        frame.add(buttonPanel,BorderLayout.NORTH);
        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}