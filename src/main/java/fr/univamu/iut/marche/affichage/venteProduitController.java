package fr.univamu.iut.marche.affichage;

import fr.univamu.iut.marche.traitement.Seeding;
import fr.univamu.iut.marche.traitement.acteurs.Marche;
import fr.univamu.iut.marche.traitement.acteurs.Participant;
import fr.univamu.iut.marche.traitement.acteurs.Vente;
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


public class venteProduitController extends VBox implements Initializable {

    @FXML
    private VBox contentVBox;

    @FXML
    private Text produitName;

    @FXML
    private Text produitQuantite;

    @FXML
    private Text popupAlert;

    @FXML
    private TextField QuantiteVoulus;

    private static homeController homeController;

    @FXML
    public void setViewToCatalog() throws IOException{
        contentVBox.getChildren().clear();
        contentVBox.getChildren().addAll(new catalogController());
    }


    private static String produitvente;

    private ObservableList<String> data = FXCollections.observableArrayList();

    public void vendreProduit()throws  IOException{
        if(QuantiteVoulus.getCharacters().toString().isEmpty()) popupAlert.setText("Quantité ??");
        else {
            Vente vente = Marche.getCompositionMarche().get(Integer.parseInt(catalogController.getSelectedProduit().substring(catalogController.getSelectedProduit().length()-1)));
            Seeding.getFxmlUser().proposerOffre(Participant.Produits.valueOf(vente.getProduitVendu().getClass().getSimpleName().toUpperCase()),Integer.valueOf(QuantiteVoulus.getCharacters().toString()), ((Double.valueOf(QuantiteVoulus.getCharacters().toString()))*vente.getPrix())/vente.getProduitVendu().getQuantite(),Seeding.getListeMarche().get(0));
            homeController.setSoldeFxmlUser();
            setViewToCatalog();
        }
    }

    public static void setHomeController(homeController h){
        homeController = h;
    }
    public venteProduitController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/VenteProduit.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(catalogController.getSelectedProduit());
        Participant participant = Participant.getParticipantbyId(Integer.parseInt(catalogController.getSelectedProduit().substring(catalogController.getSelectedProduit().length()-1)));
        Vente vente = Marche.getCompositionMarche().get(Integer.parseInt(catalogController.getSelectedProduit().substring(catalogController.getSelectedProduit().length()-1)));
        produitName.setText(vente.getProduitVendu().getClass().getSimpleName());
        produitQuantite.setText("Quantitée : " + String.valueOf(vente.getProduitVendu().getQuantite()));

    }
}
