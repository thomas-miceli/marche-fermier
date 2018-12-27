package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.*;

/***
 * @author Pierre LEJEUNE
 * @author Téo MARTIN
 * Ceci est la classe Marché qui possède toutes les fonctions permettant de gérer celui-ci
*/

public class Marche {
    private static ArrayList<Participant> listeParticipantsMarche = new ArrayList<>();
    private static ArrayList<String> listeTransactions = new ArrayList<>();


    public Marche() {
    }


    public static ArrayList<Participant> getListeParticipantsMarche() {
        return listeParticipantsMarche;
    }

    public void setListeParticipantsMarche(ArrayList<Participant> listeParticipantsMarche1) {
        listeParticipantsMarche.removeAll(listeParticipantsMarche);
        listeParticipantsMarche.addAll(listeParticipantsMarche1);
    }
    public static void vente(Participant acheteur, ProduitFermier produitAcheter, int Quantite, Participant vendeur){
            try{
                produitAcheter.setQuantite(produitAcheter.getQuantite()-Quantite);
                for (ProduitFermier produit:vendeur.getProduits()) {
                    if(produit.getId()==produitAcheter.getId()) produit = produitAcheter;
                }
                //vendeur.setArgent(acheteur.getArgent()+produitAcheter.getPrix()*Quantite);
                //acheteur.setArgent(vendeur.getArgent()-produitAcheter.getPrix()*Quantite);
                addTransaction(acheteur, produitAcheter, Quantite, vendeur);
            }catch (Exception e){
                System.out.println("un probleme est survenus");
            }

        System.out.println(acheteur.getPrenom()+' '+acheteur.getNom()+" n'a pas les moyens pour cela");
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

    public ArrayList<ProduitFermier> getListeProduitsSpe(ProduitFermier p){
        ArrayList<ProduitFermier> listeProduitSpe = new ArrayList<>();
        for (Participant participant:listeParticipantsMarche) {
            for (ProduitFermier produitFermier : participant.getProduits()){
                if(produitFermier.equals(p)) listeProduitSpe.add(produitFermier);
            }
        }
        return listeProduitSpe;
    }
    public static void addTransaction(Participant acheteur, ProduitFermier produitAcheter,int Quantite, Participant vendeur){
        listeTransactions.add("Acheteur : " + acheteur.getPrenom() +' '+ acheteur.getNom() + " | Vendeur : " + vendeur.getPrenom() +' '+vendeur.getNom()+" | Produit : " + produitAcheter.getClass().getSimpleName() + " Quantité : "+ Quantite);
    }
    public static ArrayList<String> getTransaction(){
        return listeTransactions;
    }
}
