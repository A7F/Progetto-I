
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
        addTables(numberOfTables);
    }
    
    private void addTables(int numberOfTables){
        for (int i = 0; i<numberOfTables; i++){
            tables.add(new Table(i));
        }
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
