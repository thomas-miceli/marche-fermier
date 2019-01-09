package fr.univamu.iut.marche.traitement.acteurs.Traders;

import fr.univamu.iut.marche.traitement.acteurs.Marche;
import fr.univamu.iut.marche.traitement.acteurs.Participant;

import java.util.ArrayList;
/**
 * @author Téo MARTIN
 * @author Yann FORNER
 * @author Dylan MARCH
 */
public class Trader {
    private String name;
    private String prenom;
    private int age;
    private Double solde=0.0;
    private ArrayList<Participant> clients = new ArrayList<>();
    private static ArrayList<Trader> allTraders = new ArrayList<>();

    public Trader(String name) {
        this.name = name;
    }

    public Trader(String name, String prenom, int age) {
        this.name = name;
        this.prenom = prenom;
        this.age = age;
        allTraders.add(this);
    }

    public static ArrayList<Trader> getAllTraders(){
        return allTraders;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setAge(int age) {
        this.age = age;
    }


    public void setSolde(double solde) {
        this.solde = solde;
    }

    /**
     * ajoute une client à la liste clients
     * @param p
     */
    public void addClients (Participant p){
        clients.add(p);
        p.setTrader(this);
    }

    /**
     * Ajoute à la vente un objet de type ProduitFermier d'un de ses clients dans un marché
     * @param client
     * @param produitMisEnVente
     * @param quantite
     * @param prix
     * @param marche
     */
    public void metEnVente(Participant client, Participant.Produits produitMisEnVente , Integer quantite, Double prix, Marche marche){
        if(isClient(client))client.vendreProduit(produitMisEnVente,quantite,prix,marche, client);
    }

    /**
     * Ajoute une offre d'achat sur un ou plusieurs objets de type ProduitFermier en vente dans un objet de
     * type Marche
     * @param client
     * @param produitMisEnVente
     * @param quantite
     * @param prix
     * @param marche
     */
    public void poseUneOffre(Participant client, Participant.Produits produitMisEnVente , Integer quantite, Double prix, Marche marche){
        if(isClient(client))client.proposerOffre(produitMisEnVente,quantite,prix,marche);
    }
    public void ajouterAuSolde(double revenu){
        solde += revenu/8;
    }

    /**
     * revoie la valeur de la variable solde d'un objet de type Trader
     * @return solde
     */
    public Double getSolde() {
        return solde;
    }

    /**
     * permet de modifier la valeur de la variable solde d'un objet de type Trader
     * @param solde
     */
    public void setSolde(Double solde) {
        this.solde = solde;
    }

    /**
     * revoie la valeur de la variable name d'un objet de type Trader
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * revoie le contenu de l'ArrayList<Participant> clients.
     * @return clients
     */
    public ArrayList<Participant> getClients() {
        return clients;
    }

    /**
     * revoie vrai si un Participant fait partie de la liste de clients du trader, faux sinon
     * @param p
     * @return boolean
     */
    public boolean isClient(Participant p){

        for (Participant client: clients) {
            if(p.equals(client)){
                return true;
            }
        }
        return false;
    }

    /**
     * renvoie l'ensemble des valeurs des variables d'un objet Trader
     * @return
     */
    @Override
    public String toString() {
        String string = "Le trader " + this.getName() +" possèdent " + clients.size() + " client \n";
        for (Participant participant: clients){
            string+=participant.getNom() +' ' + participant.getPrenom()+'\n';
        }
        return string;
    }
}
