package tests;

import hibernateMappers.HibernateUtil;
import hibernateMappers.Snapshot;
import java.io.IOException;
import menu.MenuElement;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
        
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        
//        Snapshot snap = new Snapshot(2,1);
//        Snapshot snap2 = new Snapshot(45,24);
//        Snapshot snap3 = new Snapshot(45,24,"buono");
//        
//        try{
//            tx = session.beginTransaction();
//            int id = (Integer)session.save(snap);
//            System.out.println("id1= "+id);
//            id = (Integer)session.save(snap2);
//            System.out.println("id2= "+id);
//            id = (Integer)session.save(snap3);
//            System.out.println("id2= "+id);
//            tx.commit();
//        }catch(HibernateException e){
//            tx.rollback();
//            e.printStackTrace();
//        }finally{
//            session.close();
//        }
        
        SnapshotEngine engine = SnapshotEngine.getInstance();
        engine.setMilliseconds(6000);
        engine.setRestaurant(r);
        engine.snap();
        
    }
    
}
