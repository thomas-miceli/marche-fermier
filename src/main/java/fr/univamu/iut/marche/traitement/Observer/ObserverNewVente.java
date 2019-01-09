package fr.univamu.iut.marche.traitement.Observer;

import fr.univamu.iut.marche.traitement.acteurs.Marche;

import static fr.univamu.iut.marche.traitement.Main.ANSI_CYAN;

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
        System.out.println(ANSI_CYAN + "Une nouvelle vente est apparue : " + marche.getCompositionMarche().get(marche.getCompositionMarche().size() - 1));
    }
}
