package fr.univamu.iut.marche.affichage;

import fr.univamu.iut.marche.traitement.acteurs.Marche;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Paysan;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
/**
 * @author Pierre LEJEUNE
 * @author Thomas MICELI
 */
public class catalogController extends VBox implements Initializable {

    @FXML
    private VBox root;

    private Scene scene;

    @FXML
    private BorderPane content;

    @FXML
    private ListView listeVentes = new ListView();

    private static String selectedProduit;
    private ObservableList<String> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(Marche.getListeParticipantsMarche());
        for (Participant participant:Marche.getListeParticipantsMarche()) {
            for (ProduitFermier produit: participant.getProduits()) {
                data.add(produit.getClass().getSimpleName() + "\t\t\t\t\t\t"+ participant.getPrenom() +' '+ participant.getNom());
            }
        }
        listeVentes.setItems(data);
        listeVentes.getSelectionModel().selectedItemProperty()
                .addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
                    try {
                        root.getChildren().clear();
                        selectedProduit = newValue;
                        root.getChildren().addAll(new venteProduitController());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

    }



    public catalogController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Catalog.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }
    public static String getSelectedProduit(){
        return selectedProduit;
    }
}