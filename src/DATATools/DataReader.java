/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;


/**
 *
 * @author ELITEBOOK
 */
public class DataReader {
    private int g;
    private String[][] tab;
    private ArrayList<String[]> tab1;
    
    public DataReader(){
        this.g = 1;
    }
    
    
     public ArrayList<String[]> getTable1(File file){
        
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            int i =0;
            int j = 0;
            String line;
            this.tab1 = new ArrayList<>();
            
             
            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(";");
                String[] localTab = new String[16];
                    while (scanner.hasNext()){
                        String data = scanner.next();
                        localTab[j] = data;
                        j++;
                    }
                j = 0;
                tab1.add(localTab);
                i++;
            }
            reader.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             return this.tab1;
     }
    
    
}
