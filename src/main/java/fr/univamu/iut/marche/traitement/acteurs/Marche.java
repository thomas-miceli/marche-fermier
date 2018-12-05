package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public void AddToList(Paysan peon, ProduitFermier produitAVendre){
        peon.getProduitsVendus().add(produitAVendre);
        System.out.println(peon);
        System.out.println(peon.getProduitsVendus());

        listeParticipantsMarche.put(peon, peon.getProduitsVendus());

        System.out.println(listeParticipantsMarche.toString());
    }

    public void listeProduitsPaysans (Paysan peon){
        for(Map.Entry<Paysan, ArrayList<ProduitFermier>> entry : listeParticipantsMarche.entrySet()) {
            Paysan peonCherche = entry.getKey();
            if (peonCherche == peon){
                peonCherche.getProduits();
           }
        }
    }

    public void VendreProduit(Paysan peon, ProduitFermier produitVendu){
        AddToList(peon, produitVendu);
        peon.removeProduit(produitVendu);
    }
}
