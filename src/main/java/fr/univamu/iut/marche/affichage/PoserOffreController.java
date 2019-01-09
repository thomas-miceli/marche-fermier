package fr.univamu.iut.marche.affichage;

import fr.univamu.iut.marche.traitement.Seeding;
import fr.univamu.iut.marche.traitement.acteurs.CentraleAchat;
import fr.univamu.iut.marche.traitement.acteurs.Marche;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class PoserOffreController extends VBox implements Initializable {

    @FXML
    private VBox contentVBox;

    private Scene scene;

    @FXML
    private BorderPane content;
    @FXML
    private VBox produitChoix;
    @FXML
    private VBox listeParticipantCentrale;


    private ArrayList<Participant> participants = new ArrayList<>();
    private ArrayList<CheckBox> participantscheck = new ArrayList<>();
    private String selectedProduit;
    private String selectedParticipant;
    private TextField prix = new TextField("0");
    private TextField quantite = new TextField("0");
    private ArrayList<String> listeParticipantsCentraleAchat = new ArrayList<>();
    private CentraleAchat centraleAchat = (CentraleAchat) listeParticipantController.getSelectedParticipant();

    @FXML
    public void setViewToListeparticipants() throws IOException{
        if(Integer.valueOf(prix.getCharacters().toString())==0) {
            centraleAchat.poserOffre(creerProduit(selectedProduit), centraleAchat.getMembres().get(listeParticipantsCentraleAchat.indexOf(selectedParticipant)), Double.valueOf(prix.getCharacters().toString()), Integer.valueOf(quantite.getCharacters().toString()), Seeding.getListeMarche().get(0));
        }else {

        }
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new listeParticipantController());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        produitChoix.getChildren().add(0,new Text("Produits rechercher : "));
        ArrayList<String> produits = new ArrayList<>();
        for (Participant.Produits produits1 : Participant.Produits.values()) produits.add(produits1.toString());
        ChoiceBox<String> cb = new ChoiceBox(FXCollections.observableArrayList(produits));
        cb.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            selectedProduit = cb.getItems().get((Integer)newValue);
        });
        produitChoix.getChildren().add(1, cb);
        produitChoix.getChildren().add(2, new Text("Quantite :"));
        produitChoix.getChildren().add(3, quantite);
        produitChoix.getChildren().add(4, new Text("Prix : "));
        produitChoix.getChildren().add(5,prix);
        for (Participant participant : centraleAchat.getMembres()) listeParticipantsCentraleAchat.add(participant.getPrenom() + ' ' + participant.getNom());
        ChoiceBox<String> cb2 = new ChoiceBox(FXCollections.observableArrayList(listeParticipantsCentraleAchat));
        cb2.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            selectedParticipant = cb2.getItems().get((Integer)newValue);
        });
        listeParticipantCentrale.getChildren().add(0, new Text("Choix participant : "));
        listeParticipantCentrale.getChildren().add(cb2);

    }

    public PoserOffreController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/PoserOffre.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }
    private Participant.Produits creerProduit(String s){

        switch (s){
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