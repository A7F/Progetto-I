/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newUIcapo;

import UIcapo.DiscountPage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import restaurant.Restaurant;
import utils.MenuBar;

/**
 * Avvia la grafica del capo e ne imposta le prorpietà
 * @author Fabio
 */
public class CapoUI extends JFrame{
    
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JPanel panelEmp = new JPanel();
    private JPanel panelMenu = new JPanel();
    private JPanel panelSconti = new JPanel();
    private Restaurant restaurant;
    private EmployeePanel employeePanel = new EmployeePanel();
    private MenuPanel menuPanel = new MenuPanel();
    private int userId;
    private DiscountPage discountPage;

        
    public CapoUI(Restaurant restaurant,int userId) {
        this.userId = userId;
        this.restaurant = restaurant;
        
        initGraphics();
    }
    
    public CapoUI(int userId){
        this.userId=userId;
        
        initGraphics();
    }
    
    private void initGraphics() {
        MenuBar capoMenuBar = new MenuBar(this,userId,restaurant);
        capoMenuBar.addDiscountMenu();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        panelEmp.add(employeePanel);
        tabbedPane.addTab("Impiegati", panelEmp);
        
        panelMenu.add(menuPanel);
        tabbedPane.addTab("Menu", panelMenu);
        
        discountPage = new DiscountPage(restaurant);      //eventuali sconti
        tabbedPane.addTab("Sconti", panelSconti);
        panelSconti.setLayout(new BorderLayout());
        panelSconti.add(discountPage,BorderLayout.NORTH);
        
        this.add(tabbedPane);
        this.setTitle("Pannello di gestione");
        this.setJMenuBar(capoMenuBar);
        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
}
