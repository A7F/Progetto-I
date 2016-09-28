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
    
    @Override
    public String toString(){
        return name+" "+price + " " + description + " " + available;
    }
    
    /**
     * Ritorna il nome dell' elemento di menu (=nome del piatto)
     * @author Luca
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
    
    public String getDescription(){
        return description;
    }
    
    
}
