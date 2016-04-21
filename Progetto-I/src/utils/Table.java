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
public class Table {
    private int tableId;
    private Boolean isTaken;
    private ArrayList<Order> orders;
    
    public Table(int tableId){
        this.tableId = tableId;
        isTaken = false;
        orders = new ArrayList<>();
    }
    
    public Boolean getIsTaken(){
        return isTaken;
    }
    
    public void takeTable(){
        isTaken = true;
    }
    
    public void setFreeTable(){
        isTaken = false;
    }
    
    public void addOrder(Order order){
        orders.add(order);
    }
    
    public double calcCheck(){
        double totalCheck = 0.0;
        for (int i = 0; i < orders.size(); i++) {
            totalCheck += orders.get(i).getPrice();
        }
        return totalCheck;
    }

    public int getTableId() {
        return tableId;
    }
    

    @Override
    public String toString() {
        return "tavolo: "+tableId+" prenotato: "+isTaken+"\n";
    }
    
    
}
