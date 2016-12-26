package snapshotEngine;

import hibernateMappers.HibernateUtil;
import hibernateMappers.Snapshot;
import org.hibernate.Session;
import org.hibernate.Transaction;
import restaurant.Restaurant;

/**
 * processo che viene eseguito ogni X secondi e che si occupa di scattare lo snapshot
 * @author Luca
 */
class SnapshotDaemon implements Runnable{
    private Restaurant restaurant;

    SnapshotDaemon(Restaurant restaurant) {
        this.restaurant=restaurant;
    }
    
    @Override
    public void run() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        for(int i=0; i<restaurant.getTables().size(); i++){
            
            if(!restaurant.getTables().get(i).getOrdersArray().isEmpty()){
                Snapshot snap = new Snapshot(restaurant.getTables().get(i).getTableId(),1);
                
            }
            
        }
        tx.commit();
        tx=null;
        session.close();
    }
}
