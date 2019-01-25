/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FFRAG;
import java.util.Date;

/**
 *
 * @author 21611924
 */
public class Parier {
    private Parieur parieur;
    private float mise;
    private String pilote;
    private float cotation;
    private Etape etape;
   
    
    public Parier(Parieur parieur, String Pilote, Etape etape, float mise,float cotation){
        this.parieur = parieur;
        this.pilote = Pilote;
        this.mise = mise;
        this.cotation = cotation;
        this.etape = etape;
        
        }
    
    public Parieur getParieur(){
        return this.parieur;
    }
    
    public Float getCote(){
        return this.cotation;
    }
    
    public String getPilote(){
        return this.pilote;
    }
    public Etape getEtape(){
        return this.etape;
    }
   
    public Float getMise(){
        return this.mise;
    }
    //verifier si une etape fini
    public boolean getEtapeFini(){
        boolean fini = false;
        if(this.etape.getClassement().isEmpty()){
            fini = false;
        }else{
            fini = true;
        }
        return fini;
    }
    //verifier si le pilote est le 1er apres l'etape
    public float getGain(){
        if(this.etape.getClassement().get(1).getParticipant().getCoureur().toString().equals(this.pilote)){
            return (float)this.mise * this.cotation;
        }else{
            return 0;
        }
        
        
        
        
        
        
    }
    
}
