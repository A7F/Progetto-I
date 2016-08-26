package UIcapo;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * pannello per modificare le informazioni nel database visualizzato
 * @author Luca
 */
public class FunctionalPanel extends JPanel{
    int rows;
    JButton b1 = new JButton("EDIT");
    JButton b2 = new JButton("UPDATE");
    JButton b3 = new JButton("DELETE");
    JPanel buttonPane = new JPanel(new FlowLayout());
    JPanel formPane;
    TableModelBuilder tablePane;
    
    public FunctionalPanel(TableModelBuilder pane){
        this.setLayout(new BorderLayout());
        this.tablePane=pane;
        rows=tablePane.getColumnsCount();
        this.formPane = new JPanel(new GridLayout(rows,2,4,4)); //righe, colonne, spaziatura in pixel
        initMe();
    }
    
    private void initMe() {
        addToButtonPane();
        createLabels();
        this.add(formPane,BorderLayout.CENTER);
        this.add(buttonPane,BorderLayout.SOUTH);
    }

    private void addToButtonPane(){
        buttonPane.add(b1);
        buttonPane.add(b2);
        buttonPane.add(b3);
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
            formPane.add(labels.get(i));
            formPane.add(new JTextField());
        }
        
        return labels;
    }

    //ottieni la textfield relativa all' indice passato (sono solo i dispari per come è fatto il gridlayout)
    private JTextField getRelativeTextField(int index){
        JTextField textField;
        if(index%2==1){     //controlla se viene passato un indice pari o dispari
            textField =(JTextField)formPane.getComponent(index);
        }else{
            textField=new JTextField();
        }
        return textField;        
    }
}
