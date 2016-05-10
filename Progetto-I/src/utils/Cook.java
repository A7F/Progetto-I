/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 *
 * @author Luca :3
 */
public class Cook {
    private ArrayList<Order> ordersCopy;
    private String cookName;
    
    public Cook(String name,ArrayList<Order> orders){
    this.cookName = name;
    ordersCopy = orders;
}
    
    private void setDone(Order order){
        ordersCopy.remove(order);
    }
    
}
