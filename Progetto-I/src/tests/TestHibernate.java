package tests;

import hibernateMappers.HibernateUtil;
import hibernateMappers.Snapshot;
import java.io.IOException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import restaurant.Restaurant;
import utils.AppConfig;

/**
 *
 * @author Luca
 */
public class TestHibernate {

    
    public static void main(String[] args) throws IOException {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        Snapshot snap = new Snapshot(2,true);
        Snapshot snap2 = new Snapshot(45,false);
        
        try{
            tx = session.beginTransaction();
            int id = (Integer)session.save(snap);
            System.out.println("id1= "+id);
            id = (Integer)session.save(snap2);
            System.out.println("id2= "+id);
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        
    }
    
}
