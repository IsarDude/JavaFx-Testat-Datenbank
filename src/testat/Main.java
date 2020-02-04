package testat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    private Stage primaryStage;
    private FXMLLoader loader;
    private MainController mainController;
    @Override
    public void start(Stage primaryStage) throws Exception{



        loader=new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        mainController= loader.getController();

        Parent root = loader.load();
        this.primaryStage=primaryStage;
        this.primaryStage.setTitle("Adressbuch");
        primaryStage.setScene(new Scene(root, 500, 475));

        //verbindung main mit MainController
        mainController= loader.getController();
        mainController.setStage(primaryStage);
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }
}
