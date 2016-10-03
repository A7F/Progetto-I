package UIcook;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import restaurant.Order;
import restaurant.Restaurant;

/**
 * questa classe avvia la grafica del cuoco e ne imposta le proprietà
 * @author Luca
 */
public class CookUI implements Observer{
    
    JList list;
    ArrayList<Order> elements;
    int userId;
    DefaultListModel model=new DefaultListModel();
    JFrame frame = new JFrame("Cucina");
    Restaurant restaurant;
    JPanel descriptionPanel;
    JPanel ingredientsPanel;
    JPanel lowContainer = new JPanel(new GridLayout(2,1));
    JLabel noteLabel = new JLabel("NOTE >> ");
    JLabel ingredientiLabel = new JLabel("INGREDIENTI >> ");
    JLabel ingredientiText = new JLabel();
    JLabel noteText = new JLabel();
    
    public CookUI(Restaurant r,int userId){
        this.userId=userId;
        this.restaurant=r;
        restaurant.addObserver(this);
        elements=r.getOrdersArray();
        initUi();
    }

    private void initUi(){
        frame.setJMenuBar(new utils.MenuBar(frame,userId));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        for(int i=0;i<elements.size();i++){
            model.addElement(elements.get(i));
        }
        
        createLowerPanel();
        
        list =new JList(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayout(new FlowLayout());
        
        list.setCellRenderer(new CookListCellRender());
        list.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e){
                if(list.getSelectedIndex()==-1){
                    noteText.setText("Non hai selezionato niente");
                    ingredientiText.setText("Non hai selezionato niente");
                }else{
                    noteText.setText(elements.get(list.getSelectedIndex()).getNotes());
                    ingredientiText.setText(elements.get(list.getSelectedIndex()).getMenuElement().getDescription());
                }
            }
        });
        
        JScrollPane panel = new JScrollPane(list);
        panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        ButtonPanel buttonPanel = new ButtonPanel(elements,list);
        buttonPanel.setLayout(new FlowLayout());
        
        frame.add(buttonPanel,BorderLayout.NORTH);
        frame.add(panel,BorderLayout.CENTER);
        frame.add(lowContainer,BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }

    @Override
    public void update(Observable o, Object arg) {
        descriptionPanel.repaint();
        elements=restaurant.getOrdersArray();
        model.removeAllElements();
        for(int i=0;i<elements.size();i++){
            model.addElement(elements.get(i));
        }
        list.repaint();
    }

    private void createLowerPanel() {
        descriptionPanel = new JPanel(new FlowLayout());
        ingredientsPanel = new JPanel(new FlowLayout());
        descriptionPanel.add(noteLabel);
        descriptionPanel.add(noteText);
        ingredientsPanel.add(ingredientiLabel);
        ingredientsPanel.add(ingredientiText);
        lowContainer.add(descriptionPanel);
        lowContainer.add(ingredientsPanel);
    }

}