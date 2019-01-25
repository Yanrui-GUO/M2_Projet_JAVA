package FFRAG;
import java.text.SimpleDateFormat;
/**
 *
 * @author Gold Safari Group Consulting
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


public class Participant {
    private UUID noIns;
    private Date dateIns;
    private float tempsFinal;
    private Vehicule vehicule;
    private Coureur coureur;
    private ArrayList<Courir> listCourir;
    
    public Participant(Date dateIns,Vehicule vehicule,Coureur coureur){
        this.noIns = UUID.randomUUID();
        this.dateIns = dateIns;
        this.vehicule = vehicule;
        this.coureur = coureur;
        this.listCourir = new ArrayList<Courir>();
    }
    
    public UUID getNoIns(){
        return this.noIns;
    }
    public Date getDateIns(){
        return this.dateIns;
    }
    public float getTempsFinal(){
        return this.tempsFinal;
    }
    public Vehicule getVehicule(){
        return this.vehicule;
    }
    public Coureur getCoureur() {
    	return this.coureur;
    }
    
    //Ajout d'un participant a la list de depart
    public void addCourir(Courir courir){
       this.listCourir.add(courir);
    }
    
    public float calculeTempsFinal(){
        this.tempsFinal = 0;
        for(Courir c:this.listCourir){
            this.tempsFinal += c.getTempsCorrige();
        }
        return this.tempsFinal;
    }
    
    public String changeFormat(float secondes){
        int hh = (int) (secondes / 3600);
        int mm =(int) ((secondes % 3600) / 60) ;
        int ss = (int) ((secondes % 3600) % 60);
        return hh+":"+mm+":"+ss;
    }

}
