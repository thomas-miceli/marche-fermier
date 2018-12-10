package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static fr.univamu.iut.marche.traitement.Main.ANSI_RED;

public class Marche {
    private static Map<Paysan, ArrayList<ProduitFermier>> listeParticipantsMarche = new HashMap<>();
    public static final ArrayList<Map<Paysan, ArrayList<ProduitFermier>>> allListeParticipantsMarche = new ArrayList<>();

    public Marche() {
    }

    public static Map<Paysan, ArrayList<ProduitFermier>> getListeParticipantsMarche() {
        return listeParticipantsMarche;
    }

    public void setListeParticipantsMarche(Map<Paysan, ArrayList<ProduitFermier>> listeParticipantsMarche) {
        this.listeParticipantsMarche = listeParticipantsMarche;
        allListeParticipantsMarche.add(listeParticipantsMarche);

    }

    public void listeProduitsPaysans (Paysan peon){
        for(Map.Entry<Paysan, ArrayList<ProduitFermier>> entry : listeParticipantsMarche.entrySet()) {
            Paysan peonCherche = entry.getKey();
            if (peonCherche == peon){
                peonCherche.getProduits();
           }
        }
    } // pas sur que ça serve ?

    public void vendreProduit(Paysan peon, ProduitFermier produitVendu){
        if (peon.getProduits().contains(produitVendu)) {

            peon.getProduitsVendus().add(produitVendu);
            listeParticipantsMarche.put(peon, peon.getProduitsVendus());
            peon.removeProduit(produitVendu);
        } else {
            System.out.println(ANSI_RED + peon.getPrenom() + " " + peon.getNom() + " ne peut pas vendre " + produitVendu);
        }
    }
}
