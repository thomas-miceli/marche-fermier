package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.Seeding;
import fr.univamu.iut.marche.traitement.acteurs.Traders.Trader;
import fr.univamu.iut.marche.traitement.produits.Identificateur;
import fr.univamu.iut.marche.traitement.produits.*;
import fr.univamu.iut.marche.traitement.remises.Strategy;

import java.util.ArrayList;

//import static fr.univamu.iut.marche.traitement.Main.ANSI_RED;

/**
 * @author Yann FORNER
 * @author Andrea GARCIA
 * @author Thomas MICELI
 * @author Téo MARTIN
 */
public abstract class Participant {
    public Trader trader=null;
    public enum Produits {
        COCHON,
        FROMAGE,
        LAIT,
        MIEL,
        ORANGE,
        POMME,
        VACHE
    }

    protected int id = 0;
    protected String nom;
    protected String prenom;
    protected int age;
    private ArrayList<ProduitFermier> stock = new ArrayList<>();
    protected double solde = 0;

    protected static ArrayList<Participant> listeParticipant = new ArrayList<>();

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

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void opArgent(double montant) {
        this.solde += montant;
    }

    public boolean canBuy(double argent) {
        return !((this.solde -= argent) < 0);
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

    public ArrayList<ProduitFermier> getStock() {
        return stock;
    }

    public void setStock(ArrayList<ProduitFermier> stock) {
        this.stock = stock;
    }

    public void addProduit(ProduitFermier produit) {
        this.stock.add(produit);
        setStock(Seeding.compilerProduits(this.stock));
    }

    public void removeProduit(ProduitFermier produit) {
        this.stock.remove(produit);
    }

    public ProduitFermier getProduit(Produits prod) {
        ProduitFermier produitFermier = null;
        for (ProduitFermier produ : stock) {
            if (produ.getClass().getSimpleName().toUpperCase().equals(prod.name())) {
                produitFermier = produ;
            }
        }
        return produitFermier;
    }

    public void vendreProduit(Produits produitAVendre, Integer quantite, Double prix,Marche marche) {
        Identificateur identificateur = new Identificateur();
        for (ProduitFermier p: stock) {
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
                            break;
                        default:
                            pTemp=null;
                            break;
                    }
                    pTemp.setQuantite(quantite);
                    Controlleur c = new Controlleur();
                    if(c.validerOffre(pTemp,"validé")){
                        new Vente(pTemp,this,prix,marche);
                        if(p.getQuantite()-quantite==0){
                            stock.remove(p);
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

    public void proposerOffre(Produits produitAAcheter, Integer quantite, Double prix,Marche marche){
        if(solde >= prix ){
            new Offre(produitAAcheter,this,prix,quantite,marche);
        }else{
            System.out.println(nom+" n'a pas assez d'solde pour placer cette offre");
        }
    }

    public void calculerCotisations(Strategy... strategies) {
        double remises = 0;
        for (Strategy s : strategies) {
            if (s.calcRemise(this) != 0)
                remises += s.calcRemise(this);
        }
        System.out.println(remises);
        opArgent(0 - (this.solde * ((15 - (remises/100))/100)));
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }
}
