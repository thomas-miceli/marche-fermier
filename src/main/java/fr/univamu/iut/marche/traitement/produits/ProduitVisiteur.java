package fr.univamu.iut.marche.traitement.produits;

import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.produits.*;

/**
 * @author Yann FORNER
 */
public interface ProduitVisiteur {


    public Participant.Produits visiter(Miel m);
    public Participant.Produits visiter(Cochon c);
    public Participant.Produits visiter(Fromage f);
    public Participant.Produits visiter(Lait l);
    public Participant.Produits visiter(Orange o);
    public Participant.Produits visiter(Pomme p);
    public Participant.Produits visiter(Vache v);
}
