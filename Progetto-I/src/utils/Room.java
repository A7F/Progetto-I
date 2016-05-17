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
    
    /**
     * inizializza i tavoli alla creazione della Room
     * @param numberOfTables 
     */
    private void initTables(int numberOfTables){ 
        
        for (int i = 0; i<numberOfTables; i++){
            tables.add(new Table(i));
        }
    }
    
    /**
     * aggiunge alla room un tavolo
     */
    public void addtables(){
        tables.add(new Table(tables.size()));
    }
    
    /**
     * Riserva il tavolo 
     * @author fabiotagliani
     * @param tableId 
     */
    public void setReservedTable(int tableId){
    
        for (int i = 0; i < tables.size(); i++) {
            
            if(tables.get(i).getTableId() == tableId){
                if(!(tables.get(i).getIsTaken())){
                    tables.get(i).takeTable();
                }else{
                    System.out.println("EEEH NO EH IL TAVOLO E' GIA' PRENOTATO ZIOOOOO");
                }
            }
        }
    }
    
    /**
     * Restituisce i tavoli della sala
     * @author fabiotagliani
     */
    public ArrayList<Table> getTables() {
        return tables;
    }
    
    /**
     * Restituisce i tavoli non prenotati 
     * @author fabiotagliani
     * @return tableFree
     */
    public ArrayList<Table> getFreeTable(){
        
        ArrayList<Table> tableFree = new ArrayList<Table>();
        for (int i = 0; i < tables.size(); i++) {
            if(tables.get(i).getIsTaken() == false){
                tableFree.add(tables.get(i));
            }
        }
        return tableFree;
    }

    /**
     * Restituisce i tavoli prenotati 
     * @author Luca :D
     * @return tableTaken
     */
    public ArrayList<Table> getTakenTable(){
        
        ArrayList<Table> tableTaken = new ArrayList<Table>();
        for (int i = 0; i < tables.size(); i++) {
            if(tables.get(i).getIsTaken() == true){
                tableTaken.add(tables.get(i));
            }
        }
        return tableTaken;
    }
    
    /**
     * @author fabiotagliani
     */   
    @Override
    public String toString() {
        
        String ret="";
        for (int i=0; i<tables.size(); i++){
            ret+=tables.get(i).toString();
        }
        return ret;
    }

    void addOrder(int tableID, Order order) { 
        if(tables.get(tableID-1).getIsTaken() == false){
            tables.get(tableID-1).takeTable();
            
            // DEBUG
            System.err.println("Il tavolo non era prenotato. Verra prenotato.");
        }
        tables.get(tableID-1).addOrder(order);
    }

    void removeOrder(int tableID, Order order) {
        tables.get(tableID-1).removeOrder(order);
    }
    
}
