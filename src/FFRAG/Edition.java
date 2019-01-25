package FFRAG;

import java.text.SimpleDateFormat;
/**
 *
 * @author Gold Safari Group Consulting
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class Edition {

    private UUID noEdition;
    private Date dateDeb;
    private Date dateFin;
    private Rallye rallye;
    private ArrayList<Etape> listEtape;
    private ArrayList<Participant> listParticipant;
    private HashMap<Integer, Participant> classement;
   
    //CONSTRUCTEUR
    public Edition(Rallye rallye,Date dateDeb, Date dateFin) {
    	
        //creer un noEdition automatiquement
        this.noEdition = UUID.randomUUID();
        this.rallye = rallye;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.listEtape = new ArrayList<Etape>();
        this.listParticipant = new ArrayList<Participant>();
        this.classement = new HashMap<Integer, Participant>();
        
    }

    // get set
    
    public UUID getNo() {
        return this.noEdition;
    }
    
    public Rallye getRallye() {
        return this.rallye;
    }
    public Date getDateDeb() {
        return this.dateDeb;
    }

    public Date getDateFin() {
        return this.dateFin;
    }

    public ArrayList<Etape> getListEtape() {
    	return this.listEtape;
    }

    public ArrayList<Participant> getListParticipant() {
        return this.listParticipant;
    }

    // Ajout particiapnt a une liste de participants
    public void addParticipant(Participant par) {
        this.listParticipant.add(par);
    }
    
    // Ajout une etape a une liste d'etapes
    public void addEtape(Etape etape) {
        this.listEtape.add(etape);
    }

    public void CalculerClassement() {       
    	Collections.sort(this.listParticipant, new Sortbyroll());
    }
    
    public void ValiderClassement() {
    	if(!this.classement.isEmpty()) {
        	this.classement.clear();
        }
    	int i = 1;
        for (Participant p : this.listParticipant) {
            this.classement.put(i++, p);
        }
    }
    
    // toString
    public String toString() {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
        String res = " de " + dateFormat.format(this.dateDeb)+ " a " + dateFormat.format(this.dateFin);

        return res;
    }
    
    public String toStringListEtape() {
    	String r = "La liste des etapes : ";
    	for(Etape e:this.listEtape) {
    		r += "\n   " + e.toString();
    	}
    	return r;
    }
   
}
