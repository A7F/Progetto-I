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
    
    @Override
    public void snap() {
        Thread thread = new Thread(new SnapshotDaemon(restaurant),"Thread di snapshot");
        thread.start();
    }

    @Override
    public void restore() {
        Thread thread = new Thread(new RestoreDaemon(restaurant),"Thread diripristino");
        thread.start();
    }

    @Override
    public void setMilliseconds(int millis) {
        this.milliseconds=millis;
    }

    @Override
    public int getMilliseconds() {
        return this.milliseconds;
    }
    
    public void setRestaurant(Restaurant res){
        this.restaurant=res;
    }

    /**
     * scatta uno snapshot ogni x millisecondi.
     * @author Luca
     */
    @Override
    public void startDaemon() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new SnapshotDaemon(restaurant), 0, milliseconds, TimeUnit.MILLISECONDS);
    }
}
