package UIcapo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;
import restaurant.Restaurant;
import utils.MenuBar;

/**
 *
 * @author Luca
 */
public class CapoUI {
    private CustomFrame frame=new CustomFrame("Pannello di gestione");
    final JTabbedPane tabbedPane = new JTabbedPane();
    final JPanel panelEmp = new JPanel();
    final JPanel panelMenu = new JPanel();
    final JPanel panelSconti = new JPanel();
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    FunctionalPanel p1;
    FunctionalPanel p2;
    int userId;
    private DiscountPage discountPage;
    private Restaurant restaurant;

    public CapoUI(Restaurant restaurant,int userId) {
        this.userId = userId;
        this.restaurant = restaurant;
        initGraphics();
    }
    
    public CapoUI(int userId){
        this.userId=userId;
        initGraphics();
    }

    /**
     * inizializza gli elementi grafici da disporre all' interno del customFrame
     * @author Luca
     */
    private void initGraphics(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        TableModelBuilder employeesModel = new TableModelBuilder("Impiegati");
        TableModelBuilder menuModel = new TableModelBuilder("Menu");       
        
        try {
            panelEmp.add(employeesModel.getPanelModel());
            panelMenu.add(menuModel.getPanelModel());
            tabbedPane.addTab("Impiegati", panelEmp);
            tabbedPane.addTab("Menu", panelMenu);
            tabbedPane.addTab("Sconti", panelSconti);
        } catch (SQLException ex) {
            Logger.getLogger(CapoUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        p1=new FunctionalPanel(employeesModel,"impiegati");
        p2=new FunctionalPanel(menuModel,"menu");
        discountPage = new DiscountPage(restaurant);
        
        panelEmp.add(p1);
        panelMenu.add(p2);
        panelSconti.setLayout(new BorderLayout());
        panelSconti.add(discountPage,BorderLayout.NORTH);
        
        frame.add(tabbedPane);
        frame.pack();
        frame.setJMenuBar(new MenuBar(frame,userId));
        frame.setVisible(true);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }
    
}
