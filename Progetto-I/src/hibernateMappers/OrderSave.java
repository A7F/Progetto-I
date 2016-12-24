package hibernateMappers;

/**
 * classe di mapping per la tabella di salvataggio ordini del database ristorante
 * @author Luca
 */
public class OrderSave {
    private int order_id;
    private int element_id;
    private String notes;
    
    public OrderSave(int menuelement_id,String notes){
        this.element_id=menuelement_id;
        this.notes=notes;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getElement_id() {
        return element_id;
    }

    public void setElement_id(int element_id) {
        this.element_id = element_id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

       
    
}
