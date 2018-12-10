package fr.univamu.iut.marche.traitement;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private static Stage pStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Platform.setImplicitExit(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/home.fxml"));
        Pane root = loader.load();
        primaryStage.setScene(new Scene(root));

        primaryStage.setTitle("Marché Fermier");
        primaryStage.setResizable(false);
        primaryStage.show();
        pStage = primaryStage;

        primaryStage.setOnCloseRequest(event -> Platform.exit());
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }


    public static void main(String[] args) {
        new Seeding();
        launch(args);

        /**/

    }
}
