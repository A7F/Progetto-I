package utils;


import java.util.ArrayList;
import menu.Menu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author federicovitro
 */
public class Restaurant {
    private String name;
    private Room room;
    private Menu menu;
    
    public Restaurant(String name, int numberOfTables){
        this.name = name;
        initRoom(numberOfTables);
        initMenu();
        initGUI();
    }
    
    private void initMenu(){
        
    }
    
    private void initRoom(int numberOfTables){
        room = new Room(numberOfTables);
    }
    
    private void initGUI(){
        //TODO da fare quando penseremo all'interfaccia grafica.
    }
    
}
