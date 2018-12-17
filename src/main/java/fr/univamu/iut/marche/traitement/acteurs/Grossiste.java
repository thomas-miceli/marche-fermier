package fr.univamu.iut.marche.traitement.acteurs;

import fr.univamu.iut.marche.traitement.produits.ProduitFermier;

import java.util.ArrayList;

public interface Grossiste {

    void acheterProduit(Participant acheteur, Participant vendeur, Participant.Produits produitAchete);
    void vendreProduit(Participant participant, Participant.Produits produitAVendre);
}
