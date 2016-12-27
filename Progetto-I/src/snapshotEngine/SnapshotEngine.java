package snapshotEngine;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import restaurant.Restaurant;

/**
 * questa classe singleton si occupa di:
 * - scattare uno snapshot ogni X secondi
 * - ripristinare lo stato del programma in caso di crash
 * @author Luca
 */
public class SnapshotEngine implements SnapEngine{
    
    private static SnapshotEngine snapshotEngine;
    private int milliseconds;
    private Restaurant restaurant;

    public static SnapshotEngine getInstance(){
        if(snapshotEngine==null){
            snapshotEngine=new SnapshotEngine();
        }
        return snapshotEngine;
    }
    
    /**
     * cattura una istantanea del programma.
     * @author Luca
     */
    @Override
    public void snap() {
        Thread thread = new Thread(new SnapshotDaemon(restaurant),"Thread di snapshot");
        thread.start();
    }

    /**
     * ripristina l'ultimo stato del programma
     * @author Luca
     */
    @Override
    public void restore() {
        Thread thread = new Thread(new RestoreDaemon(restaurant),"Thread diripristino");
        thread.start();
    }

    /**
     * imposta il delay fra uno snapshot e l'altro; utile solo se viene usato il daemon.
     * @param millis delay in millisecondi
     * @author Luca
     */
    @Override
    public void setMilliseconds(int millis) {
        this.milliseconds=millis;
    }

    /**
     * @author Luca
     * @return delay in millisecondi
     */
    @Override
    public int getMilliseconds() {
        return this.milliseconds;
    }
    
    /**
     * imposta il ristorante su cui eseguire backup
     * @param res ristorante bersaglio
     * @author Luca
     */
    public void setRestaurant(Restaurant res){
        this.restaurant=res;
    }

    /**
     * scatta uno snapshot ogni x millisecondi.
     * @author Luca
     * @see setMilliseconds()
     */
    @Override
    public void startDaemon() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new SnapshotDaemon(restaurant), 0, milliseconds, TimeUnit.MILLISECONDS);
    }
}
