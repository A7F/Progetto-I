package tests;

import utils.AppConfig;

/**
 *
 * @author Luca
 */
public class TestJson {

    public static void main(String[] args) {
        System.out.println(AppConfig.getInstance().getAppName());
        AppConfig.getInstance().setAppName("ancora senza nome :(");
        AppConfig.getInstance().commitChanges();
        System.out.println(AppConfig.getInstance().getAppName());
    }
    
}
