package UIcapo;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;


/**
 * Questa classe si occupa di creare una JTable che riflette la struttura del database
 * @author Luca
 */

public class TableModelBuilder {
    
    final String tab;
    
    public TableModelBuilder(String table){
        this.tab=table;
    }

    private JPanel createAndShowGui() throws SQLException {
        final JPanel panel = new JPanel();
        final TableModel tableModel = buildTableModel(getData(this.tab));
        final JTable table = new JTable(tableModel);
        panel.add(new JScrollPane(table));
        
        return panel;
    }

    private ResultSet getData(String table) throws SQLException {
        final String url = "jdbc:mysql://localhost:3306";
        final Connection connection = DriverManager.getConnection(url, "root", "");
        final String sql = "SELECT * FROM "+tab;
        final String selection = "USE Ristorante";
        PreparedStatement preparedStatement = connection.prepareStatement(selection);
        preparedStatement.executeQuery();
        PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
        return preparedStatement2.executeQuery();
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
        while (resultSet.next()) {
            Vector<Object> rowVector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                rowVector.add(resultSet.getObject(columnIndex));
            }
            dataVector.add(rowVector);
        }
        return new DefaultTableModel(dataVector, columnNames);
    }
    
    public JPanel getPanelModel() throws SQLException{
        return this.createAndShowGui();
    }
}