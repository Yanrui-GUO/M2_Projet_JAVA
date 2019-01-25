/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTools;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author ELITEBOOK
 */
public class DataWriter {
    FileWriter fileWriter;
    public DataWriter() {
    }
    public void writeCsv(File file,ArrayList<String>aL){
        
        FileWriter fileWriter = null;
        Date date = new Date();
        DataReader dR = new DataReader();
        String attributes = "";
        for (String s : aL){
            attributes += s +";";
        }
        try {
            fileWriter= new FileWriter(file,true);
            fileWriter.append(attributes+"\n");
        }catch (Exception ex) {
           ex.printStackTrace();
        }finally{
            try{
                fileWriter.flush();
                fileWriter.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
  
    }


    // supprimer tous les informations dans CSV
    public static void clearCsv(File file, boolean existe) throws Exception { 	
    	if(existe == true) {
    		FileWriter fw = new FileWriter(file, false); 
        PrintWriter pw = new PrintWriter(fw, false); 
        pw.flush(); 
        pw.close(); 
        fw.close();
    	}
       } 
    
    // Verifier CSV est null ou pas
    public boolean existeInfo(File file) {
    	boolean existe = false;
    	DataReader dr = new DataReader();
    	ArrayList<String[]> res2 = dr.getTable1(file);
        for(String[] tab :res2){
            if(tab[0]!=null){
            	existe = true;
            } 
        }  	
    	return existe;
    }
    
}
