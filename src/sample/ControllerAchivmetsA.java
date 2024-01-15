package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import oracle.jdbc.driver.OracleDriver;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.sql.*;

public class ControllerAchivmetsA {
    @FXML
    private Button home;
    @FXML
    private Button donate;
    @FXML
    private Button donatemoney;
    @FXML
    private Button Donlist;
    @FXML
    private Button Achivments;
    @FXML
    private Button about;
    @FXML
    private Button logout;
    @FXML
    private Button AddAchiv;
    @FXML
    void Gohome(ActionEvent e)throws IOException {
        if(e.getSource()==home){
            Stage stage = (Stage)home.getScene().getWindow();
            stage.close();
            Stage primaryStage=new Stage();
            Parent root= FXMLLoader.load(getClass().getResource("welcomAdmin.fxml"));
            primaryStage.setTitle("Together we can");
            Image image=new Image(("sample/give-love.png"));
            primaryStage.getIcons().add(image);
            Scene scene=new Scene(root, 782, 790);
            primaryStage.setScene(scene);
            primaryStage.show();
            scene.getStylesheets().add(getClass().getResource("welcome.css").toExternalForm());
        }
    }
    @FXML
    void Godonate(ActionEvent e)throws IOException {
        if(e.getSource()==donate){
            Stage primaryStage=new Stage();
            Parent root= FXMLLoader.load(getClass().getResource("DonateA.fxml"));
            primaryStage.setTitle("Together we can");
            Image image=new Image(("sample/give-love.png"));
            primaryStage.getIcons().add(image);
            Scene scene=new Scene(root, 782, 790);
            primaryStage.setScene(scene);
            primaryStage.show();
            scene.getStylesheets().add(getClass().getResource("Donate.css").toExternalForm());
        }
    }
    @FXML
    void Godonatemoney(ActionEvent e)throws IOException {
        if(e.getSource()==donatemoney){
            Stage stage = (Stage)donatemoney.getScene().getWindow();
            stage.close();
            Stage primaryStage=new Stage();
            Parent root= FXMLLoader.load(getClass().getResource("DonateMoneyA.fxml"));
            primaryStage.setTitle("Together we can");
            Image image=new Image(("sample/give-love.png"));
            primaryStage.getIcons().add(image);
            Scene scene=new Scene(root, 882, 790);
            primaryStage.setScene(scene);
            primaryStage.show();
            scene.getStylesheets().add(getClass().getResource("DonateMoney.css").toExternalForm());
        }
    }
    @FXML
    void Golist(ActionEvent e)throws IOException {
        if(e.getSource()==Donlist){
            Stage stage = (Stage)Donlist.getScene().getWindow();
            stage.close();
            Stage primaryStage=new Stage();
            Parent root= FXMLLoader.load(getClass().getResource("listA.fxml"));
            primaryStage.setTitle("Together we can");
            Image image=new Image(("sample/give-love.png"));
            primaryStage.getIcons().add(image);
            Scene scene=new Scene(root, 782, 790);
            primaryStage.setScene(scene);
            primaryStage.show();
            scene.getStylesheets().add(getClass().getResource("list.css").toExternalForm());
        }
    }
    @FXML
    void Goabout(ActionEvent e)throws IOException {
        if(e.getSource()==about){
            Stage stage = (Stage)about.getScene().getWindow();
            stage.close();
            Stage primaryStage=new Stage();
            Parent root= FXMLLoader.load(getClass().getResource("AboutA.fxml"));
            primaryStage.setTitle("Together we can");
            Image image=new Image(("sample/give-love.png"));
            primaryStage.getIcons().add(image);
            Scene scene=new Scene(root, 782, 790);
            primaryStage.setScene(scene);
            primaryStage.show();
            scene.getStylesheets().add(getClass().getResource("About.css").toExternalForm());
        }
    }
    @FXML
    void Log_Out(ActionEvent e)throws IOException {
        if(e.getSource()==logout){
            Stage stage = (Stage)logout.getScene().getWindow();
            stage.close();
            Stage primaryStage=new Stage();
            Parent root= FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Together we can");
            Image image=new Image(("sample/give-love.png"));
            primaryStage.getIcons().add(image);
            Scene scene=new Scene(root, 882, 790);
            primaryStage.setScene(scene);
            primaryStage.show();
            scene.getStylesheets().add(getClass().getResource("Main.css").toExternalForm());
        }
    }
    @FXML
    void goAddAchiv(ActionEvent e)throws IOException{
        if(e.getSource()==AddAchiv){
            Stage stage = (Stage)AddAchiv.getScene().getWindow();
            stage.close();
            Stage primaryStage=new Stage();
            Parent root= FXMLLoader.load(getClass().getResource("AddAchivments.fxml"));
            primaryStage.setTitle("Together we can");
            Image image=new Image(("sample/give-love.png"));
            primaryStage.getIcons().add(image);
            Scene scene=new Scene(root, 782, 790);
            primaryStage.setScene(scene);
            primaryStage.show();
            scene.getStylesheets().add(getClass().getResource("addachiv.css").toExternalForm());
        }
    }
    @FXML
    private Button btnn;
    @FXML
    private TextArea TXT;

    public void Ach(ActionEvent event) {
        try {
            TXT.setDisable(true);

            DriverManager.registerDriver(new OracleDriver());
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(url, "C##ZLProject", "12345");
            String q1 = "select TEXTT FROM AppAchievements";
            PreparedStatement ps = con.prepareStatement(q1);
            ResultSet rslt = ps.executeQuery();
            while (rslt.next()) {
                String temp=rslt.getString("TEXTT");
                TXT.appendText(temp +"\n");
                TXT.appendText("_____________________________");
                TXT.appendText("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
