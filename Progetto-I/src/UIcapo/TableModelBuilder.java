package UIcapo;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;


/**
 * Questa classe si occupa di creare una JTable che rifletta la struttura del database
 * @author Luca
 * @see FunctionalPanel
 */
public class TableModelBuilder {
    
    final String tab;
    JTable table;
    JPanel panel = new JPanel();
    TableModel tableModel;
    
    public TableModelBuilder(String tablename){
        this.tab=tablename;
    }

    private JPanel createAndShowGui() throws SQLException {
        tableModel = buildTableModel(getData(this.tab));
        table = new JTable(tableModel);
        panel.add(new JScrollPane(table));
        
        return panel;
    }

    private ResultSet getData(String table) throws SQLException {
        final String url = "jdbc:mysql://localhost:3306/ristorante?relaxAutoCommit=true";
        final Connection connection = DriverManager.getConnection(url, "root", "");
        //final String sql = "SELECT * FROM ?;";
        String selection = "USE ristorante";
        PreparedStatement preparedStatement = connection.prepareStatement(selection);
        preparedStatement.executeQuery();
        preparedStatement = connection.prepareStatement("SELECT * FROM ?;");
        preparedStatement.setString(1, table);
        return preparedStatement.executeQuery();
    }

    protected static TableModel buildTableModel(final ResultSet resultSet) throws SQLException {
        int columnCount = resultSet.getMetaData().getColumnCount();

        // ottieni i nomi delle colonne. Uso SuppressWarnings almeno l'IDE non mi rompe per Vector che sarebbe deprecato
        @SuppressWarnings("UseOfObsoleteCollectionType")
        Vector<String> columnNames = new Vector<>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            columnNames.add(resultSet.getMetaData().getColumnName(columnIndex));
        }

        // ottieni i dati della table
        Vector<Vector<Object>> dataVector = new Vector<>();
        while (resultSet.next()){
            Vector<Object> rowVector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
                rowVector.add(resultSet.getObject(columnIndex));
            }
            dataVector.add(rowVector);
        }
        return new DefaultTableModel(dataVector, columnNames);
    }
    
    public JPanel getPanelModel() throws SQLException{
        return this.createAndShowGui();
    }
    
    public int getColumnsCount(){
        return table.getColumnCount();
    }
    
    public int getColumn(){
        return table.getSelectedColumn();
    }
    
    public int getRow(){
        return table.getSelectedRow();
    }
    
    public String getSelectedColumnName(){
        return table.getColumnName(getColumn());
    }
    
    public ArrayList<String> getColumnsName(){
        ArrayList<String> names = new ArrayList<>();
        for(int i=0;i<this.getColumnsCount();i++){
            names.add(table.getColumnName(i));
        }
        return names;
    }
    
    public Object getElementAt(){
        return table.getComponentAt(table.getSelectedRow(), table.getSelectedColumn());
    }
    
}