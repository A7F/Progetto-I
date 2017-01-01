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
    CookListCellRender myRender = new CookListCellRender();

    
    public CookUI(Restaurant r,int userId){
        this.userId=userId;
        this.restaurant=r;
        restaurant.addObserver(this);
        elements=r.getOrdersArray();
        initUi();
    }

    /**
     * metodo per avviare la grafica del cuoco
     * @author Luca
     */
    private void initUi(){
        frame.setJMenuBar(new utils.commonGraphics.MenuBar(frame,userId,restaurant));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        for(int i=0;i<elements.size();i++){
            model.addElement(elements.get(i));
        }
        
        createLowerPanel();
        
        list =new JList(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayout(new FlowLayout());
        
        list.setCellRenderer(myRender);
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

    /**
     * pattern observer: questa classe è observer di ristorante. Ogni volta che ristorante
     * notifica cambamenti, viene chiamato questo metodo per aggiornare la lista delle cose da cucinare.
     * @author Luca
     * @see Restaurant
     * @param o Observable
     * @param arg Object
     */
    @Override
    public void update(Observable o, Object arg) {
        
        descriptionPanel.repaint();
        elements=restaurant.getOrdersArray();
        model.removeAllElements();
        for(int i=0;i<elements.size();i++){
            model.addElement(elements.get(i));
        }
        myRender.repaint();
        list.repaint();
    }

    /**
     * metodo per creare il pannello inferiore contenente descrizione ordine ed eventuali note
     * @author Luca
     */
    private void createLowerPanel() {
        descriptionPanel = new JPanel(new FlowLayout());
        ingredientsPanel = new JPanel(new FlowLayout());
        noteLabel.setFont(new Font("Calibri",Font.ITALIC,21));
        noteText.setFont(new Font("Calibri",Font.PLAIN,19));
        ingredientiLabel.setFont(new Font("Calibri",Font.ITALIC,21));
        ingredientiText.setFont(new Font("Calibri",Font.PLAIN,19));
        descriptionPanel.add(noteLabel);
        descriptionPanel.add(noteText);
        ingredientsPanel.add(ingredientiLabel);
        ingredientsPanel.add(ingredientiText);
        lowContainer.add(descriptionPanel);
        lowContainer.add(ingredientsPanel);
    }

}