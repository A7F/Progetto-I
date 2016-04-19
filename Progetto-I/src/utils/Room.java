package utils;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author federicovitro
 */
public class Room {
    private ArrayList<Table> tables;
    
    public Room(int numberOfTables){
        tables = new ArrayList<Table>();
        initTables(numberOfTables);
    }
    
    //il metodo initTable() inizializza i tavoli alla creazione della Room
    
    private void initTables(int numberOfTables){ 
        for (int i = 0; i<numberOfTables; i++){
            tables.add(new Table(i));
        }
    }
    
    //il metodo addTable() aggiunge alla room un tavolo
    
    public void addtables(){
        tables.add(new Table(tables.size()));
    }
    
    public ArrayList<Table> getFreeTable(){
        ArrayList<Table> tableFree = new ArrayList<Table>();
        for (int i = 0; i < tables.size(); i++) {
            if(tables.get(i).getIsTaken() == false){
                tableFree.add(tables.get(i));
            }
        }
        return tableFree;
    }
}
