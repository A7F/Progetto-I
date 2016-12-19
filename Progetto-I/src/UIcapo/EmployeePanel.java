package UIcapo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.spi.SyncProviderException;
import javax.swing.*;
import utils.EmployeeManager;

/**
 *
 * @author Luca
 */
public class EmployeePanel extends JPanel implements RowSetListener{
    EmployeeTableModel myEmployeeTableModel;
    EmployeeManager empManager;
    JTable table;
    Connection conn;
    
    JLabel label_EM_USERNAME,label_EM_ID,label_EM_PASSWORD,label_STATUS,label_RUOLO;
    JTextField textField_EM_USERNAME,textField_EM_ID,textField_EM_PASSWORD,textField_STATUS,textField_RUOLO;
    JButton button_ADD_ROW, button_REMOVE_ROW,button_UPDATE_DATABASE,button_DISCARD_CHANGES;

    public EmployeePanel(){
        this.empManager = new EmployeeManager();
        try {
        //    conn = empManager.getConnection();
            CachedRowSet myCachedRowset = empManager.getEmployeeResultSet();
            myEmployeeTableModel = new EmployeeTableModel(myCachedRowset);
            myEmployeeTableModel.addEventHandlersToRowSet(this);
            table = new JTable();
            table.setModel(myEmployeeTableModel);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        initGraphics();
    }
    
    @Override
    public void rowSetChanged(RowSetEvent event) {}

    @Override
    public void rowChanged(RowSetEvent event) {
        CachedRowSet currentRowSet = this.myEmployeeTableModel.employeeRowSet;
        try {
            currentRowSet.moveToCurrentRow();
            myEmployeeTableModel = new EmployeeTableModel(myEmployeeTableModel.getEmployeeRowSet());
            table.setModel(myEmployeeTableModel);
        } catch (SQLException ex) {
            System.out.println("Qui non funziona qualcosa...");
        }
    }

    @Override
    public void cursorMoved(RowSetEvent event) {}
    
    /**
     * metodo usato per ricreare la JTable
     * @throws SQLException 
     * @author Luca
     */
    private void createNewTableModel() throws SQLException {
        myEmployeeTableModel = new EmployeeTableModel(empManager.getEmployeeResultSet());
        myEmployeeTableModel.addEventHandlersToRowSet(this);
        table.setModel(myEmployeeTableModel);
    }
    
    /**
     * metodo per disporre la grafica ed i relativi elementi
     * @author Luca
     */
    private void initGraphics(){
        label_EM_USERNAME = new JLabel();
        label_EM_ID = new JLabel();
        label_EM_PASSWORD = new JLabel();
        label_STATUS = new JLabel();
        label_RUOLO = new JLabel();

        textField_EM_USERNAME = new JTextField(10);
        textField_EM_ID = new JTextField(10);
        textField_EM_PASSWORD = new JTextField(10);
        textField_STATUS = new JTextField(10);
        textField_RUOLO = new JTextField(10);

        button_ADD_ROW = new JButton();
        button_REMOVE_ROW = new JButton();
        button_UPDATE_DATABASE = new JButton();
        button_DISCARD_CHANGES = new JButton();

        label_EM_ID.setText("ID:");
        label_EM_USERNAME.setText("Username impiegato:");
        label_EM_PASSWORD.setText("Password:");
        label_STATUS.setText("Stato:");
        label_RUOLO.setText("Ruolo:");

        textField_EM_ID.setText("101");
        textField_EM_USERNAME.setText("Inserisci uno username...");
        textField_EM_PASSWORD.setText("Inserisci una password...");
        textField_STATUS.setText("false");
        textField_RUOLO.setText("Specifica un ruolo...");

        button_ADD_ROW.setText("Aggiungi impiegato");
        button_REMOVE_ROW.setText("Rimuovi impiegato");
        button_UPDATE_DATABASE.setText("Invia cambiamenti al database");
        button_DISCARD_CHANGES.setText("Cancella cambiamenti");
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        this.add(new JScrollPane(table), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        this.add(label_EM_ID, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.75;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        this.add(textField_EM_ID, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.25;
        c.weighty = 0;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        this.add(label_EM_USERNAME, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.75;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        this.add(textField_EM_USERNAME, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        this.add(label_EM_PASSWORD, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.75;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        this.add(textField_EM_PASSWORD, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        this.add(label_STATUS, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.75;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        this.add(textField_STATUS, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        this.add(label_RUOLO, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.75;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        this.add(textField_RUOLO, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        this.add(button_ADD_ROW, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 1;
        this.add(button_REMOVE_ROW, c);
        

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 1;
        this.add(button_DISCARD_CHANGES, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 7;
        c.gridwidth = 1;
        this.add(button_UPDATE_DATABASE, c);
        
        button_ADD_ROW.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (empManager.getEmployeeById(Integer.parseInt(textField_EM_ID.getText().trim())) == null) {
                    myEmployeeTableModel.insertRow(Integer.parseInt(textField_EM_ID.getText().trim()),
                            textField_EM_USERNAME.getText(),
                            textField_EM_PASSWORD.getText(),
                            textField_RUOLO.getText(),
                            Boolean.parseBoolean(textField_STATUS.getText().trim()));
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Id già esistente", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        
        button_REMOVE_ROW.addActionListener(new ActionListener() {       // funziona va bene ma non aggiorna runtime la tabella
            @Override
            public void actionPerformed(ActionEvent e) {
                empManager.removeEmployee(Integer.parseInt(textField_EM_ID.getText().trim()));
            }
        });
        
        //invia i cambiamenti e ridisegna la jtable
        button_UPDATE_DATABASE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    myEmployeeTableModel.employeeRowSet.acceptChanges(empManager.getConnection());
                    System.out.println("DATABASE AGGIORNATO");
                } catch (SyncProviderException ex) {
                    ex.toString();
                }
            }
        });
        
        //ridisegna la jtable (senza inviare nulla) scartando quindi ogni modifica
        button_DISCARD_CHANGES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createNewTableModel();
                } catch (SQLException ex) {
                    ex.toString();
                }
            }
        });
    }
}
