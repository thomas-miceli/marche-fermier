package fr.univamu.iut.marche.affichage;

import fr.univamu.iut.marche.traitement.Seeding;
import fr.univamu.iut.marche.traitement.acteurs.CentraleAchat;
import fr.univamu.iut.marche.traitement.acteurs.Grossiste.Grossiste;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Paysans.*;
import fr.univamu.iut.marche.traitement.acteurs.Traders.Trader;
import fr.univamu.iut.marche.traitement.produits.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class ajoutParticipantController extends VBox implements Initializable {
    @FXML
    private VBox contentVBox;

    private Scene scene;

    @FXML
    private TextField Nom;
    @FXML
    private TextField Prenom;
    @FXML
    private TextField Age;
    @FXML
    private TextField Cochon;
    @FXML
    private TextField CochonPrix;
    @FXML
    private TextField Fromage;
    @FXML
    private TextField FromagePrix;
    @FXML
    private TextField Lait;
    @FXML
    private TextField LaitPrix;
    @FXML
    private TextField Miel;
    @FXML
    private TextField MielPrix;
    @FXML
    private TextField Orange;
    @FXML
    private TextField OrangePrix;
    @FXML
    private TextField Pomme;
    @FXML
    private TextField PommePrix;
    @FXML
    private TextField Vache;
    @FXML
    private TextField VachePrix;
    @FXML
    private Text alertText;
    @FXML
    private HBox Type;
    @FXML
    private Text textType;

    private String selectedType = "";
    @FXML
    public void newParticipant() throws IOException{
        if(!Nom.getCharacters().toString().isEmpty() && !Prenom.getCharacters().toString().isEmpty() && !Age.getCharacters().toString().isEmpty() && !Cochon.getCharacters().toString().isEmpty() && !Fromage.getCharacters().toString().isEmpty() && !Lait.getCharacters().toString().isEmpty() && !Miel.getCharacters().toString().isEmpty() && !Orange.getCharacters().toString().isEmpty() && !Pomme.getCharacters().toString().isEmpty() && !Vache.getCharacters().toString().isEmpty() && !selectedType.isEmpty()){
            Participant participant = null;
            switch (selectedType) {
                case "Grossiste":
                    /*participant = new Grossiste(getStringOfTextField(Nom), getStringOfTextField(Prenom), getIntOfTextField(Age));*/
                    break;
                case "Centrale d'achat":
                    participant = new CentraleAchat(getStringOfTextField(Nom), getStringOfTextField(Prenom), getIntOfTextField(Age));
                    break;
                default:
                    participant = null;
            }
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, 1);
            ajouterProdtoParticipant(participant,new Cochon(getIntOfTextField(Cochon),calcDatePeremption()) ,new Lait(getIntOfTextField(Lait), calcDatePeremption()), new Miel(getIntOfTextField(Miel), calcDatePeremption()), new Fromage(getIntOfTextField(Fromage), calcDatePeremption()), new Pomme(getIntOfTextField(Pomme), calcDatePeremption()), new Orange(getIntOfTextField(Orange), calcDatePeremption()), new Vache(getIntOfTextField(Vache), calcDatePeremption()));
            Random Solde = new Random();
            participant.setSolde(Solde.nextInt(400)+200);
            contentVBox.getChildren().clear();
            contentVBox.getChildren().addAll(new catalogController());
        }else{
            alertText.setText("Il manque des arguments");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChoiceBox<String> cb = new ChoiceBox(FXCollections.observableArrayList("Grossiste", "Centrale d'achat"));
        cb.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            selectedType = cb.getItems().get((Integer)newValue);
        });
        Type.getChildren().add(cb);
    }
    public ajoutParticipantController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/AjoutParticipant.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    public String getStringOfTextField(TextField textField){
        return textField.getCharacters().toString();
    }
    public int getIntOfTextField(TextField textField){
        try{
            return Integer.valueOf(getStringOfTextField(textField));
        }catch (Exception e){
            return 0;
        }

    }
    private void ajouterProdtoParticipant(Participant p, ProduitFermier pf1, ProduitFermier pf2, ProduitFermier pf3, ProduitFermier pf4, ProduitFermier pf5, ProduitFermier pf6, ProduitFermier pf7){
        ajouterProdtoParticipant(p, pf1);
        ajouterProdtoParticipant(p, pf2);
        ajouterProdtoParticipant(p, pf3);
        ajouterProdtoParticipant(p, pf4);
        ajouterProdtoParticipant(p, pf5);
        ajouterProdtoParticipant(p, pf6);
        ajouterProdtoParticipant(p, pf7);
    }
    private void ajouterProdtoParticipant(Participant p ,  ProduitFermier produitFermier){
        if(produitFermier.getQuantite()>0){
            p.addProduit(produitFermier);
        }
    }
    private Date calcDatePeremption(){

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 5);
        return (c.getTime());
    }
}
