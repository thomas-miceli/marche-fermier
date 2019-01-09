package fr.univamu.iut.marche.traitement.Observer;

import fr.univamu.iut.marche.traitement.acteurs.Marche;

/**
 * @author Yann FORNER
 */
public class ObserverNewVente extends Observer {

    public ObserverNewVente(Marche m) {
        this.marche = m;
    }

    /**
     * Mets à jour le marche
     */
    @Override
    public void updateO() {
        System.out.println("une nouvelle vente est apparue");
        System.out.println(marche.getCompositionMarche().get(marche.getCompositionMarche().size() - 1));
    }
}
