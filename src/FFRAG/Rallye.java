package FFRAG;

/**
 *
 * 
 */

import java.util.ArrayList;

public class Rallye {
    private String nom;
    private String lieu;
    private ArrayList<Edition> listEdition;
    
    public Rallye(String nom, String lieu){
        this.nom = nom;
        this.lieu = lieu;
        this.listEdition = new  ArrayList<Edition>();
    }


    public String getNom(){
        return this.nom;
    }
    
    public String getLieu(){
        return this.lieu;
    }
    
    public ArrayList<Edition> getListEdition(){
        return this.listEdition;
    }
    
    //ajouter une edition dans un rallye
    public void organisateur(Edition edition){
    	this.listEdition.add(edition);
    }
    
    public String toString(){
 	String res = this.nom + " a lieu dans " + this.lieu;
 	return res;
 }

 
}
