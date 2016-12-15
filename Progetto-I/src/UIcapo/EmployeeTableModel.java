package UIcapo;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * questa classe è il modello della table impiegati ovvero prende i dati dal
 * database e ne costruisce una jtable. imposta anche le proprietà del modello
 * stesso. E' stato scelto un rowset invece che un resultset perchè il rowset
 * permette cambiamenti a runtime, notificando gli altri componenti di avvenuto
 * cambiamento. Sostanzialmente si comporta come un listmodel, contrariamente al
 * resultset che è statico.
 *
 * @author Luca
 */
public class EmployeeTableModel implements TableModel {

    CachedRowSet impiegatiRowSet; //contiene il contenuto della table impiegati SQL
    private ResultSetMetaData metadata;
    int numcols, numrows;
    JFrame frame = new JFrame();

    public EmployeeTableModel(CachedRowSet rowSetArg) throws SQLException {
        this.impiegatiRowSet = rowSetArg;

        this.metadata = impiegatiRowSet.getMetaData();
        numcols = metadata.getColumnCount();

        this.impiegatiRowSet.beforeFirst();
        this.numrows = 0;
        while (this.impiegatiRowSet.next()) {
            this.numrows++;
        }
        this.impiegatiRowSet.beforeFirst();
        //showEmployees();
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

    /**
     * ritorna il tipo della colonna selezionata
     *
     * @param columnIndex indice della colonna
     * @return tipo di dato della specifica colonna
     * @author Luca
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    /**
     * la tabella non prevede modifiche dirette
     *
     * @param rowIndex
     * @param columnIndex
     * @return false
     * @author Luca
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    /**
     * metodo per ottenere il valore alla riga e colonna specificata
     *
     * @param rowIndex indice di riga
     * @param columnIndex indice di colonna
     * @return valore a riga e colonna specificata
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            this.impiegatiRowSet.absolute(rowIndex + 1);
            Object o = this.impiegatiRowSet.getObject(columnIndex + 1);
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

public CachedRowSet getImpiegatiRowSet() {
        return impiegatiRowSet;
    }

    public void setImpiegatiRowSet(CachedRowSet impiegatiRowSet) {
        this.impiegatiRowSet = impiegatiRowSet;
    }

    public void addEventHandlersToRowSet(RowSetListener listener) {
        this.impiegatiRowSet.addRowSetListener(listener);
    }

    public void close() {
        try {
            impiegatiRowSet.getStatement().close();
        } catch (SQLException ex) {
            ex.toString();
        }
    }

    @Override
    protected void finalize() {
        close();
    }
    
    public void insertRow(int id, String username, String password, String ruolo, boolean status) {
        try {
            this.impiegatiRowSet.moveToInsertRow();
            this.impiegatiRowSet.updateInt("ID", id);
            this.impiegatiRowSet.updateString("username", username);
            this.impiegatiRowSet.updateString("password", password);
            this.impiegatiRowSet.updateString("ruolo", ruolo);
            this.impiegatiRowSet.updateBoolean("status", status);
            this.impiegatiRowSet.insertRow();
            this.impiegatiRowSet.moveToCurrentRow();
        } catch (SQLException ex) {

            ex.toString();
        }
    }
}
