package fr.univamu.iut.marche.affichage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ajoutPaysanController extends VBox implements Initializable {
    @FXML
    private VBox root;

    private Scene scene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public ajoutPaysanController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/AjoutPaysan.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }
}
