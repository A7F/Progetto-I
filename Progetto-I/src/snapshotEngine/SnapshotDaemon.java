package snapshotEngine;

import hibernateMappers.HibernateUtil;
import hibernateMappers.Snapshot;
import org.hibernate.Session;
import org.hibernate.Transaction;
import restaurant.Restaurant;

/**
 * processo che viene eseguito ogni X secondi e che si occupa di scattare lo snapshot.
 * viene salvata l'istantanea dei soli tavoli che hanno effettuato ordinazioni.
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
        
        tx = session.beginTransaction();
        
        for(int i=0; i<restaurant.getTables().size(); i++){
            
            if(!restaurant.getTables().get(i).getOrdersArray().isEmpty()){
                for(int j=0;j<restaurant.getTables().get(i).getOrdersArray().size();j++){
                    Snapshot snap = new Snapshot(restaurant.getTables().get(i).getTableId(),restaurant.getTables().get(i).getOrdersArray().get(j).getMenuElement().getId(),restaurant.getTables().get(i).getOrdersArray().get(j).getNotes());
                    session.save(snap);
                }
            }
        }
        
        tx.commit();
        tx=null;
        session.close();
    }
}
