package restaurant;


import graphics.cassa.PercentDiscount;
import graphics.cassa.ProxyDiscount;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import menu.Menu;
import menu.MenuElement;

/**
 *
 * @author Federico Vitrò
 */
public class Restaurant extends Observable{
    
    private final String name;
    private Room room;
    private Menu menu;
    private ProxyDiscount contextDiscount = new ProxyDiscount(new PercentDiscount());
    
    /**
     * @author Federico Vitrò
     * @param name il nome del ristorante
     * @param numberOfTables numero dei tavoli che ha il ristorante
     * @param pathFile percorso da cui caricare il file di testo contenente il menu
     * @throws IOException file menu non trovato
     */
    public Restaurant(String name, int numberOfTables, String pathFile) throws IOException{
        this.name = name;
        initRoom(numberOfTables);
        initMenu(pathFile);
    }
    
    /**
     * metodo per inizializzare il menu
     * @param pathFile percorso del file di testo del menu
     * @throws IOException 
     * @author Luca
     */
    private void initMenu(String pathFile) throws IOException{
        menu = new Menu(pathFile);   
    }
    
    /**
     * inizializza la sala da pranzo
     * @author Federico Vitrò
     * @param numberOfTables il numero dei tavoli nella sala
     */
    
    private void initRoom(int numberOfTables){
        room = new Room(numberOfTables);
    }
    
    /**
     * restituisce l'array di ordini 
     * @author Federico Vitrò
     * @return arraylist degli ordini
     * @see UIcook.CookUI
     */
    public ArrayList<Order> getOrdersArray(){
        return room.getOrdersArray();
    }
    
    /**
     * Restituisce l'ordinazione di un specifico tavolo
     * @param tableId l'id del tavolo di cui si vogliono gli ordini
     * @return arraylist di ordini relativi al tavolo con id specificato
     * @author FabioTagliani
     */
    public ArrayList<Order> getOrderTable(int tableId){
        setChanged();
        notifyObservers(tableId);
        return room.getOrdersTable(tableId - 1);
    }
    
    /**
     * metodo di sincronizzazione con gli altri observers
     * @author Luca
     * @see utils.TablePanel
     */
    public void notifyAllObservers(){
        setChanged();
        notifyObservers();
    }
    
    /**
     * questo metodo si occupa di notificare il cuoco di cambiamenti negli
     * ordini di sala (per gestire la grafica)
     * @author Luca 
     */
    public void notifyCook(){
        this.setChanged();
        notifyObservers(this.getOrdersArray());
    }
    
    /**
     * Aggiunge un ordine al tavolo prescelto
     * @param tableId id del tavolo su cui aggiungere l'ordine
     * @param order l'ordine da aggiungere
     * @author FabioTagliani
     */
    public void addOrder(int tableId, Order order){
        this.setChanged();
        notifyObservers(this.getOrdersArray());
        room.addOrder(tableId, order);
    }
    
    /**
     * Aggiunge un tavolo al ristorante aggiornando anche il database
     * @author Luca
     */
    public void addTables(){
       room.addNewTable();
       this.setChanged();
       notifyObservers();
    }
    
    /**
     * Rimuove un tavolo al ristorante aggiornando anche il database.
     * Un tavolo può essere rimosso senza problemi solo se libero, altrimenti è richiesta conferma dell' azione.
     * @author Luca
     */
    public void remTables() {
        int input = 0;
        
        if(room.getTables().size() == 1){
            JOptionPane.showMessageDialog(new JFrame(), "Non è possibile rimuovere l'unico tavolo presente","info",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if(room.getTables().get(room.getTables().size()-1).getIsTaken()){
            input = JOptionPane.showOptionDialog(null, "Sei sicuro? L'azione non può essere annullata!", "Conferma", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            if (input == JOptionPane.OK_OPTION) {
            room.removeLastTable();
            this.setChanged();
            notifyObservers();
            }
        }else{
            room.removeLastTable();
            this.setChanged();
            notifyObservers();
        }
    }
    
    /**
     * Rimuove il singolo ordine di un tavolo
     * @param tableId id del tavolo su cui rimuovere l'ordine
     * @param order ordine da rimuovere
     * @author Luca
     */
    public void removeSingleOrder(int tableId, Order order){
        this.setChanged();
        notifyObservers(this.getOrdersArray());
        room.removeSingleOrder(tableId - 1, order);
    }
    
    /**
     * Rimuove tutti gli ordini di un tavolo
     * @param tableId id del tavolo su cui rimuovere gli ordini
     * @author Fabio Tagliani
     */
    public void removeAllOrder(int tableId){
        room.removeAllOrder(tableId - 1);
        this.setChanged();
        notifyObservers(this.getOrdersArray());
    }
    
    /**
     * dato l'id di un tavolo non prenotato lo prenota
     * @author Federico Vitrò
     * @param tableId numero del tavolo da riservare
     */
    public void setReserved(int tableId){
        room.setReservedTable(tableId -1);
        this.setChanged();
        notifyObservers(this.getTables());
    }
    
    /**
     * duale del metodo setReserved; imposta come libero il tavolo con id specificato
     * @author Federico Vitrò
     * @param tableID numero del tavolo da liberare
     * @see setReserved
     */
    public void setFreeTable(int tableID){
        room.setFreeTable(tableID - 1);
        this.setChanged();
        notifyObservers(this.getTables());
    }
    
    /**
     * Restituisce i tavoli della sala
     * @author fabiotagliani
     * @return tavoli della sala
     */
    public ArrayList<Table> getTables(){
        return room.getTables();
      
    }

     /**
     * Ritorna l'eventuale sconto di giornata
     * @return discount
     * @author FabioTagliani
     */
    public ProxyDiscount getDiscount() {
        return contextDiscount;
    }
            
    /**
    * Restituisce i tavoli non prenotati
    * @author FabioTagliani
     * @return tutti i tavoli liberi della sala
    */
    public ArrayList<Table> getFreeTable(){
       return room.getFreeTable();
    }
    
    /**
     * ritorna il menu del ristorante
     * @author Federico Vitrò
     * @return menu del ristorante
     */    
    public ArrayList<MenuElement> getMenu(){
        return menu.getMenu();
    }
    
    /**
     * dato il nome di un elemento il metodo restituisce gli elementi nel menu corrispondenti a quel nome
     * @author Federico Vitrò
     * @param elementName nome del menuelement da cercare
     * @return elementi menu (ovvero pietanze) con lo stesso nome
     */
    public ArrayList<MenuElement> getMenuByName(String elementName){
        return menu.getElementByName(elementName);
    }
    
    /**
     * dato il tipo di un elemento restituisce gli elementi nel menu corrispondenti a quel tipo
     * @author Federico Vitrò
     * @param type tipo del menuelement
     * @return elementi menu (ovvero pietanze) con lo stesso tipo
     */
    public ArrayList<MenuElement> getElementByType(String type){
        return menu.getElementByType(type);
    }
    
    /**
     * dato il prezzo di un elemento restituisce gli elementi nel menu corrispondenti a quel prezzo
     * @author Federico Vitrò
     * @param cash prezzo del menuelement
     * @return elementi menu (ovvero pietanze) con lo stesso prezzo
     */
    public ArrayList<MenuElement> getElementByPrice(double cash){
        return menu.getElementByPrice(cash);
    }
    
    /**
     * restituisce i tavoli prenotati
     * @author Federico Vitrò
     * @return tutti i tavoli occupati
     */
     public ArrayList<Table> getTakenTable(){
        return room.getTakenTable();
    }
     
     /**
      * restituisce il nome del ristorante
      * @author Federico Vitrò
      * @return name nome del ristorante
      */
    public String getRestaurantName() {
        return this.name;
    }
}
