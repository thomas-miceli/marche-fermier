package fr.univamu.iut.marche.affichage;

import fr.univamu.iut.marche.traitement.acteurs.CentraleAchat;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class addParticipantController extends VBox implements Initializable {

    @FXML
    private VBox contentVBox;

    private Scene scene;

    @FXML
    private BorderPane content;
    @FXML
    private VBox listeParticipantsToAdd;

    private ArrayList<Participant> participants = new ArrayList<>();
    private ArrayList<CheckBox> participantscheck = new ArrayList<>();

    @FXML
    public void setViewToParticipant() throws IOException {
        CentraleAchat centraleAchat = (CentraleAchat) listeParticipantController.getSelectedParticipant();
        for (CheckBox checkBox : participantscheck) {
            if (checkBox.isSelected()) centraleAchat.addMembre(participants.get(participantscheck.indexOf(checkBox)));
        }
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new participantController());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        CentraleAchat centraleAchat = (CentraleAchat) listeParticipantController.getSelectedParticipant();
        for (Participant participant : Participant.getAllParticipants()) {
            CheckBox checkBox = new CheckBox();
            Text text = new Text(participant.getPrenom() + ' ' + participant.getNom());
            participants.add(participant);
            participantscheck.add(checkBox);
            for (Participant participant1 : centraleAchat.getMembres()) {
                if (participant.getId() != participant1.getId() || !participant1.getClass().getSimpleName().equals("CentraleAchat")) {
                    HBox hBox = new HBox();
                    hBox.getChildren().add(0, text);
                    hBox.getChildren().add(1, checkBox);
                    listeParticipantsToAdd.getChildren().add(hBox);
                }
            }

        }

    }

    public addParticipantController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/addParticipant.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

}
