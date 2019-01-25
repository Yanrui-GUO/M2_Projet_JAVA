/*
 * 
 */
package FFRAG;

import dataTools.DataWriter;
import java.io.File;
import java.util.ArrayList;


/**
 *
 * @
 */
public class Vehicule {
   
    protected String NomVehicule;
    protected int puissanceV;
    protected float poidsV;
    protected float coefCorrectifV;
    protected double adherenceV;
    
    public Vehicule( String nom, int puis, float poids){
     
        this.NomVehicule = nom;
        this.puissanceV = puis;
        this.poidsV = poids;
        this.adherenceV = (float)(poids * 0.001);
        this.coefCorrectifV = (float) (0.0143 * this.puissanceV);
        
    }
    
    public String getNom(){
        return this.NomVehicule;
    }
    
    public int getPuissance(){
        return this.puissanceV;
    }
    
    public float getPoids(){
        return this.poidsV;
    }
 
   
}
