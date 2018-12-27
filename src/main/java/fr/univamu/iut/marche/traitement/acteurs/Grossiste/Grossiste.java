package fr.univamu.iut.marche.traitement.acteurs.Grossiste;

import fr.univamu.iut.marche.traitement.acteurs.Participant;

/***
 * @author Thomas MICELI
 * @author Téo MARTIN
 * Ceci est la classe Marché qui possède toutes les fonctions permettant de gérer celui-ci
 */
public interface Grossiste {

    void acheterProduit(Participant acheteur, Participant vendeur, Participant.Produits produitAchete);
    void vendreProduit(Participant participant, Participant.Produits produitAVendre);
}
