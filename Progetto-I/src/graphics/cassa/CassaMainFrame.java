package graphics.cassa;

import graphics.commons.TablePanel;
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
import graphics.commons.MenuBar;

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
        MenuBar cassaMenuBar = new MenuBar(mainframe,userId,r);
        cassaMenuBar.addTableEntry(r);
        mainframe.setJMenuBar(cassaMenuBar);
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
                    JOptionPane.showMessageDialog(new JFrame(), "Nessun elemento selezionato!", "Warning", JOptionPane.WARNING_MESSAGE);
                }else{
                    scontrinoPanel.addElement((Order)ordiniPanel.getModel().getElementAt(ordiniPanel.getSelectedIndex()));
                    ordiniPanel.removeOrder();
                    setPreviewTotal(scontrinoPanel, mySouthPanel);
                }
            }
        });
        
        JButton remOne = new JButton("<");
        remOne.setPreferredSize(new Dimension(50,40));
        remOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (scontrinoPanel.getSelectedListIndex() == -1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Nessun elemento selezionato!", "Warning", JOptionPane.WARNING_MESSAGE);                   
                } else{
                    ordiniPanel.addOrder((Order)scontrinoPanel.getModel().getElementAt(scontrinoPanel.getSelectedListIndex()));
                    scontrinoPanel.removeElement();
                    setPreviewTotal(scontrinoPanel, mySouthPanel);
                }
            }
        });
        
        JButton addAll = new JButton(">>");
        addAll.setPreferredSize(new Dimension(50,40));
        addAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(ordiniPanel.getModel().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Pannello già vuoto!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else{
                    for(int i=0;i<ordiniPanel.getModel().size();i++){
                        scontrinoPanel.addElement((Order)ordiniPanel.getModel().getElementAt(i));
                    }    
                    ordiniPanel.removeAllOrders();
                    setPreviewTotal(scontrinoPanel, mySouthPanel);
                }
            }
        });
        
        JButton remAll = new JButton("<<");
        remAll.setPreferredSize(new Dimension(50,40));
        remAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(scontrinoPanel.getModel().isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(), "Pannello già vuoto!", "Warning", JOptionPane.WARNING_MESSAGE);
                }else{
                    for(int i=0;i<scontrinoPanel.getModel().size();i++){
                        ordiniPanel.addOrder((Order)scontrinoPanel.getModel().getElementAt(i));
                    }
                    scontrinoPanel.removeAllElement();
                    setPreviewTotal(scontrinoPanel, mySouthPanel);
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
    
    private void setPreviewTotal(ScontrinoPanel scontrinoPanel, ServicePanel mySouthPanel){
    
        mySouthPanel.setJlabelTotText(scontrinoPanel.calculateCurrentTotString());
        mySouthPanel.setJlabelTotDiscText(scontrinoPanel.calculateTotWithDiscount());
        mySouthPanel.setJlabelDiscountText(scontrinoPanel.getRestaurantDiscount());  
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
