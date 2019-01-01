package fr.univamu.iut.marche.traitement.acteurs.Traders;

import fr.univamu.iut.marche.traitement.acteurs.Marche;
import fr.univamu.iut.marche.traitement.acteurs.Participant;

import java.util.ArrayList;

public class Trader {
    private String name;
    private Double solde=0.0;
    private ArrayList<Participant> clients = new ArrayList<>();

    public Trader(String name) {
        this.name = name;
    }
    public void addClients (Participant p){
        clients.add(p);
        p.setTrader(this);
    }
    public void metEnVente(Participant client, Participant.Produits produitMisEnVente , Integer quantite, Double prix, Marche marche){
        client.vendreProduit(produitMisEnVente,quantite,prix,marche);
    }
    public void poseUneOffre(Participant client, Participant.Produits produitMisEnVente , Integer quantite, Double prix, Marche marche){
        client.proposerOffre(produitMisEnVente,quantite,prix,marche);
    }
    public void ajouterAuSolde(Double revenu){
        solde+=revenu*1/8;
    }
    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Participant> getClients() {
        return clients;
    }
}
