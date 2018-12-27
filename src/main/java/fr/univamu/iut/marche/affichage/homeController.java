package fr.univamu.iut.marche.affichage;

import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Traders.TraderIndependant;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * @author Pierre LEJEUNE
 */
public class homeController implements Initializable {

    @FXML
    private VBox contentVBox;

    @FXML
    private Text soldeFxmlUser;

    private static Stage credits;

    private static Participant participantFxml;

    public void setStageAndSetupListeners(Stage stage) {
        stage.setOnCloseRequest(event -> Platform.exit());
    }

    @FXML
    public void setSceneToCatalogView() throws IOException {
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new catalogController());
    }

    @FXML
    public void setSceneToTransactionView() throws IOException {
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new transactionController());
    }

    @FXML
    public void setSceneToCotationView() throws IOException {
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(/* new CotationController*/);
    }
    @FXML
    public void setSceneToParticipantsView() throws IOException {
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new listeParticipantController());
    }

    @FXML
    public void showCredits() throws IOException {
        Stage creditStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr.univ_amu.iut/views/credits.fxml"));
        Pane root = loader.load();
        creditStage.setScene(new Scene(root));
        creditStage.setTitle("Crédits");
        creditStage.setResizable(false);
        credits = creditStage;
        creditStage.show();
    }

    public static Stage getCreditStage() {
        return credits;
    }

    @FXML
    public void actionQuitter() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Voulez-vous quitter ?", ButtonType.CLOSE, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.CLOSE) {
            Platform.exit();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        participantFxml = new TraderIndependant("Utilisateur", "FXML", 69);
        participantFxml.setArgent(150);
        soldeFxmlUser.setText("Solde : " + String.valueOf(participantFxml.getArgent()));
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll();
    }

    public void reloadsolde(){
        soldeFxmlUser.setText("Solde : " + String.valueOf(participantFxml.getArgent()));
    }

    public static Participant getParticipantFxml(){
        return participantFxml;
    }
}
