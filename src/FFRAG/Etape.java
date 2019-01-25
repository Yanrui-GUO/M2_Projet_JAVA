package FFRAG;

/**
 *
 * @author Gold Safari Group Consulting
 */

import dataTools.DataWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;


public class Etape {
    
    private Edition edition;
    private int codeEtape;
    private float distance;
    private int nbVirage;
    
    private ArrayList<Courir> listCourir;
    private HashMap<Integer, Courir> classement;
    private HashMap<Coureur, Float> listCotation;
    private ArrayList<Parier> listeParierEtape;
    private ArrayList<HashMap.Entry<Participant,Float>> classementProbable;
    private File fileT = new File("src/CSV/TempsProbable.csv");
    DataWriter dW = new DataWriter();
    
    public Etape(Edition edition, int codeEtape, float distance, int nbVirage){
        this.edition = edition;
        this.codeEtape = codeEtape;
        this.distance = distance;
        this.nbVirage = nbVirage;
        
        this.classement = new HashMap<Integer, Courir>();
        this.listCourir = new ArrayList<Courir>();
        this.listCotation = new HashMap<Coureur, Float>();
        this.listeParierEtape = new ArrayList<Parier>();
                
    }

    public int getCodeE() {
        return this.codeEtape;
    }

    public float getDistance() {
        return this.distance;
    }

    public int getNbVirage() {
        return this.nbVirage;
    }
    
    public Edition getEdition(){
        return this.edition;
    }
           
    
    public ArrayList<Courir> getListCourir() {
        return this.listCourir;
    }
    
    public HashMap<Coureur, Float> getCotation(){
        return this.listCotation;
    }


    //ajouter courir dans une etape
    public void addCourir(Courir courir) {
        this.listCourir.add(courir);
    }
    
     public void addParier(Parier p){
        this.listeParierEtape.add(p);
    }
     
     public ArrayList<Parier> getListeParierEtape(){
        return this.listeParierEtape;
    }
     
    public HashMap<Integer, Courir>  getClassement() {
        return this.classement;
    }
     
   
    
    // classement par etape ==> tempsCorrige dans la classe Courir
    public void calculerClassement() {
       Collections.sort(this.listCourir, new SortCourirbyroll());
    }
    
    //enregistrement la nouvelle liste de courirs dans la classement par etape
    public void validerClassement() {  	
	if(!this.classement.isEmpty()) {
            this.classement.clear();
        }
    	int i = 1;
        for (Courir c : this.listCourir) {
            this.classement.put(i++, c);
            switch (i) {
                case 1:
                    c.getParticipant().getCoureur().addRecord(50);
                    break;
                case 2:
                    c.getParticipant().getCoureur().addRecord(30);
                    break;
                case 3:
                    c.getParticipant().getCoureur().addRecord(20);
                    break;
                case 4:
                    c.getParticipant().getCoureur().addRecord(10);
                    break;
                case 5:
                    c.getParticipant().getCoureur().addRecord(5);
                    break;
                case 6:
                    c.getParticipant().getCoureur().addRecord(3);
                    break;
                case 7:
                    c.getParticipant().getCoureur().addRecord(1);
                    break;
                default:
                    break;
            }
            
            
        }
    }
    
    //calculer classement probable
    public void getClassementProbable(){
        HashMap<Participant,Float> tempsProbable = new HashMap<Participant,Float>();
        
        float adherence=0,niveau=0,coefpuissance=0;
        float coefNbVirage = 0;
        
        coefNbVirage = (float)-0.0072*this.nbVirage;
        
        for(Participant p : this.edition.getListParticipant()){
           switch (p.getCoureur().getNiveau()) {
                case "expert":
                    niveau = (float)3.399;
                    break;
                case "junior":
                    niveau = (float)0.63;
                    break;
                default:
                    niveau = 0;
                    break;
            }
            
            adherence = p.getVehicule().puissanceV;
            coefpuissance = p.getVehicule().coefCorrectifV;
            
            double temps = (float)this.distance/(50.441+coefpuissance+niveau+1.7155*adherence+coefNbVirage);
            
            tempsProbable.put(p, (float)Math.round(temps));
            
        }
        
        
        Set<HashMap.Entry<Participant,Float>> entryset = tempsProbable.entrySet();
        
        this.classementProbable = new ArrayList<HashMap.Entry<Participant,Float>>(entryset);
        Collections.sort(classementProbable, new Comparator<HashMap.Entry<Participant,Float>>(){
            public int compare(HashMap.Entry<Participant,Float> c1,HashMap.Entry<Participant, Float> c2){
                return c1.getValue().compareTo(c2.getValue());
            }
        });
            
        //conserver dans csv
        for(int i=0; i<classementProbable.size();i++){
            ArrayList<String> aL; 
            aL = new ArrayList<String>();
            aL.add(String.valueOf(classementProbable.get(i).getKey().getCoureur().toString()));
            aL.add(String.valueOf(classementProbable.get(i).getValue()));
            dW.writeCsv(fileT, aL);
        }
        
        
    }
    
    //calculer cotation
    public void cotation() {
        // cotation initial = 1. cotation final = cotationInitial + base1*base2*base3
        float cotation = 1, base1 = 1,base2=1,base3=1; 
        
        // tous les coureurs qui participent  = this.listCourir
        ArrayList<Coureur> listCoureur = new ArrayList<Coureur>();
        for(Participant p :this.edition.getListParticipant()){
            listCoureur.add(p.getCoureur());
        }
        
        // cotation
        for(int i= 0;i<listCoureur.size();i++){
            /* base 1 : classement probable
             * on distingue le classement probable en 5 partie, chaque est 20%,30% , 35%, 10% et  5% de l'ensemble. 
             * Et le coefficient de chaque partie est 15,20,30,50 et 100. base 1 =  coeff * classement / nbtotal
             */
          int length = this.classementProbable.size();
            for(int j = 0 ; j < length; j++){
                if(!classementProbable.isEmpty()){
                    if(listCoureur.get(i).equals(classementProbable.get(j).getKey().getCoureur())){ 
                        float position = (float) (j+1) / length;
                        if(position<=0.2){
                             base1 = position * 15;
                        }else if(0.2<position && position<=0.5){
                            base1 = position * 20;
                        }else if(0.5<position&& position<=0.85){
                            base1 = position * 30;
                        }else if(0.85<position&&position<=0.95){
                            base1 = position * 50;
                        }else{
                            base1 = position * 100;
                        }
                        
                    }
                }
            } 
            
         /* base 2 : pourcentage de nombre de  parieur 
      	 * pourcentage = nombre de parieur pour un participant donne divise l'ensemble de nombre de parieur
      	 * 10%, cotation va multiplier base 2 (= 0.9)
      	 */
                int nbParieur = 0,  sommeParieur = this.listeParierEtape.size();  
                if(sommeParieur != 0){
                    for(Parier p:this.listeParierEtape){
                        if(p.getPilote() == listCoureur.get(i).toString()){
                            nbParieur ++;
                        }
                    }
                int puissance = 10*nbParieur / sommeParieur;
                if( puissance > 0){
                    base2 = (float)Math.pow(0.9,puissance);
                }
                }
                
                /* base3 : Record de champion
                * intervalle =0    <10    <30   <=50
                * coef     1.5   0.9    0.7    0.5
                */ 
                float record = listCoureur.get(i).calRecordMoyenne();
                    if(record >= 30){
                        base3 = (float)0.5;
                    }else if(record >=10 && record < 30){
                        base3 = (float)0.7;
                    }else if(record >0 && record<10){
                        base3 = (float)0.9;
                    }else{
                        base3 = (float)1.5;
                 }  
                    
                    
        cotation =1.01F +  base1* base2*base3;
       // System.out.print(base1 + "---");
        
        this.listCotation.put(listCoureur.get(i), cotation); 
        }
        
    }  
    
    
    
    
    
    // toString 
    public String toString() {
    	return  this.codeEtape + "   " +this.distance + " " + this.nbVirage;
    }
    
}
