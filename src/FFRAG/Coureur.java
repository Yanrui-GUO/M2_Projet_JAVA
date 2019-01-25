package FFRAG;


import dataTools.DataWriter;
import java.io.File;
import java.text.SimpleDateFormat;
/**
 *
 * @author fhamzaoui
 */
import java.util.ArrayList;
import java.util.Date;

public class Coureur {

    private String nomCoureur;
    private String prenomCoureur;
    private Date dateNaissance;
    private String niveauExperience;
    private ArrayList<Participant> listParticipant;
    //conserver le record de chaque participation
    private ArrayList<Integer> listRecord;
    private float recordFinal;

    public Coureur(String nom, String prenom, Date date, String niveau) {
        this.nomCoureur = nom;
        this.prenomCoureur = prenom;
        this.dateNaissance = date;
        this.niveauExperience = niveau;
        this.listParticipant = new ArrayList<Participant>();
        
        this.recordFinal = 0;        
        this.listRecord = new ArrayList<Integer>();
    }
    
	//get set
    public String getNom() {
        return this.nomCoureur;
    }

    public String getPrenom() {
        return this.prenomCoureur;
    }

    public Date getDateNai() {
        return this.dateNaissance;
    }
    public String getNiveau() {
	return this.niveauExperience;
    }
    
    
    //ajouter l'inscription
    public void inscrire(Participant participant) {
        this.listParticipant.add(participant);
    }
    
    
    public ArrayList<Integer> getListRecord(){
        return this.listRecord;
    }
    
    public void addRecord(int record){
        this.listRecord.add(record);
    }

    public float calRecordMoyenne(){
        if(!this.listRecord.isEmpty()){
            for(int record:this.listRecord){
                this.recordFinal += record;
            }
            return (float)this.recordFinal / this.listRecord.size();
        }else{
            return 0;
        }
	
    }
    
  //toString
    public String toString() {
    	
    	
    	return  this.nomCoureur  + this.prenomCoureur;
    }
    
    
}
