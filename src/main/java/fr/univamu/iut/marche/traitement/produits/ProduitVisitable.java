package fr.univamu.iut.marche.traitement.produits;

import fr.univamu.iut.marche.traitement.acteurs.Participant;

/**
 * @author Yann FORNER
 */
public interface ProduitVisitable {
    Participant.Produits identifier(ProduitVisiteur v);
}
