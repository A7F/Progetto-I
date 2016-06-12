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
    private boolean is_read = false;
    private boolean is_done = false;
    
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

    public void getTicket(){
    
        System.out.println(quantity + "|| " + menuElement.getNameElement() + "|| " + menuElement.getPrizeElement());
    }
    
    public double getPrice(){
        return menuElement.getPrizeElement()*quantity;
    }
    
    /**
     * i seguenti metodi sono per la classe cuoco, il quale deve poter segnare
     * come letto o eseguito l'elemento di ordinazione del tavolo.
     * @author Luca
     */
    public void setDone(){
        this.is_done = true;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
       
    public void unsetDone(){
        this.is_done = false;
    }
    
    public void setRead(){
        this.is_read = true;
    }
    
    public void unsetRead(){
        this.is_read = false;
    }
    
    public void changeDone(){
        if(this.is_done){
            unsetDone();
        }
        setDone();
    }
    
    public void changeRead(){
        if(this.is_read){
            unsetRead();
        }
        setRead();
    }
    
    public boolean getOrderRead(){
        return this.is_read;
    }
    
    public boolean getOrderDone(){
        return this.is_done;
    }
    
    
    public String getNameEl(){
        return menuElement.getNameElement();
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
