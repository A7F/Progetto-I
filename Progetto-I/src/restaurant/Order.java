package restaurant;

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
     * @author Luca
     */
    public Order(int quantity, MenuElement menuElement,String notes){
        this.quantity = quantity;
        this.menuElement = menuElement;
        this.notes = notes;
    }
    
    /**
     * i camerieri possono modificare le note relative all' elemento di ordinazione
     * @param text 
     * @author Luca
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
     public boolean getDone(){
        return this.is_done;
    }
    
    public boolean getRead(){
        return this.is_read;
    }
    
    public void changeDone(){
        if(is_done){
            this.is_done = false;
        }else{
            this.is_done = true;
            this.is_read = true;
        }
    }
    
    public void changeRead(){
        if(is_read){
            this.is_read = false;
            this.is_done = false;
        }else{
            this.is_read = true;
            this.is_done = false;
        }
    }
    
    
    public String getNameEl(){
        return menuElement.getNameElement();
    }
    
    /**
     * sistemato il toString: visualizza solo il nome dell' ordine
     * @autor Luca
     */
    @Override
    public String toString() {
        return menuElement.getNameElement();
    }
}
