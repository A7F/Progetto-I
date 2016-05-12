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
    private ArrayList<MenuElement> out = new ArrayList<>();
    
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

    /**
     * 
     * @autor FabioTagliani
     */
    public ArrayList<MenuElement> getMenu() {
        return menu;
    }
        
    public String toString(){
        String ret="";
        for (int i=0; i<menu.size(); i++){
            ret+=menu.get(i).toString()+"\n";
        }
        return ret;
    }
    
    /**
     * Ritorna tutti gli elementi di menu che abbiano lo stesso nome passato al metodo
     * In sostanza è un cerca.
     * @author Luca :]
     * @param elementName
     * @return out
     */
    public ArrayList<MenuElement> getElementByName(String elementName){
        elementName=elementName.toLowerCase();
        
        for(int i=0;i<menu.size();i++){
            String temp = menu.get(i).getNameElement();
            if(elementName.equals(temp.toLowerCase())){
                out.add(menu.get(i));
            }
        }
        
        return out;
    }
    
    /**
     * Ritorna tutti gli elementi di menu che abbiano lo stesso tipo
     * In sostanza è un cerca.
     * @author Luca :]
     * @param elementName
     * @return out
     */
    public ArrayList<MenuElement> getElementByType(String type){
        type=type.toLowerCase();
        
        for(int i=0;i<menu.size();i++){
            String temp = menu.get(i).getTypeElement();
            if(type.equals(temp.toLowerCase())){
                out.add(menu.get(i));
            }
        }
        
        return out;
    }
}
