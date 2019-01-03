package fr.univamu.iut.marche.affichage;

import fr.univamu.iut.marche.traitement.acteurs.Marche;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class participantController extends VBox implements Initializable {

    @FXML
    private VBox contentVBox;

    private Scene scene;

    @FXML
    private BorderPane content;

    @FXML
    private Text participantName;

    @FXML
    private Text participantInfo;

    @FXML
    private ListView listeParticipant = new ListView();

    public void setViewToListeparticipants() throws IOException{
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new listeParticipantController());
    }

    private static String selectedParticipant;
    private ObservableList<String> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(Participant.getAllParticipants());
        Participant participantCurrent = Participant.getParticipantbyId(Integer.parseInt(listeParticipantController.getSelectedParticipant()));
        for (int i =0; i<participantCurrent.getStock().size(); ++i) {
            data.add("Produit : " + participantCurrent.getStock().get(i).getClass().getSimpleName() +"  Quantité : " + participantCurrent.getStock().get(i).getQuantite() + "  | Date de peremption : "+ participantCurrent.getStock().get(i).getDateDePeremption().getMonth() + '-' +(participantCurrent.getStock().get(i).getDateDePeremption().getYear()-100+2000) + "   | Id : " + i);
        }
        listeParticipant.setItems(data);
        participantName.setText(participantCurrent.getPrenom() + ' ' + participantCurrent.getNom());
        participantInfo.setText("Info \nAge : " + participantCurrent.getAge() + " | Nombre de produits : " + participantCurrent.getStock().size() + " | Solde : " + participantCurrent.getSolde()  + " | Type : "  + participantCurrent.getClass().getSimpleName()  );

    }

    public participantController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Participant.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }
}