package fr.univamu.iut.marche.affichage;

import fr.univamu.iut.marche.traitement.Seeding;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProposerOffreController extends VBox implements Initializable {

    @FXML
    private VBox contentVBox;

    private Scene scene;

    @FXML
    private BorderPane content;
    @FXML
    private VBox produitChoix;
    private String selectedProduit;
    private TextField prix = new TextField("0");
    private TextField quantite = new TextField("0");

    @FXML
    public void setViewToCatalog() throws IOException {
        if (Integer.valueOf(prix.getCharacters().toString()) != 0 || Integer.valueOf(prix.getCharacters().toString()) != 0) {
            Seeding.getFxmlUser().proposerOffre(creerProduit(selectedProduit), Integer.valueOf(quantite.getCharacters().toString()), Double.valueOf(prix.getCharacters().toString()), Seeding.getListeMarche().get(0));
        } else {

        }
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new listeParticipantController());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        produitChoix.getChildren().add(0, new Text("Produits rechercher : "));
        ArrayList<String> produits = new ArrayList<>();
        for (Participant.Produits produits1 : Participant.Produits.values()) produits.add(produits1.toString());
        ChoiceBox<String> cb = new ChoiceBox(FXCollections.observableArrayList(produits));
        cb.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            selectedProduit = cb.getItems().get((Integer) newValue);
        });
        produitChoix.getChildren().add(1, cb);
        produitChoix.getChildren().add(2, new Text("Prix :"));
        produitChoix.getChildren().add(3, prix);
        produitChoix.getChildren().add(4, new Text("Quantité :"));
        produitChoix.getChildren().add(5, quantite);

    }

    public ProposerOffreController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/ProposerOffre.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    private Participant.Produits creerProduit(String s) {

        switch (s) {
            case "COCHON":
                return Participant.Produits.COCHON;
            case "FROMAGE":
                return Participant.Produits.FROMAGE;
            case "LAIT":
                return Participant.Produits.LAIT;
            case "MIEL":
                return Participant.Produits.MIEL;
            case "ORANGE":
                return Participant.Produits.ORANGE;
            case "POMME":
                return Participant.Produits.POMME;
            case "VACHE":
                return Participant.Produits.VACHE;
            default:
                return null;
        }
    }
}