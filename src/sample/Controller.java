package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    void GoSignUp(ActionEvent e)throws IOException{
        if(e.getSource()==btn2){
            Stage stage = (Stage)btn2.getScene().getWindow();
            stage.close();
            Stage primaryStage=new Stage();
            Parent root= FXMLLoader.load(getClass().getResource("signup.fxml"));
            primaryStage.setTitle("Together we can");
            Image image=new Image(("sample/give-love.png"));
            primaryStage.getIcons().add(image);
            Scene scene=new Scene(root, 680, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
            scene.getStylesheets().add(getClass().getResource("signup.css").toExternalForm());
        }
    }
    @FXML
    void GoLogIn(ActionEvent e)throws IOException{
        if(e.getSource()==btn1){
            Stage stage = (Stage)btn1.getScene().getWindow();
            stage.close();
            Stage primaryStage=new Stage();
            Parent root= FXMLLoader.load(getClass().getResource("login.fxml"));
            primaryStage.setTitle("Together we can");
            Image image=new Image(("sample/give-love.png"));
            primaryStage.getIcons().add(image);
            Scene scene=new Scene(root, 750, 790);
            primaryStage.setScene(scene);
            primaryStage.show();
            scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        }
    }

}
