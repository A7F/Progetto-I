package tests;

import java.io.IOException;
import menu.MenuElement;
import restaurant.Order;
import restaurant.Restaurant;
import snapshotEngine.SnapshotEngine;
import utils.AppConfig;

/**
 *
 * @author Luca
 */
public class TestHibernate {

    
    public static void main(String[] args) throws IOException {
        
        Restaurant r = new Restaurant(AppConfig.getInstance().getRestaurantName(),AppConfig.getInstance().getTableNumber(),AppConfig.getInstance().getMenuPath());
        MenuElement me = new MenuElement(10,"Pasta alla Carbonara", 7, "PRIMO", "Descrizione.");
        MenuElement me2 = new MenuElement(10,"Pasta alla Carbonara", 7, "PRIMO", "Altra descrizione.");
        Order order = new Order(1,me, "al dente");
        Order order2 = new Order(1,me2, "senza bianchi");
        r.addOrder(1, order);
        r.addOrder(1, order2);
        
        if(AppConfig.getInstance().getExitState()!=0){
            SnapshotEngine engine = SnapshotEngine.getInstance();
            engine.restore();
        }else{
            SnapshotEngine engine = SnapshotEngine.getInstance();
            engine.setMilliseconds(6000);
            engine.setRestaurant(r);
            engine.startDaemon();
        }
        
    }
    
}
