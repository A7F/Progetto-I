/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

/**
 *
 * @author federicovitro
 */
public class MenuElement {
    private String name;
    private double price;
    private Boolean available;
    private MenuElementType typeElement;
    private String description;
    
    public double getPrice(){
        return price;
    }
}
