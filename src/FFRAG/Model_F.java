/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FFRAG;

import dataTools.DataReader;
import dataTools.DataWriter;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 *
 * @author guoyanrui
 */
public class Model_F {
    
    private ArrayList<Rallye> listRallye;
    private ArrayList<Participant> listParticipant;
    private ArrayList<Coureur> listCoureur;
    private ArrayList<Parieur> listParieur;
    private ArrayList<Vehicule> listVehicule; 
    
    private File fileR = new File("src/CSV/Rallye.csv");
    private File fileE = new File("src/CSV/Edition.csv");
    private File fileEtape = new File("src/CSV/Etape.csv");
    private File fileC = new File("src/CSV/Coureur.csv");
    private File filePp = new File("src/CSV/Participant.csv");
    private File filePreur = new File("src/CSV/Parieur.csv");
    private File filePrier = new File("src/CSV/Parier.csv");
    private File fileV = new File("src/CSV/Vehicule.csv");
    private File fileCB = new File("src/CSV/Bonascre.csv");
    private File fileCS = new File("src/CSV/SuperBesse.csv");
    private File fileCV = new File("src/CSV/ValThorens.csv");
    private File fileCFS = new File("src/CSV/ClassementFinalSup.csv");
    private File fileCFV = new File("src/CSV/ClassementFinalVAL.csv");
    
    DataReader dR = new DataReader();
    DataWriter dW = new DataWriter();
    
    public Model_F() throws ParseException{
        this.listCoureur = new ArrayList<Coureur>();
        this.listRallye = new ArrayList<Rallye>();
        this.listParieur = new ArrayList<Parieur>();
        this.listParticipant = new ArrayList<Participant>();
        this.listVehicule = new ArrayList<Vehicule>();
        
        
        this.ajouterEdition();
        this.ajouterParticipant();
        this.ajouterParieur();
        this.ajouterCourir();
        
    }
    
    public ArrayList<Rallye> getListRallye() throws ParseException{
        return this.listRallye;
    }
    
    public ArrayList<Coureur> getListCoureur(){
        return this.listCoureur;
    }
    
    public ArrayList<Parieur> getListParieur(){
        return this.listParieur;
    }
    public ArrayList<Vehicule> getListVehicule(){
        return this.listVehicule;
    }
    
    public ArrayList<Participant> getListParticipant(){
        return this.listParticipant;
    }
    
    
    //inserer les rallyes
    
    public void ajouterEdition() throws ParseException{
        ArrayList<String[]> rallye =dR.getTable1(fileR);
        ArrayList<String[]> edition =dR.getTable1(fileE);
        ArrayList<String[]> etape =dR.getTable1(fileEtape);
        
        //date format
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        //ajouter rallyes
        for(String[] tabR :rallye){
            if(tabR[0] !=null){
                Rallye r = new Rallye(tabR[0],tabR[1]);
                this.listRallye.add(r);
                
                //ajouter edition
                for(String[] tabE :edition){
                    if(tabE[0] !=null && tabR[0].equals(tabE[0])){
                        Edition edi = new Edition(r,df.parse(tabE[1]),df.parse(tabE[2]));
                        //ajouter edition dans un rallye
                        r.organisateur(edi);
                        
                        //ajouter une etape dans une edition d'un rallye
                        for(String[] tabEtape :etape){
                            if(tabEtape[0] !=null && tabR[0].equals(tabEtape[0]) && tabE[1].equals(tabEtape[1])){
                                Etape eta = new Etape(edi,Integer.parseInt(tabEtape[2]),Float.parseFloat(tabEtape[3]),Integer.parseInt(tabEtape[4]));
                                edi.addEtape(eta);
                                
                            }
                        }
                    }
                }
            }
        }
    }
     
    
    //lire les donnees Coureur
    public void ajouterParticipant() throws ParseException{
        ArrayList<String[]> coureur =dR.getTable1(fileC);
        ArrayList<String[]> participant =dR.getTable1(filePp);
        ArrayList<String[]> vehicule =dR.getTable1(fileV);

        //date format
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        //ajouter vehicule
        for(String[] tabV : vehicule){
            Vehicule v = new Vehicule(tabV[0],Integer.parseInt(tabV[1]), Float.parseFloat(tabV[2]));
            this.listVehicule.add(v);
        }
        //ajouter coureur
        for(String[] tabC : coureur){
            if(tabC[0] !=null){
                Coureur cr= new Coureur(tabC[0],tabC[1],df.parse(tabC[2]),tabC[3]);
                this.listCoureur.add(cr);
          
                //ajouter participation
                for(String[] tabP :participant){
                    //ajouter participant dans dans coureur
                    if(tabP[0] !=null && tabP[3].equals(cr.getNom()) && tabP[4].equals(cr.getPrenom())){
                        
                        for(Vehicule v : this.listVehicule){
                            if(v.NomVehicule.equals(tabP[5])){
                                Participant part = new Participant(df.parse(tabP[2]),v,cr);
                                this.listParticipant.add(part);
                                cr.inscrire(part);
                                
                                //ajouter participant dans une edition
                                for(Rallye r : this.listRallye){
                                    if(r.getNom().equals(tabP[0])){
                                                for(Edition e : r.getListEdition()){
                                                    if(e.getDateDeb().compareTo(df.parse(tabP[1])) == 0){
                                                        e.addParticipant(part);
                                                    }
                                                }
                                            }  
                                }
                            }
                        }
                    }
                    
                    
                }
            }
        }
                        
    }
    
    //lire les donnees Parieur
    public void ajouterParieur() throws ParseException{
        ArrayList<String[]> parieur =dR.getTable1(filePreur);
        ArrayList<String[]> parier =dR.getTable1(filePrier);

        //date format
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        //ajouter coureur
        for(String[] tabPre : parieur){
            if(tabPre[0] !=null){
                Parieur pre = new Parieur(tabPre[0],tabPre[1],tabPre[2],tabPre[3],tabPre[4],tabPre[5],tabPre[6]);
                this.listParieur.add(pre);
                
                for(String[] tabPri : parier){
                    if(tabPri[0] !=null && tabPri[0].equals(tabPre[0])){
                        
                        for(Rallye r : this.listRallye){
                            if(r.getNom().equals(tabPri[2])){
                                for(Edition e : r.getListEdition()){
                                    if(e.getDateDeb().compareTo(df.parse(tabPri[3])) == 0){
                                        for(Etape eta :e.getListEtape()){
                                            if(String.valueOf(eta.getCodeE()).equals(tabPri[4])){
                                                Parier pri = new Parier(pre,tabPri[1],eta,Float.parseFloat(tabPri[5]),Float.parseFloat(tabPri[6]));
                                                pre.addParier(pri);
                                                eta.addParier(pri);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
                
        }         
        }
    }
    
    //lire les donnees Courir
    public void ajouterCourir() throws ParseException{
    
        ArrayList<String[]> courirB =dR.getTable1(fileCB);
        ArrayList<String[]> courirS =dR.getTable1(fileCS);
        ArrayList<String[]> courirV =dR.getTable1(fileCV);
        
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dh = new SimpleDateFormat("hh:mm:ss");
        //ajouter courir du rally Bonascre
        for(Rallye r : this.listRallye){
            
            if(r.getNom().equals("Bonascre")){
                System.out.print(r.getNom());
                for(Edition e : r.getListEdition()){
                    if(df.format(e.getDateDeb()).equals("10/10/2018")){
                        
                        for(Etape eta :e.getListEtape()){
                            int codeEtape = eta.getCodeE();
                            for(String[] tabB : courirB){
                                for(Participant p : this.listParticipant){
                                    String pilote = p.getCoureur().getNom() + " " + p.getCoureur().getPrenom();
                                    if(pilote.equals(tabB[0])&&r.getNom().equals(r.getNom())){
                                        Courir cour = new Courir(p,eta,dh.parse(tabB[codeEtape+2]));
                                        p.addCourir(cour);
                                        eta.addCourir(cour);
                                        
                                        
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
      
    
        //ajouter courir du rally SuperBesse
        for(Rallye r : this.listRallye){
            if(r.getNom().equals("SuperBesse")){
                for(Edition e : r.getListEdition()){
                    if(df.format(e.getDateDeb()).equals("09/12/2017")){
                        for(Etape eta :e.getListEtape()){
                            int codeEtape = eta.getCodeE();
                            for(String[] tabS : courirS){
                                for(Participant p : this.listParticipant){
                                    if(p.getCoureur().getNom().equals(tabS[0]) && p.getCoureur().getPrenom().equals(tabS[1])){
                                        Courir cour = new Courir(p,eta,dh.parse(tabS[codeEtape+3]));
                                        eta.addCourir(cour);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
       
        //ajouter courir du rally SuperBesse
        for(Rallye r : this.listRallye){
            if(r.getNom().equals("ValThorens")){
                for(Edition e : r.getListEdition()){
                    if(df.format(e.getDateDeb()).equals("08/12/2017")){
                        for(Etape eta :e.getListEtape()){
                            int codeEtape = eta.getCodeE();
                            for(String[] tabV : courirV){
                                for(Participant p : this.listParticipant){
                                    if(p.getCoureur().getNom().equals(tabV[0]) && p.getCoureur().getPrenom().equals(tabV[1])){
                                        Courir cour = new Courir(p,eta,dh.parse(tabV[codeEtape+3]));
                                        eta.addCourir(cour);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
    }
        
    //enregistrer les donnees dans rallye.csv
    public void enregistrerRallye(String nomrallye,String lieu) {
        
        ArrayList<String> aL; 
        aL = new ArrayList<String>();
        aL.add(String.valueOf(nomrallye));
        aL.add(String.valueOf(lieu));
        dW.writeCsv(fileR, aL);
    }
    
    //enregistrer les donnees dans Edition.csv
    public void enregistrerEdition(String nomrallye,String datedeb,String datefin) {
        
        ArrayList<String> aL; 
        aL = new ArrayList<String>();
        aL.add(String.valueOf(nomrallye));
        aL.add(String.valueOf(datedeb));
        aL.add(String.valueOf(datefin));
        dW.writeCsv(fileE, aL);
    }
    
    //enregistrer les donnees dans etape.csv
    public void enregistrerEtape(String nomrallye,String datedeb,String codeEtape, String distance, String nbVirage) {
        
        ArrayList<String> aL; 
        aL = new ArrayList<String>();
        aL.add(String.valueOf(nomrallye));
        aL.add(String.valueOf(datedeb));
        aL.add(String.valueOf(codeEtape));
        aL.add(String.valueOf(distance));
        aL.add(String.valueOf(nbVirage));
        dW.writeCsv(fileEtape, aL);
    }
    
    //enregistrer les donnees dans vehicule.csv
    public void enregistrerVehicule(String idV, String vehicule, String puissance, String poids){
        ArrayList<String> aL; 
        aL = new ArrayList<String>();
        aL.add(idV);
        aL.add(vehicule);
        aL.add(puissance);
        aL.add(poids);
        dW.writeCsv(fileV, aL);
    }
      
    //enregistrer les donnees dans coureur.csv
    public void enregistrerCoureur(String nom, String prenom, String date, String niveau){
        ArrayList<String> aL; 
        aL = new ArrayList<String>();
        aL.add(nom);
        aL.add(prenom);
        aL.add(date);
        aL.add(niveau);
        dW.writeCsv(fileC, aL);
    }
    
    //enregistrer les donnees dans participant.csv
    public void enregistrerParticipant(String rallye,String dateDeb,String dateIns,String nomC,String prenomC,String nomVehicule){
        ArrayList<String> aL; 
        aL = new ArrayList<String>();
        aL.add(rallye);
        aL.add(dateDeb);
        aL.add(dateIns);
        aL.add(nomC);
        aL.add(prenomC);
        aL.add(nomVehicule);
        dW.writeCsv(filePp, aL);
    }
    
    //enregistrer les donnees dans parieur.csv
    public void enregistrerParieur(String email, String motdepasse, String nom, String prenom, String age,String adresse, String iban){
        ArrayList<String> aL; 
        aL = new ArrayList<String>();
        aL.add(email);
        aL.add(motdepasse);
        aL.add(nom);
        aL.add(prenom);
        aL.add(age);
        aL.add(adresse);
        aL.add(iban);
        
        dW.writeCsv(filePreur,aL);
    }
    
    //enregistrer les donnees dans parier.csv
    public void enregistrerParier(String email, String Pilote, String nomRallye, String dateDeb, String codeEtape, String mise,String cote,String etat){
        ArrayList<String> aL; 
        aL = new ArrayList<String>();
        aL.add(email);
        aL.add(Pilote);
        aL.add(nomRallye);
        aL.add(dateDeb);
        aL.add(codeEtape);
        aL.add(mise);
        aL.add(cote);
        aL.add(etat);
        dW.writeCsv(filePrier,aL);
    }
    
    //transforme l'heure en heure minute et seconde
    public String transformerHeure(String temps){
        int hh = (int)Math.floor(Double.valueOf(temps));
        int mm = (int)Math.floor((Double.valueOf(temps) - hh)*60);
        int ss = (int)Math.floor(((Double.valueOf(temps) - hh)*60-mm)*60);
        
        return hh+":"+mm+":"+ss;
    }
}
