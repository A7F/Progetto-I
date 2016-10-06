package UIcapo;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;
import utils.MenuBar;

/**
 *
 * @author Luca
 */
public class CapoUI {
    private CustomFrame frame=new CustomFrame("gestione");
    final JTabbedPane tabbedPane = new JTabbedPane();
    final JPanel panelEmp = new JPanel();
    final JPanel panelMenu = new JPanel();
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private FunctionalPanel p1;
    private FunctionalPanel p2;
    private int userId;
    
    public CapoUI(int userId){
        this.userId=userId;
        initGraphics();
    }

    private void initGraphics(){
        TableModelBuilder employeesModel = new TableModelBuilder("Impiegati");
        TableModelBuilder menuModel = new TableModelBuilder("Menu");
        
        
        try {
            panelEmp.add(employeesModel.getPanelModel());
            panelMenu.add(menuModel.getPanelModel());
            tabbedPane.addTab("Impiegati", panelEmp);
            tabbedPane.addTab("Menu", panelMenu);
        } catch (SQLException ex) {
            Logger.getLogger(CapoUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        p1=new FunctionalPanel(employeesModel,"impiegati");
        p2=new FunctionalPanel(menuModel,"menu");
        panelEmp.add(p1);
        panelMenu.add(p2);
        
        frame.add(tabbedPane);
        frame.setJMenuBar(new MenuBar(frame,userId));
        frame.setVisible(true);
    }
    
}
