package utils;


import menu.MenuElement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author federicovitro
 */
public class Order {
    private int quantity;
    private MenuElement menuElement;
    private String notes;
    
    public Order(int quantity, MenuElement menuElement){
        this.quantity = quantity;
        this.menuElement = menuElement;
    }

    public int getQuantity() {
        return quantity;
    }

    public MenuElement getMenuElement() {
        return menuElement;
    }

    public String getNotes() {
        return notes;
    } 
    
    public double getPrice(){
        return menuElement.getPrice()*quantity;
    }
}
