package UIcapo;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import utils.DatabaseManager;

/**
 *
 * @author Luca
 */
public class buttonLayout extends JPanel{
    JButton b1 = new JButton("EDIT");
    JButton b2 = new JButton("UPDATE");
    JButton b3 = new JButton("DELETE");
    Connection con;
    HashMap<String,JTextField> map;
    String tablename;
    TableModelBuilder tablePane;
    DatabaseManager mgr;
    
    public buttonLayout(DatabaseManager mgr,HashMap<String,JTextField> map,String tablename,TableModelBuilder tablePane){
        this.tablePane=tablePane;
        this.mgr=mgr;
        con=mgr.getConnection();
        this.map=map;
        this.tablename=tablename;
        this.setLayout(new FlowLayout());
        addButtonListeners();
        this.add(b1);
        this.add(b2);
        this.add(b3);
    }
    
    public boolean areTextFieldEmpty(){
        ArrayList<String> temp = tablePane.getColumnsName();
        boolean flag=false;
        for (String temp1 : temp) {
            if (map.get(temp1).getText().equals("")) {
                flag=true;
                break;
            }
        }
        return flag;
    }

    private void addButtonListeners() {
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("EDIT");
            }
        });
        
        b2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!areTextFieldEmpty()){
                    if(!areTextFieldEmpty()){
                        PreparedStatement con;
                        try {
                            if(tablename.equals("impiegati")){
                                con = mgr.getConnection().prepareStatement("USE ristorante");
                                con.executeQuery();
                                con = mgr.getConnection().prepareStatement("INSERT INTO impiegati(username,password,ruolo) VALUES (?,?,?)");
                                con.setString(1, map.get("username").getText());
                                con.setString(2, map.get("password").getText());
                                con.setString(3, map.get("ruolo").getText());
                                con.executeUpdate();
                            }else if(tablename.equals("menu")){
                                con = mgr.getConnection().prepareStatement("USE ristorante");
                                con.executeQuery();
                                con = mgr.getConnection().prepareStatement("INSERT INTO menu(name,description,price,type) VALUES (?,?,?,?)");
                                con.setString(1, map.get("name").getText());
                                con.setString(2, map.get("description").getText());
                                con.setString(3, map.get("price").getText());
                                con.setString(4, map.get("type").getText());
                                con.executeUpdate();
                                tablePane.reprintMe();
                            }
                        tablePane.reprintMe();
                        } catch (SQLException ex) {
                            Logger.getLogger(FunctionalPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
        
        b3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String colName = tablePane.getSelectedColumnName();     //per cancellare una entrata si deve selezionare la colonna id
                String str = map.get(colName).getText();
                int id = Integer.parseInt(str);
                               
                if(!str.equals("")){
                    try {
                        PreparedStatement con = mgr.getConnection().prepareStatement("USE ristorante");
                        con.executeQuery();
                        con = mgr.getConnection().prepareStatement("DELETE FROM impiegati WHERE id=?");
                        //con.setString(1, tablename);
                        //con.setString(2, colName);
                        con.setInt(1, id);
                        con.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(FunctionalPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
}
