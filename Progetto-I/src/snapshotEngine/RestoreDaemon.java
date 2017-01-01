package snapshotEngine;

import hibernateMappers.HibernateUtil;
import hibernateMappers.Snapshot;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import menu.MenuElement;
import org.hibernate.Session;
import org.hibernate.Transaction;
import restaurant.Order;
import restaurant.Restaurant;
import utils.AppConfig;
import utils.managers.MenuManager;

/**
 * questa classe ripristina l'ultimo salvataggio reimpostando il programma
 * allo stato appena prima della chiusura inaspettata.
 * @author Luca
 */
public class RestoreDaemon implements Runnable{
    
    Restaurant restaurant;

    RestoreDaemon(Restaurant restaurant){
        this.restaurant=restaurant;
    }
    
    @Override
    public void run() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        MenuManager mgr = new MenuManager();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        ArrayList<Snapshot> snapshots = (ArrayList<Snapshot>) session.createCriteria(Snapshot.class).list();
        ArrayList<MenuElement> elements = mgr.listFromMenuTable();
        
        for(int i=0; i<snapshots.size(); i++){
            MenuElement me;
            Order order;
            for(int j=0; j<elements.size();j++){
                if(snapshots.get(i).getElement_id()==elements.get(j).getId()){
                    me = new MenuElement(elements.get(j).getId(),elements.get(j).getNameElement(),elements.get(j).getPrizeElement(),elements.get(j).getDescription(),elements.get(j).getTypeElement());
                    order = new Order(1,me,snapshots.get(i).getNote());
                    restaurant.addOrder(snapshots.get(i).getTable_id(), order);
                }
            }
        }
        
        AppConfig.getInstance().setExitState(0);
        AppConfig.getInstance().commitChanges();
        System.out.println(dateFormat.format(date)+" - "+"Ripristinato ultimo stato programma.");
    }
    
}
