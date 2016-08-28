package UIcapo;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import utils.DatabaseManager;

/**
 * pannello per modificare le informazioni nel database visualizzato
 * @author Luca
 */
public class FunctionalPanel extends JPanel{
    int rows;
    String tablename;
    JButton b1 = new JButton("EDIT");
    JButton b2 = new JButton("UPDATE");
    JButton b3 = new JButton("DELETE");
    JPanel buttonPane;
    JPanel formPane;
    TableModelBuilder tablePane;
    DatabaseManager mgr = new DatabaseManager();
    HashMap<String,JTextField> map = new HashMap<>();
    
    public FunctionalPanel(TableModelBuilder pane,String tablename){
        this.tablename=tablename;
        this.setLayout(new BorderLayout());
        this.tablePane=pane;
        rows=tablePane.getColumnsCount();
        this.formPane = new JPanel(new GridLayout(rows,2,4,4)); //righe, colonne, spaziatura in pixel
        initMe();
    }
    
    private void initMe() {
        buttonPane=new buttonLayout(mgr,map,tablename,tablePane);
        createLabels();
        this.add(formPane,BorderLayout.CENTER);
        this.add(buttonPane,BorderLayout.SOUTH);
    }
    
    private ArrayList<JLabel> createLabels(){
        ArrayList<JLabel> labels = new ArrayList<>();
                        
        //ottieni i nomi delle colonne e facci delle JLabel
        for(int i=0;i<tablePane.getColumnsName().size();i++){
            JLabel label = new JLabel(tablePane.getColumnsName().get(i));
            labels.add(label);
        }
                
        //popola la griglia con label ed area di testo
        for(int i=0;i<rows;i++){
            JTextField f = new JTextField();
            formPane.add(labels.get(i));
            formPane.add(f);
            map.put(labels.get(i).getText(), f);
        }
        return labels;
    }
    
}
