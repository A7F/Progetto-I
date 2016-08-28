package UIcapo;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.sql.SQLException;
import javax.swing.JTabbedPane;

/**
 *
 * @author Luca
 */
public class CapoUI {
    JFrame frame = new JFrame("Amministrazione");
    final JTabbedPane tabbedPane = new JTabbedPane();
    final JPanel panelEmp = new JPanel();
    final JPanel panelMenu = new JPanel();
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    
    public CapoUI(){
        initGraphics();
    }

    private void initGraphics() {
        TableModelBuilder employeesModel = new TableModelBuilder("Impiegati");
        TableModelBuilder menuModel = new TableModelBuilder("Menu");
        
        try {
            panelEmp.add(employeesModel.getPanelModel());
            panelEmp.add(new FunctionalPanel(employeesModel,"impiegati"));
            panelMenu.add(menuModel.getPanelModel());
            panelMenu.add(new FunctionalPanel(menuModel,"menu"));
        } catch (SQLException ex) {
            ex.toString();
        }
        
        tabbedPane.addTab("Impiegati", panelEmp);
        tabbedPane.addTab("Menu", panelMenu);
        
        frame.add(tabbedPane);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);
    }
    
    
    
}
