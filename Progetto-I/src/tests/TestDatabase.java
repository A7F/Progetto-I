package tests;

import utils.DatabaseManager;

/**
 *
 * @author Luca
 */
public class TestDatabase {
    public static void main(String[] args){
        DatabaseManager dbm = new DatabaseManager();
        dbm.initServer();
    }
}
