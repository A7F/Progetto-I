package utils;

import menu.MenuElement;

/**
 *
 * @author federicovitro
 */
public class Order {
    
    private int quantity;
    private MenuElement menuElement;
    private String notes = "";
    
    public Order(int quantity, MenuElement menuElement){
        this.quantity = quantity;
        this.menuElement = menuElement;
    }
    
    /**
     * Polimorfismo metodi costruttori: posso istanziare un ordine sia con che senza
     * annotazioni aggiuntive
     * @param quantity
     * @param menuElement
     * @param notes 
     * @author Luca :D
     */
    public Order(int quantity, MenuElement menuElement,String notes){
        this.quantity = quantity;
        this.menuElement = menuElement;
        this.notes = notes;
    }
    
    /**
     * i camerieri possono modificare le note relative all' elemento di ordinazione
     * @param text 
     * @author Luca :c
     */
    public void setNotes(String text){
        this.notes = text;
    }
    
    public void resetNotes(){
        this.notes = "";
    }
    
    public String getNotes(){
        return notes;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public MenuElement getMenuElement() {
        return menuElement;
    }

    public double getPrice(){
        return menuElement.getPrizeElement()*quantity;
    }
    
    
    /**
     * 
     * @autor FabioTagliani
     */
    @Override
    public String toString() {
        
        return "Ordine -> " + "Portata: " + menuElement + " Quantità: " + quantity + " note: " + notes + "\n";
    }
    
    
}
