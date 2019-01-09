package fr.univamu.iut.marche.affichage;

import fr.univamu.iut.marche.traitement.acteurs.CentraleAchat;
import fr.univamu.iut.marche.traitement.acteurs.Marche;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class participantController extends VBox implements Initializable {

    @FXML
    private VBox contentVBox;

    private Scene scene;

    @FXML
    private BorderPane content;
    @FXML
    private HBox choix;

    @FXML
    private Text participantName;

    @FXML
    private Text participantInfo;

    @FXML
    private ListView listeParticipant = new ListView();

    private ArrayList<ProduitFermier> produitsParticipants = new ArrayList<>();
    private ArrayList<String> produitParticipantsString = new ArrayList<>();

    private Button choixButton;
    @FXML
    public void setViewToListeparticipants() throws IOException{
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new listeParticipantController());
    }

    private static ProduitFermier selectedProduit;
    private ObservableList<String> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Participant participantCurrent = listeParticipantController.getSelectedParticipant();
        System.out.println(participantCurrent.getClass().getSimpleName());
        if (participantCurrent.getClass().getSimpleName().equals("CentraleAchat")){
            CentraleAchat centraleAchat = (CentraleAchat) participantCurrent;
            for(Participant participant : centraleAchat.getMembres()){
                data.add(participant.toString());
            }
            listeParticipant.setItems(data);
            choixButton = new Button("Ajouter Participants");
            choix.getChildren().add(choixButton);
            choixButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        setViewToAddParticipant();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }else{
            for (int i =0; i<participantCurrent.getStock().size(); ++i) {
                data.add("Produit : " + participantCurrent.getStock().get(i).getClass().getSimpleName() +"  Quantité : " + participantCurrent.getStock().get(i).getQuantite() + "  | Date de peremption : "+ participantCurrent.getStock().get(i).getDateDePeremption().getMonth() + '-' +(participantCurrent.getStock().get(i).getDateDePeremption().getYear()-100+2000) + "   | Id : " + i);
                produitParticipantsString.add(data.get(i));
                produitsParticipants.add(participantCurrent.getStock().get(i));
            }
            listeParticipant.setItems(data);
            listeParticipant.getSelectionModel().selectedItemProperty()
                    .addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
                        try {
                            contentVBox.getChildren().clear();
                            selectedProduit = produitsParticipants.get(produitParticipantsString.indexOf(newValue));
                            contentVBox.getChildren().addAll(new vendreProduitController());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            choixButton = new Button("Créer produit");
            choixButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        setViewToListeProduction();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            choix.getChildren().add(choixButton);
        }

        participantName.setText(participantCurrent.getPrenom() + ' ' + participantCurrent.getNom());
        participantInfo.setText("Info \nAge : " + participantCurrent.getAge() + " | Nombre de produits : " + participantCurrent.getStock().size() + " | Solde : " + participantCurrent.getSolde()  + " | Type : "  + participantCurrent.getClass().getSimpleName()  );

    }

    public participantController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Participant.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    public void setViewToListeProduction() throws IOException {
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new productionController());
    }
    public void setViewToAddParticipant() throws IOException {
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new addParticipantController());
    }
    public static ProduitFermier getSelectedProduit(){
        return selectedProduit;
    }
}