package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import oracle.jdbc.driver.OracleDriver;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.sql.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.swing.JRadioButton;
public class ControllermoneyA {
    @FXML
    private ComboBox donatefor;
    @FXML
    private TextField visa;
    @FXML
    private TextField money;
    @FXML
    private ComboBox typeofmoney;
    @FXML
    private Button home;
    @FXML
    private Button donate;
    @FXML
    private Button donatemoney;
    @FXML
    private Button AddAchiv;
    @FXML
    private Button Donlist;
    @FXML
    private Button Achivments;
    @FXML
    private Button about;
    @FXML
    private Button logout;

    @FXML
    private Text lbl;
    String ssn;
    private FileInputStream filename;
    ObservableList<String> options =
            FXCollections.observableArrayList(
                    "Nibras Association for Community Development",
                    "Palestine House Association",
                    "Palestinian Renaissance Association",
                    "Truth and Justice Association",
                    "Kuwaiti Zakat House",
                    "The Purity Association"
            );
    ObservableList<String> optionsmoney =
            FXCollections.observableArrayList(
                    "£",
                    "¥",
                    "€",
                    "$"
            );
    @FXML
    public void initialize(){
        typeofmoney.setItems(optionsmoney);
        typeofmoney.setPromptText("Select...");

        donatefor.setItems(options);
        donatefor.setPromptText("Select...");

    }
    public void Set_money(ActionEvent event) {
        try {
            DriverManager.registerDriver(new OracleDriver());
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(url, "C##ZLProject", "12345");

            String query = ("SELECT * FROM login ORDER BY logid desc");
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) ssn=rs.getString("logssn");
            PreparedStatement ps = con.prepareStatement("insert into Donation values(?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1,1);
            ps.setString(2,donatefor.getValue().toString());
            ps.setString(3, visa.getText());
            ps.setInt(4,Integer.parseInt(money.getText()));
            ps.setString(5,"");
            ps.setString(6,"");
            ps.setInt(7,0);
            ps.setInt(8,1);
            ps.setInt(9,0);
            ps.setString(10,ssn);
            ps.setBinaryStream(11,filename);
            ps.executeUpdate();
            con.setAutoCommit(false);
            con.commit();
            con.close();
            lbl.setText("Thanks for donate!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage().toString());
            lbl.setText("Make sure you have entered everything correctly");
        }


    }
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
    void Golist(ActionEvent e)throws IOException {
        if(e.getSource()==Donlist){
            Stage stage = (Stage)Donlist.getScene().getWindow();
            stage.close();
            Stage primaryStage=new Stage();
            Parent root= FXMLLoader.load(getClass().getResource("listA.fxml"));
            primaryStage.setTitle("Together we can");
            Image image=new Image(("sample/give-love.png"));
            primaryStage.getIcons().add(image);
            Scene scene=new Scene(root, 882, 790);
            primaryStage.setScene(scene);
            primaryStage.show();
            scene.getStylesheets().add(getClass().getResource("list.css").toExternalForm());
        }
    }
    @FXML
    void GoAchivments(ActionEvent e)throws IOException {
        if(e.getSource()==Achivments){
            Stage stage = (Stage)Achivments.getScene().getWindow();
            stage.close();
            Stage primaryStage=new Stage();
            Parent root= FXMLLoader.load(getClass().getResource("achivmentsA.fxml"));
            primaryStage.setTitle("Together we can");
            Image image=new Image(("sample/give-love.png"));
            primaryStage.getIcons().add(image);
            Scene scene=new Scene(root, 782, 790);
            primaryStage.setScene(scene);
            primaryStage.show();
            scene.getStylesheets().add(getClass().getResource("achivments.css").toExternalForm());
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


}
