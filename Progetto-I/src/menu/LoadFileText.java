package menu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Javier
 */
public class LoadFileText {
    
    /**
     * 
     * @param String
     * @return ArrayList of MenuElements
     * @throws FileNotFoundException
     * @throws IOException 
     * Questo metodo prende un file di testo che deve essere formatato in questo modo:
     * String(TAB)double(TAB)menuElementType(TAB)String
     */
    public static ArrayList<MenuElement> loadFile (String pathFile) throws FileNotFoundException, IOException{
        ArrayList<MenuElement> menuElements = new ArrayList<>(); 
        FileReader fr = new FileReader(pathFile);
        BufferedReader br = new BufferedReader(fr);
        while(br.ready()){
            String line = br.readLine();
            String[] splittedLine = line.split("\t");
            Double price = new Double(splittedLine[1]);
            //MenuElementType menuElementType = MenuElementType.valueOf(splittedLine[3]);
            menuElements.add(new MenuElement(splittedLine[0], price, splittedLine[2],splittedLine[3]));
        }
        return menuElements;
    }
    
}
