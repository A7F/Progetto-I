package hibernateMappers;

/**
 * classe di mapping per la tabella snapshot del database
 * @author Luca
 */
public class Snapshot {
    private int table_id;
    private boolean is_taken;
    private int order_id;
    
    public Snapshot(){}
    
    public Snapshot(int table_id,boolean is_taken){
        this.is_taken=is_taken;
        this.table_id=table_id;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public boolean isIs_taken() {
        return is_taken;
    }

    public void setIs_taken(boolean is_taken) {
        this.is_taken = is_taken;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    
    
}
