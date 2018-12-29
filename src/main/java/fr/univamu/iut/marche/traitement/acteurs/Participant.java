package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.Identificateur;
import fr.univamu.iut.marche.traitement.produits.*;

import javax.naming.PartialResultException;
import java.util.ArrayList;
import java.util.List;

//import static fr.univamu.iut.marche.traitement.Main.ANSI_RED;

/**
 * @author Yann FORNER
 * @author Andrea GARCIA
 * @author Thomas MICELI
 * @author Téo MARTIN
 */
public abstract class Participant {

    public enum Produits {
        COCHON,
        FROMAGE,
        LAIT,
        MIEL,
        ORANGE,
        POMME,
        VACHE
    }

    private ArrayList<ProduitFermier> produitsEnStock = new ArrayList<>();


    protected int id = 0;
    protected String nom;
    protected String prenom;
    protected int age;
    protected static ArrayList<Participant> listeParticipant = new ArrayList<>();

    protected double argent = 0;

    public Participant(String nom, String prenom, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;

        id = listeParticipant.size()+1;
        listeParticipant.add(this);
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getArgent() {
        return argent;
    }

    public void setArgent(double argent) {
        this.argent = argent;
    }

    public void opArgent(double argent) {
        this.argent += argent;
    }

    public boolean canBuy(double argent) {
        return !((this.argent -= argent) < 0);
    }

    public static ArrayList<Participant> getAllParticipants(){
        return listeParticipant;
    }

    public static Participant getParticipantbyId(int id){
        for (Participant participant : listeParticipant) {
            if(participant.getId()==id) return participant;
        }
        return null;
    }



    public ArrayList<ProduitFermier> getProduits() {
        return produitsEnStock;
    }


    public void setProduits(ArrayList<ProduitFermier> produits) {
        this.produitsEnStock = produits;
    }

    public void addProduit(ProduitFermier produit) {
        this.produitsEnStock.add(produit);
    }

    public void removeProduit(ProduitFermier produit) {
        this.produitsEnStock.remove(produit);
    }



    public ProduitFermier getProduit(Produits prod) {
        ProduitFermier produitFermier = null;
        for (ProduitFermier produ : produitsEnStock) {
            if (produ.getClass().getSimpleName().toUpperCase().equals(prod.name())) {
                produitFermier = produ;
            }
        }
        return produitFermier;
    }

    public void vendreProduit(Produits produitAVendre, Integer quantite, Integer prix,Marche marche) {
        Identificateur identificateur = new Identificateur();
        for (ProduitFermier p: produitsEnStock) {
            if(p.identifier(identificateur).equals(produitAVendre)){
                if(p.getQuantite()>=quantite){
                    ProduitFermier pTemp;
                    switch (p.getClass().getSimpleName()){
                        case "Miel":
                            pTemp = new Miel(p);
                            break;
                        case "Vache":
                            pTemp = new Vache(p);
                            break;
                        case "Pomme":
                            pTemp = new Pomme(p);
                            break;
                        case "Orange":
                            pTemp = new Orange(p);
                            break;
                        case "Lait":
                            pTemp = new Lait(p);
                            break;
                        case "Fromage":
                            pTemp = new Fromage(p);
                            break;
                        case "Cochon":
                            pTemp = new Cochon(p);
                        default:
                            pTemp=null;
                            break;
                    }
                    pTemp.setQuantite(quantite);
                    Controlleur c = new Controlleur();
                    if(c.validerOffre(pTemp,"validé")){
                        new Vente(pTemp,this,prix,marche);
                        if(p.getQuantite()-quantite==0){
                            produitsEnStock.remove(p);
                        }else{
                            p.setQuantite(p.getQuantite()-quantite);
                        }
                        System.out.println("la vente c'est bien passée");
                    }else{
                        System.out.println("le controle de l'objet c'est mal passé");
                    }


                }
            }
        }

    }
    public void proposerOffre(Produits produitAAcheter, Integer quantite, Integer prix,Marche marche){
        if(argent >= prix ){
            new Offre(produitAAcheter,this,prix,quantite,marche);
        }else{
            System.out.println(nom+" n'a pas assez d'argent pour placer cette offre");
        }
    }
}
