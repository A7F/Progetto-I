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
 *
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
        populateSouthPanel();
        setFrameProperties();
    }

    private void populateNorthPanel() {
        panelOrdersTable = new TablePanel(r);
        northPanel.add(panelOrdersTable.getPanel());
        northPanel.setPreferredSize(new Dimension(700,50));
    }

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
                    mySouthPanel.setJlabelText(scontrinoPanel.calculateCurrentTot());
                }
            }
        });
        
        JButton remOne = new JButton("<");
        remOne.setPreferredSize(new Dimension(50,40));
        remOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(scontrinoPanel.getSelectedIndex()==-1){
                    System.err.println("NULLA SELEZIONATO!");
                }else{
                    ordiniPanel.addOrder((Order)scontrinoPanel.getModel().getElementAt(scontrinoPanel.getSelectedIndex()));
                    scontrinoPanel.removeElement();
                    mySouthPanel.setJlabelText(scontrinoPanel.calculateCurrentTot());
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
                        ordiniPanel.removeOrder(i);
                    }                    
                    mySouthPanel.setJlabelText(scontrinoPanel.calculateCurrentTot());
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
                        scontrinoPanel.removeElement(i);
                    }
                    mySouthPanel.setJlabelText(scontrinoPanel.calculateCurrentTot());
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

    private void populateSouthPanel() {
        
    }

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