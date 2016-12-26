package hibernateMappers;

/**
 * classe di mapping per la tabella snapshot del database
 * @author Luca
 */
public class Snapshot {
    private int order_id;
    private int table_id;
    private int element_id;
    private String note;
    
    public Snapshot(){}
    
    public Snapshot(int tableId,int elementId){
        this.table_id=tableId;
        this.element_id=elementId;
        this.note="";
    }
    
    public Snapshot(int tableId,int elementId,String note){
        this.table_id=tableId;
        this.element_id=elementId;
        this.note=note;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    
    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public int getElement_id() {
        return element_id;
    }

    public void setElement_id(int element_id) {
        this.element_id = element_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
}
