/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FFRAG;

import java.util.ArrayList;

/**
 *
 * @author 21611924
 */
public class Parieur {
    private String nom;
    private String prenom;
    private String age;
    private String adresse;
    private String iban;
    private String email;
    private String motdepass;
    private ArrayList<Parier> listeParier;
    
    
    public Parieur(String email, String motdepass, String nom, String prenom,String age, String adresse, String iban){
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.adresse = adresse;
        this.iban = iban;
        this.email = email;
        this.motdepass = motdepass;
        this.listeParier = new ArrayList<Parier>();
    }
    
    public ArrayList<Parier> getListeParier(){
        return this.listeParier;
        
    }
    
    public void addParier(Parier p){
        this.listeParier.add(p);
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getMotdePasse(){
        return this.motdepass;
    }
    public String getNom(){
        return this.nom;
    }
    public String getPrenom(){
        return this.prenom;
    }
    public String getAge(){
        return this.age;
    }
    public String getAdresse(){
        return this.adresse;
    }
    public String getiban(){
        return this.iban;
    }
    
}
