/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics.capo;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JFrame;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * Contiene il modello della table menu ovvero prende i dati dal
 * database e ne costruisce una jtable. imposta anche le proprietà del modello
 * stesso. E' stato scelto un rowset invece che un resultset perchè il rowset
 * permette cambiamenti a runtime, notificando gli altri componenti di avvenuto
 * cambiamento. Sostanzialmente si comporta come un listmodel, contrariamente al
 * resultset che è statico.
 
 * @author Fabio
 */
public class MenuTabelModel implements TableModel{

    CachedRowSet menuRowSet; //contiene il contenuto della table impiegati SQL
    private final ResultSetMetaData metadata;
    int numcols, numrows;
    JFrame frame = new JFrame();

    public MenuTabelModel(CachedRowSet rowSetArg) throws SQLException {
        this.menuRowSet = rowSetArg;

        this.metadata = menuRowSet.getMetaData();
        numcols = metadata.getColumnCount();

        this.menuRowSet.beforeFirst();
        this.numrows = 0;
        while (this.menuRowSet.next()) {
            this.numrows++;
        }
        this.menuRowSet.beforeFirst();
    }
    @Override
    public int getRowCount() {
       return this.numrows;
    }

    @Override
    public int getColumnCount() {
         return this.numcols;
    }

    @Override
    public String getColumnName(int columnIndex) {
        try {
            return this.metadata.getColumnLabel(columnIndex + 1);
        } catch (SQLException ex) {
            return ex.toString();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
       return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            this.menuRowSet.absolute(rowIndex + 1);
            Object o = this.menuRowSet.getObject(columnIndex + 1);
            if (o == null) {
                return null;
            } else {
                return o.toString();
            }
        } catch (SQLException ex) {
            return ex.toString();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        
    }

    public CachedRowSet getMenuRowSet() {
        return menuRowSet;
    }
    /**
     * Aggiorna le righe della tabella menu
     * @param id id
     * @param name nome menuelement
     * @param description descrizione menuelement
     * @param price prezzo dell' elemento
     * @param Tipo tipo dell' elemento
     * @author FabioT
     */
    public void insertRow(int id, String name, String description, double price, String Tipo ) {
        try {
                        
            this.menuRowSet.moveToInsertRow();
            this.menuRowSet.updateInt("ID", id);
            this.menuRowSet.updateString("name", name);
            this.menuRowSet.updateString("description", description);
            this.menuRowSet.updateDouble("price", price);
            this.menuRowSet.updateString("Tipo", Tipo);
            
            this.menuRowSet.insertRow();
            this.menuRowSet.moveToCurrentRow();
        } catch (SQLException ex) {

            ex.toString();
        }
    }
    
    public void addEventHandlersToRowSet(RowSetListener listener) {
        this.menuRowSet.addRowSetListener(listener);
    }
    
    /**
     * chiude la connessione
     * @author Fabiot
     */
    public void close() {
        try {
            menuRowSet.getStatement().close();
        } catch (SQLException ex) {
            ex.toString();
        }
    }
}
