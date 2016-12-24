package snapshotEngine;

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
        for(int i=0; i<restaurant.getTables().size(); i++){
            
            
            
        }
    }
}
