package UIcapo;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    CustomFrame refFrame;
    JFrame frame = new JFrame();
    
    public buttonLayout(DatabaseManager mgr,HashMap<String,JTextField> map,String tablename,TableModelBuilder tablePane,CustomFrame frame){
        this.tablePane=tablePane;
        this.refFrame=frame;
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
                    PreparedStatement con;
                    try {
                        switch (tablename) {
                            case "impiegati":
                                con = mgr.getConnection().prepareStatement("USE ristorante");
                                con.executeQuery();
                                con = mgr.getConnection().prepareStatement("INSERT INTO impiegati(username,password,ruolo) VALUES (?,?,?)");
                                con.setString(1, map.get("username").getText());
                                con.setString(2, map.get("password").getText());
                                con.setString(3, map.get("ruolo").getText());
                                con.executeUpdate();
                                refFrame.repaint();
                                break;
                            case "menu":
                                con = mgr.getConnection().prepareStatement("USE ristorante");
                                con.executeQuery();
                                con = mgr.getConnection().prepareStatement("INSERT INTO menu(name,description,price,type) VALUES (?,?,?,?)");
                                con.setString(1, map.get("name").getText());
                                con.setString(2, map.get("description").getText());
                                con.setString(3, map.get("price").getText());
                                con.setString(4, map.get("type").getText());
                                con.executeUpdate();
                                refFrame.repaint();
                                break;
                        }
                    } catch (SQLException ex){
                        Logger.getLogger(FunctionalPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(frame,"Ci sono campi vuoti. Vanno riempiti tutti.","Errore",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        b3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int id=0;
                String str = map.get("id").getText();
                id = Integer.parseInt(str);
                if(!str.equals("")){
                    try {
                        PreparedStatement con = mgr.getConnection().prepareStatement("USE ristorante");
                        con.executeQuery();
                        if(!checkExistingId(mgr.getConnection(),tablename,id)){
                            con = mgr.getConnection().prepareStatement("DELETE FROM impiegati WHERE id=?");
                            con.setInt(1, id);
                            con.executeUpdate();
                            refFrame.repaint();
                        }else{
                            JOptionPane.showMessageDialog(frame,"ID inesistente","Errore",JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex){
                        Logger.getLogger(FunctionalPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(frame,"Specificare un id da cancellare","Errore",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    private boolean checkExistingId(Connection con,String nametable,int id){
        boolean flag=false;
        ResultSet rs = null;
        switch(nametable){
            case "impiegati":
                try {
                    PreparedStatement stat=con.prepareStatement("SELECT * FROM impiegati WHERE id=?");
                    //stat.setString(1, nametable);
                    stat.setInt(1, id);
                    rs=stat.executeQuery();
                } catch (SQLException ex) {
                    Logger.getLogger(buttonLayout.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "menu":
                try {
                    PreparedStatement stat=con.prepareStatement("SELECT * FROM menu WHERE id=?");
                    //stat.setString(1, nametable);
                    stat.setInt(1, id);
                    rs=stat.executeQuery();
                } catch (SQLException ex) {
                    Logger.getLogger(buttonLayout.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
        return isResultSetEmpty(rs);
    }
    
    //false=resultset non vuoto
    private boolean isResultSetEmpty(ResultSet rs){
        boolean flag=false;
        try {
            if(rs.next()){
                flag=false;
            }else{
                flag=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(buttonLayout.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
    //false=non ci sono textfield vuoti
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
    
}
