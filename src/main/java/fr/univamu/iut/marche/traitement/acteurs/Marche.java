package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.security.Key;
import java.util.*;

import static fr.univamu.iut.marche.traitement.Main.ANSI_RED;

/***
 * @author Téo Martin
 * Ceci est la classe Marché qui possède toutes les fonctions permettant de gérer celui-ci
 */

public class Marche {
    private Map<Paysan, ArrayList<ProduitFermier>> listeParticipantsMarche = new HashMap<>();

    public Marche() {
    }

    public Map<Paysan, ArrayList<ProduitFermier>> getListeParticipantsMarche() {
        return listeParticipantsMarche;
    }

    public void setListeParticipantsMarche(Map<Paysan, ArrayList<ProduitFermier>> listeParticipantsMarche) {
        this.listeParticipantsMarche = listeParticipantsMarche;

    }

    public void listeProduitsPaysans (Paysan paysan){
        for(Map.Entry<Paysan, ArrayList<ProduitFermier>> entry : listeParticipantsMarche.entrySet()) {
            Paysan peonCherche = entry.getKey();
            if (peonCherche == paysan){
                peonCherche.getProduits();
           }
        }
    } // pas sur que ça serve ?

    public void vendreProduit(Paysan paysan, ProduitFermier produitVendu){
        if (paysan.getProduits().contains(produitVendu)) {

            listeParticipantsMarche.get(paysan).add(produitVendu);
            listeParticipantsMarche.put(paysan, paysan.getProduitsVendus());
            paysan.removeProduit(produitVendu);
        } else {
            System.out.println(ANSI_RED + paysan.getPrenom() + " " + paysan.getNom() + " ne peut pas vendre " + produitVendu);
        }
    }

    public ArrayList<ProduitFermier> creerListeProduitsSpe(ProduitFermier p){
        ArrayList<ProduitFermier> listeProduitSpe = new ArrayList<>();
        for (Paysan paysan: listeParticipantsMarche.keySet()) {
            System.out.println(paysan);

            for (ArrayList<ProduitFermier> list: listeParticipantsMarche.values()) {
                if (listeParticipantsMarche.containsValue(p))
                    System.out.println(list);
                    listeProduitSpe.add(p);
            }
        }
        System.out.println(listeProduitSpe);
        return listeProduitSpe;
    }
}
