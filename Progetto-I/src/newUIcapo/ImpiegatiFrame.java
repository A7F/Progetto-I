package newUIcapo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.spi.SyncProviderException;
import javax.swing.*;
import utils.ImpiegatiManager;

/**
 *
 * @author Luca
 */
public class ImpiegatiFrame extends JFrame implements RowSetListener{
    ImpiegatiTableModel myImpiegatiTableModel;
    ImpiegatiManager empManager = new ImpiegatiManager();
    JTable table;
    
    JLabel label_EM_USERNAME,label_EM_ID,label_EM_PASSWORD,label_STATUS,label_RUOLO;
    JTextField textField_EM_USERNAME,textField_EM_ID,textField_EM_PASSWORD,textField_STATUS,textField_RUOLO;
    JButton button_ADD_ROW,button_UPDATE_DATABASE,button_DISCARD_CHANGES;

    public ImpiegatiFrame(){
        super("Prova");
        try {
            CachedRowSet myCachedRowset = empManager.getImpiegatiResultSet();
            myImpiegatiTableModel = new ImpiegatiTableModel(myCachedRowset);
            myImpiegatiTableModel.addEventHandlersToRowSet(this);
            table = new JTable();
            table.setModel(myImpiegatiTableModel);
        } catch (SQLException ex) {
            Logger.getLogger(ImpiegatiFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        initGraphics();
    }
    
    @Override
    public void rowSetChanged(RowSetEvent event) {}

    @Override
    public void rowChanged(RowSetEvent event) {
        CachedRowSet currentRowSet = this.myImpiegatiTableModel.impiegatiRowSet;
        try {
            currentRowSet.moveToCurrentRow();
            myImpiegatiTableModel = new ImpiegatiTableModel(myImpiegatiTableModel.getImpiegatiRowSet());
            table.setModel(myImpiegatiTableModel);
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
        myImpiegatiTableModel = new ImpiegatiTableModel(empManager.getImpiegatiResultSet());
        myImpiegatiTableModel.addEventHandlersToRowSet(this);
        table.setModel(myImpiegatiTableModel);
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
        button_UPDATE_DATABASE = new JButton();
        button_DISCARD_CHANGES = new JButton();

        label_EM_USERNAME.setText("Username impiegato:");
        label_EM_ID.setText("ID:");
        label_EM_PASSWORD.setText("Password:");
        label_STATUS.setText("Stato:");
        label_RUOLO.setText("Ruolo:");

        textField_EM_USERNAME.setText("Inserisci uno username...");
        textField_EM_ID.setText("101");
        textField_EM_PASSWORD.setText("Inserisci una password...");
        textField_STATUS.setText("false");
        textField_RUOLO.setText("Specifica un ruolo...");

        button_ADD_ROW.setText("Aggiungi riga");
        button_UPDATE_DATABASE.setText("Invia cambiamenti");
        button_DISCARD_CHANGES.setText("Cancella cambiamenti");
        
        Container contentPane = getContentPane();
        contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.setContentPane(contentPane);

        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        contentPane.add(new JScrollPane(table), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        contentPane.add(label_EM_USERNAME, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.75;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        contentPane.add(textField_EM_USERNAME, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.25;
        c.weighty = 0;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        contentPane.add(label_EM_ID, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.75;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        contentPane.add(textField_EM_ID, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        contentPane.add(label_EM_PASSWORD, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.75;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        contentPane.add(textField_EM_PASSWORD, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        contentPane.add(label_STATUS, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.75;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        contentPane.add(textField_STATUS, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        contentPane.add(label_RUOLO, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.75;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        contentPane.add(textField_RUOLO, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        contentPane.add(button_ADD_ROW, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 1;
        contentPane.add(button_UPDATE_DATABASE, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 1;
        contentPane.add(button_DISCARD_CHANGES, c);
        
        button_ADD_ROW.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("click add");
                myImpiegatiTableModel.insertRow(Integer.parseInt(textField_EM_ID.getText().trim()),
                                                textField_EM_USERNAME.getText(),
                                                textField_EM_PASSWORD.getText(),
                                                textField_RUOLO.getText(),
                                                Boolean.parseBoolean(textField_STATUS.getText().trim()));
            }
        });
        
        
        //invia i cambiamenti e ridisegna la jtable
        button_UPDATE_DATABASE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    myImpiegatiTableModel.impiegatiRowSet.acceptChanges();
                } catch (SyncProviderException ex) {
                    ex.toString();
                }
                try {
                    createNewTableModel();
                } catch (SQLException ex) {
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
