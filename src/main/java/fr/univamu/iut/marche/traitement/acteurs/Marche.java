package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;
import java.util.Map;

public class Marche {
    private Map<Paysan, ArrayList<ProduitFermier>> listeParticipantsMarche;

    public Marche(Map<Paysan, ArrayList<ProduitFermier>> listeParticipantsMarche) {
        this.listeParticipantsMarche = listeParticipantsMarche;
    }

    public Map<Paysan, ArrayList<ProduitFermier>> getListeParticipantsMarche() {
        return listeParticipantsMarche;
    }

    public void setListeParticipantsMarche(Map<Paysan, ArrayList<ProduitFermier>> listeParticipantsMarche) {
        this.listeParticipantsMarche = listeParticipantsMarche;

    }

    @Override
    public String toString() {
        return "liste des fermiers et des produits qu'ils vendent : "
                + listeParticipantsMarche.get(listeParticipantsMarche);
    }

    public void VendreProduit(Paysan peon, ArrayList<ProduitFermier> produitsVendus){
        listeParticipantsMarche.put(peon, produitsVendus);
    }
}
