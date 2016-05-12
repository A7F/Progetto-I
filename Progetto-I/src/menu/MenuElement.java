/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;


public class MenuElement {
    private String name;
    private double price;
    private Boolean available;
    private String typeElement;
    private String description;
    
    public MenuElement(String name, double price, String typeElement, String description){
        this.name = name;
        this.price = price;
        this.typeElement = typeElement;
        this.description = description;
        available = true;
        }
    
    public String toString(){
        return name+" "+price + " " + description + " " + available;
    }
    
    /**
     * Ritorna il nome dell' elemento di menu (=nome del piatto)
     * @author Luca :(
     * @return String typeElement
     */
    public String getNameElement(){
        return name;
    }
    
    public String getTypeElement(){
        return typeElement;
    }
    
    public double getPrizeElement(){
        return price;
    }
    
    
}
