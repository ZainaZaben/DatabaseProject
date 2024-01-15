package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Together we can");
        Scene scene=new Scene(root, 882, 790);
        Image image=new Image(("sample/give-love.png"));
        primaryStage.getIcons().add(image);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().add(getClass().getResource("Main.css").toExternalForm());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
