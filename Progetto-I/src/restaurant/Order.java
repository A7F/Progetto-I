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
    
    /**
     * @author Federico Vitrò
     * @param quantity
     * @param menuElement 
     */
    
    public Order(int quantity, MenuElement menuElement){
        this.quantity = quantity;
        this.menuElement = menuElement;
    }
    
    /**
     * Polimorfismo metodi costruttori: posso istanziare un ordine sia con che senza
     * annotazioni aggiuntive
     * @param quantity la quantità da aggiungere di uno stesso ordine
     * @param menuElement l'elemento di menu scelto
     * @param notes eventuali note correlate all' ordine
     * @author Luca
     */
    public Order(int quantity, MenuElement menuElement,String notes){
        this.quantity = quantity;
        this.menuElement = menuElement;
        this.notes = notes;
    }
    
    /**
     * @author Federico Vitrò
     * @param menuElement 
     */
    
    public Order(MenuElement menuElement){
        this.quantity = 1;
        this.menuElement = menuElement;
    }
    
    /**
     * i camerieri possono modificare le note relative all' elemento di ordinazione
     * @param text le note da aggiungere
     * @author Luca
     */
    public void setNotes(String text){
        this.notes = text;
    }
    
    /**
     * metodo per azzerare le note relative all'ordine
     * @author Luca
     */
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
     * Getter dello stato dell' ordine selezionato
     * @author Luca
     * @return true se l'ordine è eseguito
     * @see Cook
     */
     public boolean getDone(){
        return this.is_done;
    }
    
     /**
     * Getter dello stato dell' ordine selezionato
     * @author Luca
     * @return true se l'ordine è letto
     * @see Cook
     */
    public boolean getRead(){
        return this.is_read;
    }
    
    /**
     * metodo di set che cambia lo stato di esecuzione dell' ordine: se fatto allora verrà
     * impostato come non fatto, e viceversa.
     * @author Luca
     * @see Cook
     */
    public void changeDone(){
        if(is_done){
            this.is_done = false;
        }else{
            this.is_done = true;
            this.is_read = true;
        }
    }
    
    /**
     * metodo di set che cambia lo stato di lettura dell' ordine: se letto allora verrà
     * impostato come non letto, e viceversa.
     * @author Luca
     * @see Cook
     */
    public void changeRead(){
        if(is_read){
            this.is_read = false;
            this.is_done = false;
        }else{
            this.is_read = true;
            this.is_done = false;
        }
    }
    
    /**
     * metodo get del nome dell' elemento menu
     * @return nome dell' elemento del menu
     * @author Luca
     */
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
