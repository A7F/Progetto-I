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

    /**
     * @author Luca
     * @return id dell' ordine (PK)
     */
    public int getOrder_id() {
        return order_id;
    }

    /**
     * @author Luca
     * @param order_id id dell' ordine. Questo metodo non va mai usato, è destinato ad Hibernate.
     */
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    
    /**
     * @author Luca
     * @return id del tavolo (non allineato dunque il tavolo 1 sarà 0)
     */
    public int getTable_id() {
        return table_id;
    }

    /**
     * @author Luca
     * @param table_id id del tavolo (non allineato)
     */
    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    /**
     * ottieni l' id dell' elemento menu con cui è stato salvato nella tabella database
     * @return id elemento menu (PK)
     * @author Luca
     */
    public int getElement_id() {
        return element_id;
    }

    /**
     * @author Luca
     * @param element_id id dell' elemento menu
     */
    public void setElement_id(int element_id) {
        this.element_id = element_id;
    }

    /**
     * @author Luca
     * @return note sull' ordine
     */
    public String getNote() {
        return note;
    }

    /**
     * @author Luca
     * @param note note da associare all' ordine
     */
    public void setNote(String note) {
        this.note = note;
    }
    
    
}
