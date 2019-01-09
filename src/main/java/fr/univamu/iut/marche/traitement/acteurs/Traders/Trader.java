package fr.univamu.iut.marche.traitement.acteurs.Traders;

import fr.univamu.iut.marche.traitement.acteurs.Marche;
import fr.univamu.iut.marche.traitement.acteurs.Participant;

import java.util.ArrayList;

public class Trader {
    private String name;
    private String prenom;
    private int age;
    private Double solde=0.0;
    private ArrayList<Participant> clients = new ArrayList<>();

    public Trader(String name) {
        this.name = name;
    }

    public Trader(String name, String prenom, int age) {
        this.name = name;
        this.prenom = prenom;
        this.age = age;
    }

    public String getName() {
        return name;
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

    public Double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void addClients (Participant p){
        clients.add(p);
        p.setTrader(this);
    }
    public void metEnVente(Participant client, Participant.Produits produitMisEnVente , Integer quantite, Double prix, Marche marche){
        if(isClient(client))client.vendreProduit(produitMisEnVente,quantite,prix,marche);
    }
    public void poseUneOffre(Participant client, Participant.Produits produitMisEnVente , Integer quantite, Double prix, Marche marche){
        if(isClient(client))client.proposerOffre(produitMisEnVente,quantite,prix,marche);
    }
    public void ajouterAuSolde(double revenu){
        solde+=revenu*1/8;
    }



    public ArrayList<Participant> getClients() {
        return clients;
    }
    public boolean isClient(Participant p){

        for (Participant client: clients) {
            if(p.equals(client)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String string = "Le trader " + this.getName() +" possèdent " + clients.size() + " client \n";
        for (Participant participant: clients){
            string+=participant.getNom() +' ' + participant.getPrenom()+'\n';
        }
        return string;
    }
}
