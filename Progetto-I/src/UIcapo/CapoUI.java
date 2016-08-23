package UIcapo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComponent;
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
    
    public CapoUI(){
        initGraphics();
    }

    private void initGraphics() {
        TableModelBuilder employeesModel = new TableModelBuilder("Impiegati");
        TableModelBuilder menuModel = new TableModelBuilder("Menu");
        
        try {
            panelEmp.add(employeesModel.getPanelModel());
            panelMenu.add(menuModel.getPanelModel());
        } catch (SQLException ex) {
            System.out.println("Qualcosa non va con SQL");
        }
        
        tabbedPane.addTab("Impiegati", panelEmp);
        tabbedPane.addTab("Menu", panelMenu);
        
        frame.add(tabbedPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
}
