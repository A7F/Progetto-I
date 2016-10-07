package UIcassa;

import UICameriere.TablePanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import restaurant.Order;
import restaurant.Restaurant;

/**
 * grafica della cassa
 * @author Luca
 */
public class CassaMainFrame{
    public JFrame mainframe = new JFrame("Cassa");
    Restaurant r;
    int userId;
    JPanel northPanel = new JPanel(new FlowLayout());
    JPanel midPanel = new JPanel(new FlowLayout());
    JPanel southPanel = new JPanel();
    JPanel leftCenterPanel = new JPanel();
    TablePanel panelOrdersTable;
    
    public CassaMainFrame(Restaurant r, int userId){
        this.r=r;
        this.userId=userId;
        mainframe.setJMenuBar(new utils.MenuBar(mainframe,userId));
        populateNorthPanel();
        populateCenterPanel();
        setFrameProperties();
    }

    /**
     * questo metodo aggiunge a nord del borderlayout del frame principale la pulsantiera dei tavoli
     * @author Luca
     * @see TablePanel
     */
    private void populateNorthPanel() {
        panelOrdersTable = new TablePanel(r);
        northPanel.add(panelOrdersTable.getPanel());
        northPanel.setPreferredSize(new Dimension(700,50));
    }

    /**
     * questo metodo aggiunge al centro del borderlayout del frame principale una pulsantiera per aggiungere
     * o rimuovere uno o più ordini allo scontrino
     * @author Luca
     */
    private void populateCenterPanel() {
        final OrdiniPanel ordiniPanel = new OrdiniPanel(panelOrdersTable,r);
        final ScontrinoPanel scontrinoPanel = new ScontrinoPanel(panelOrdersTable,r);
        final ServicePanel mySouthPanel = new ServicePanel(scontrinoPanel);
        
        JButton addOne = new JButton(">");
        addOne.setPreferredSize(new Dimension(50,40));
        addOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(ordiniPanel.getSelectedIndex()==-1){
                    System.err.println("NULLA SELEZIONATO!");
                }else{
                    scontrinoPanel.addElement((Order)ordiniPanel.getModel().getElementAt(ordiniPanel.getSelectedIndex()));
                    ordiniPanel.removeOrder();
                    mySouthPanel.setJlabelTotText(scontrinoPanel.calculateCurrentTot());
                    mySouthPanel.setJlabelTotDiscText(scontrinoPanel.calculateTotWithDiscount());
                    mySouthPanel.setJlabelDiscountText("- "+scontrinoPanel.getRestaurantDiscount()+"%");
                }
            }
        });
        
        JButton remOne = new JButton("<");
        remOne.setPreferredSize(new Dimension(50,40));
        remOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(scontrinoPanel.getSelectedListIndex()==-1){
                    System.err.println("NULLA SELEZIONATO!");
                }else{
                    ordiniPanel.addOrder((Order)scontrinoPanel.getModel().getElementAt(scontrinoPanel.getSelectedListIndex()));
                    scontrinoPanel.removeElement();
                    mySouthPanel.setJlabelTotText(scontrinoPanel.calculateCurrentTot());
                    mySouthPanel.setJlabelTotDiscText(scontrinoPanel.calculateTotWithDiscount());
                    mySouthPanel.setJlabelDiscountText("- "+scontrinoPanel.getRestaurantDiscount()+"%");
                }
            }
        });
        
        JButton addAll = new JButton(">>");
        addAll.setPreferredSize(new Dimension(50,40));
        addAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(ordiniPanel.getModel().isEmpty()){
                    System.err.println("Vuoto!");
                }else{
                    for(int i=0;i<ordiniPanel.getModel().size();i++){
                        scontrinoPanel.addElement((Order)ordiniPanel.getModel().getElementAt(i));
                    }    
                    ordiniPanel.removeAllOrders();
                    mySouthPanel.setJlabelTotText(scontrinoPanel.calculateCurrentTot());
                    mySouthPanel.setJlabelTotDiscText(scontrinoPanel.calculateTotWithDiscount());
                    mySouthPanel.setJlabelDiscountText("- "+scontrinoPanel.getRestaurantDiscount()+"%");
                }
            }
        });
        
        JButton remAll = new JButton("<<");
        remAll.setPreferredSize(new Dimension(50,40));
        remAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(scontrinoPanel.getModel().isEmpty()){
                    System.err.println("Vuoto!");
                }else{
                    for(int i=0;i<scontrinoPanel.getModel().size();i++){
                        ordiniPanel.addOrder((Order)scontrinoPanel.getModel().getElementAt(i));
                    }
                    scontrinoPanel.removeAllElement();
                    mySouthPanel.setJlabelTotText(scontrinoPanel.calculateCurrentTot());
                    mySouthPanel.setJlabelTotDiscText(scontrinoPanel.calculateTotWithDiscount());
                    mySouthPanel.setJlabelDiscountText("- "+scontrinoPanel.getRestaurantDiscount()+"%");
                }
            }
        });
        
        GridLayout myLayout = new GridLayout(4,1);
        myLayout.setVgap(5);
        myLayout.setHgap(2);
        
        JPanel controlButtons = new JPanel(myLayout);
        controlButtons.add(addOne);
        controlButtons.add(addAll);
        controlButtons.add(remOne);
        controlButtons.add(remAll);
        
        midPanel.add(ordiniPanel);
        midPanel.add(controlButtons);
        midPanel.add(scontrinoPanel);
        midPanel.setPreferredSize(new Dimension(700,250));
        
        southPanel.add(mySouthPanel);
        southPanel.setPreferredSize(new Dimension(700,160));
    }

    /**
     * questo metodo imposta le proprietà del frame principale
     * @author Luca
     */
    private void setFrameProperties() {
        mainframe.setLayout(new BorderLayout());
        mainframe.add(northPanel,BorderLayout.NORTH);
        mainframe.add(midPanel,BorderLayout.CENTER);
        mainframe.add(southPanel,BorderLayout.SOUTH);
        mainframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainframe.pack();
        mainframe.setVisible(true);
    }
}
