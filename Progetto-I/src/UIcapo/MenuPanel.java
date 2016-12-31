/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIcapo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import utils.MenuManager;

/**
 * Pannello contenente il tabella del menu, i pulsanti di aggiunta rimozione e aggiornento database
 * @author Fabio
 */
public class MenuPanel extends JPanel implements RowSetListener {

    private MenuTabelModel myMenuTabelModel;
    MenuManager menuManager;
    JTable table;
    Connection conn;
    
    JLabel label_MENU_ID, label_MENU_NAME,label_MENU_DESCRIPTION,label_MENU_PRICE,label_MENU_TYPE;
    JTextField textField_MENU_ID,textField_MENU_NAME,textFieldMENU_DESCRIPTION,textField_MENU_PRICE,textField_MENU_TYPE;
    JButton button_ADD_ROW, button_REMOVE_ROW,button_UPDATE_DATABASE,button_DISCARD_CHANGES;

    public MenuPanel() {
         this.menuManager = new MenuManager();
        try {
        //    conn = empManager.getConnection();
            CachedRowSet myCachedRowset = menuManager.getMenuResultSet();
            myMenuTabelModel = new MenuTabelModel(myCachedRowset);
            myMenuTabelModel.addEventHandlersToRowSet(this);
            table = new JTable();
            table.setModel(myMenuTabelModel);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        initGraphics();
    }
    
    
    @Override
    public void rowSetChanged(RowSetEvent event) {
        
    }

    @Override
    public void rowChanged(RowSetEvent event) {
         CachedRowSet currentRowSet = this.myMenuTabelModel.menuRowSet;
        try {
            currentRowSet.moveToCurrentRow();
            myMenuTabelModel = new MenuTabelModel(myMenuTabelModel.getMenuRowSet());
            table.setModel(myMenuTabelModel);
        } catch (SQLException ex) {
            System.out.println("Qui non funziona qualcosa...");
        }
    }

    @Override
    public void cursorMoved(RowSetEvent event) {
       
    }
    
     /**
     * metodo usato per ricreare la JTable
     * @throws SQLException 
     * @author Luca
     */
    private void createNewTableModel() throws SQLException {
        myMenuTabelModel = new MenuTabelModel(menuManager.getMenuResultSet());
        myMenuTabelModel.addEventHandlersToRowSet(this);
        table.setModel(myMenuTabelModel);
    }
    
    
    private void initGraphics(){
    
        label_MENU_ID = new JLabel();
        label_MENU_NAME = new JLabel();
        label_MENU_DESCRIPTION = new JLabel();
        label_MENU_PRICE = new JLabel();
        label_MENU_TYPE = new JLabel();

        textField_MENU_ID = new JTextField(10);
        textField_MENU_NAME = new JTextField(10);
        textFieldMENU_DESCRIPTION = new JTextField(10);
        textField_MENU_PRICE = new JTextField(10);
        textField_MENU_TYPE = new JTextField(10);

        button_ADD_ROW = new JButton();
        button_REMOVE_ROW = new JButton();
        button_UPDATE_DATABASE = new JButton();
        button_DISCARD_CHANGES = new JButton();

        label_MENU_ID.setText("ID:");
        label_MENU_NAME.setText("Nome elemento:");
        label_MENU_DESCRIPTION.setText("Descrizione");
        label_MENU_PRICE.setText("Prezzo:");
        label_MENU_TYPE.setText("Tipo:");

//        textField_MENU_ID.setText("101");
//        textField_MENU_NAME.setText("Inserisci un nome...");
//        textFieldMENU_DESCRIPTION.setText("Inserisci una descrizione...");
//        textField_MENU_PRICE.setText("Prezzo");
//        textField_MENU_TYPE.setText("Tipo");

        button_ADD_ROW.setText("Aggiungi elemento");
        button_REMOVE_ROW.setText("Rimuovi elemento");
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
        this.add(label_MENU_ID, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.75;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        this.add(textField_MENU_ID, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.25;
        c.weighty = 0;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        this.add(label_MENU_NAME, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.75;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        this.add(textField_MENU_NAME, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        this.add(label_MENU_DESCRIPTION, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.75;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        this.add(textFieldMENU_DESCRIPTION, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        this.add(label_MENU_PRICE, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.75;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        this.add(textField_MENU_PRICE, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        this.add(label_MENU_TYPE, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.75;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        this.add(textField_MENU_TYPE, c);

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
                try {
                    double d = Double.parseDouble(textField_MENU_PRICE.getText());

                    if (textField_MENU_ID.getText().isEmpty() || !textField_MENU_NAME.getText().isEmpty()
                            || !textFieldMENU_DESCRIPTION.getText().isEmpty() || !textField_MENU_PRICE.getText().isEmpty()
                            || !textField_MENU_TYPE.getText().isEmpty()) {

                        insertMenuTable();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Compilare tutti i campi", "Warning", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Dati non inseriti correttamente", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
          
        button_REMOVE_ROW.addActionListener(new ActionListener() {                    
            @Override
            public void actionPerformed(ActionEvent e) {
                menuManager.removeMenuElement(Integer.parseInt(textField_MENU_ID.getText().trim()));
            }
        });
        
        //invia i cambiamenti e ridisegna la jtable
        button_UPDATE_DATABASE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    myMenuTabelModel.menuRowSet.acceptChanges(menuManager.getConnection());
                    JOptionPane.showMessageDialog(new JFrame(),"Database aggiornato!","Informazione",JOptionPane.INFORMATION_MESSAGE);
                } catch (SyncProviderException ex) {
                    JOptionPane.showMessageDialog(new JFrame(),"Database NON aggiornato!","Errore",JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(new JFrame(),"Impossibile annullare le ultime modifiche","Warning",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }    

    private void insertMenuTable() {
        if (menuManager.checkMenuElementById(Integer.parseInt(textField_MENU_ID.getText().trim())) == false) {
            
            myMenuTabelModel.insertRow(Integer.parseInt(textField_MENU_ID.getText().trim()),
                    textField_MENU_NAME.getText(), textFieldMENU_DESCRIPTION.getText(),
                    Double.parseDouble(textField_MENU_PRICE.getText().trim()),textField_MENU_TYPE.getText().trim());
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Id già esistente", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
