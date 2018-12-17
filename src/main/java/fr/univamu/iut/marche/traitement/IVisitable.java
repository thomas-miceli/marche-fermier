package fr.univamu.iut.marche.traitement;

import fr.univamu.iut.marche.traitement.acteurs.Participant;

public interface IVisitable {
    Participant.Produits identifier(Visiteur v);
}
