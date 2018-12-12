package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.security.Key;
import java.util.*;

import static fr.univamu.iut.marche.traitement.Main.ANSI_RED;

/***
 * @author Téo Martin/Pierre Lejeune
 * Ceci est la classe Marché qui possède toutes les fonctions permettant de gérer celui-ci
 */

public class Marche {
    private static ArrayList<Participant> listeParticipantsMarche;


    public Marche() {
    }

    public static ArrayList<Participant> getListeParticipantsMarche() {
        return listeParticipantsMarche;
    }

    public void setListeParticipantsMarche(ArrayList<Participant> listeParticipantsMarche1) {
        listeParticipantsMarche.removeAll(listeParticipantsMarche);
        listeParticipantsMarche.addAll(listeParticipantsMarche1);
    }
    public void addParticipant(Participant participant){
        listeParticipantsMarche.add(participant);
    }

    public void listeProduitsParticipants (Participant participant){
        for (Participant participant1:listeParticipantsMarche) {
            if(participant1 == participant){
                System.out.println("Le participant " + participant1.getPrenom() +" "+ participant1.getNom() + " vend sur le marché :");
                for (ProduitFermier produit:participant1.getProduitsAVendre()) {
                    System.out.println(produit.getClass().getSimpleName());
                }
                break;
            }
        }
        System.out.println("Le paysan passé en paramètre n'est pas sur le marché");
    }

    public void vendreProduit(Participant participant, ProduitFermier produitAVendre){
        if (participant.getProduitsAVendre().contains(produitAVendre)) {
            participant.removeProduit(produitAVendre);
        } else {
            System.out.println(ANSI_RED + participant.getPrenom() + " " + participant.getNom() + " ne peut pas vendre " + produitAVendre);
        }
    }

    public ArrayList<ProduitFermier> getListeProduitsSpe(ProduitFermier p){
        ArrayList<ProduitFermier> listeProduitSpe = new ArrayList<>();
        for (Participant participant:listeParticipantsMarche) {
            for (ProduitFermier produitFermier : participant.getProduits()){
                if(produitFermier.equals(p)) listeProduitSpe.add(produitFermier);
            }
        }
        return listeProduitSpe;
    }
}
