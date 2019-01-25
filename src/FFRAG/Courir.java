package FFRAG;

import java.util.Date;

/**
 *
 * @author Gold Safari Group Consulting
 */
public class Courir {

    private int tempsEtape;
    private Participant participant;
    private Etape etape;
    private int tempsCorrige;

    public Courir(Participant participant, Etape etape, Date temps) {
        this.participant = participant;
        this.etape = etape;
        this.tempsEtape = getSeconde(temps);
        this.tempsCorrige = (int) (this.tempsEtape * this.participant.getVehicule().coefCorrectifV);
    }

    public int getTempsEtape() {
        return this.tempsEtape;
    }

    public Participant getParticipant() {
        return this.participant;
    }

    public Etape getEtape() {
        return etape;
    }

    public int getTempsCorrige() {
        return this.tempsCorrige;
    }

    // change le format de TEMPS => nb Secondes
    public int getSeconde(Date tempsE){
        int hh = tempsE.getHours();
        int mm = tempsE.getMinutes();
        int ss = tempsE.getSeconds(); 
        this.tempsEtape = hh * 3600 + mm *60 +ss;
        return this.tempsEtape;
    }
    
    // nb Secondes => Temps
    public String changeFormat(int secondes){
        int hh = (int) (secondes / 3600);
        int mm =(int) ((secondes % 3600) / 60) ;
        int ss = (int) ((secondes % 3600) % 60);
        return hh+":"+mm+":"+ss;
    }
    
    // toString 
    public String toString() {
    	return this.getParticipant().getCoureur().toString() + "  " + this.changeFormat(this.getTempsEtape());
    }
    
    public String toStringTempsCorrige() {
    	return this.getParticipant().getCoureur().toString() + "  " + this.changeFormat(this.tempsCorrige);
    }
    
    public String toStringTempsEtape() {
    	return this.getParticipant().getCoureur().toString() + "  " + this.changeFormat(this.tempsEtape);
    }
}
