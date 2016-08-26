package UIcapo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import utils.DatabaseManager;

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
    DatabaseManager mgr = new DatabaseManager();
    
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
        
        b1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("EDIT");
            }
        });
        
        b2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("UPDATE");
            }
        });
        
        b3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("DELETE");
            }
        });
        
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
    
    //questo metodo deve controllare che tutti i campi non siano vuoti prima di fare update
    private boolean isTextAreaEmpty(){
        boolean flag = false;
        for(int i=1;i<rows;i++){
            String text = getRelativeTextField(i).getText();
            if(text.equals("")){
            } else {
                flag=true;
            }
            i++;    //così prende solo i dispari ovvero prende solo le jtextarea
        }
        return flag;
    }
}
