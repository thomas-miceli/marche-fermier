package fr.univamu.iut.marche.affichage;

import fr.univamu.iut.marche.traitement.Seeding;
import fr.univamu.iut.marche.traitement.acteurs.Marche;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Vente;
import fr.univamu.iut.marche.traitement.produits.Identificateur;
import fr.univamu.iut.marche.traitement.produits.ProduitFermier;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class vendreProduitController extends VBox implements Initializable {

    @FXML
    private VBox contentVBox;

    @FXML
    private Text produitName;

    @FXML
    private TextField prixDeVente;

    @FXML
    private TextField QuantiteVoulus;
    @FXML
    private Text popupAlert;
    @FXML
    private Text quantiteDisponible;

    private static homeController homeController;

    @FXML
    public void setViewToParticipant() throws IOException{
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new participantController());
    }


    private static String produitvente;

    private ObservableList<String> data = FXCollections.observableArrayList();

    public void vendreProduit()throws  IOException{
        Identificateur i = new Identificateur();
        if(QuantiteVoulus.getCharacters().toString().isEmpty() || prixDeVente.getCharacters().toString().isEmpty()) popupAlert.setText("Quantité | Prix ?");
        else if(Integer.valueOf(QuantiteVoulus.getCharacters().toString())>participantController.getSelectedProduit().getQuantite()) popupAlert.setText("Le participant ne possède pas assez de produit");
        else{
            listeParticipantController.getSelectedParticipant().vendreProduit(participantController.getSelectedProduit().identifier(i), Integer.valueOf(QuantiteVoulus.getCharacters().toString()), Double.valueOf(prixDeVente.getCharacters().toString()), Seeding.getListeMarche().get(0));
            contentVBox.getChildren().clear();
            contentVBox.getChildren().addAll(new listeParticipantController());
        }
    }

    public static void setHomeController(homeController h){
        homeController = h;
    }
    public vendreProduitController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/VenteProduit.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProduitFermier produitFermier = participantController.getSelectedProduit();
        quantiteDisponible.setText("Quantité disponible : "+ participantController.getSelectedProduit().getQuantite());
        produitName.setText(produitFermier.getClass().getSimpleName());
    }
}

