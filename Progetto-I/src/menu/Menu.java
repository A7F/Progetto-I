/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author federicovitro
 */
public class Menu {
    private ArrayList<MenuElement> menu;
    
    public Menu(String pathMenu) throws IOException{
        loadMenu(pathMenu);
    }
    /**
     * @autor JS
     * @param pathFile
     * @throws IOException
     * 
     */    
    private void loadMenu (String pathFile) throws IOException{
        menu = LoadFileText.loadFile(pathFile);
    }
    
    public String toString(){
        String ret="";
        for (int i=0; i<menu.size(); i++){
            ret+=menu.get(i).toString();
        }
        return ret;
    }
    
    
}
