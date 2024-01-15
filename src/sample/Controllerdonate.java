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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import oracle.jdbc.datasource.OracleDataSource;
import oracle.jdbc.driver.OracleDriver;

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.sql.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.swing.JRadioButton;

public class Controllerdonate {
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
    private TextField quantity;
    @FXML
    private TextArea desc;
    @FXML
    private ComboBox type;
    @FXML
    private ComboBox Charity;
    @FXML
    private Button btn1;
    @FXML
    private Button add;
    @FXML
    private ImageView img;
    @FXML
    private Text txt;
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
    ObservableList<String> optionsT =
            FXCollections.observableArrayList(
                    "Food",
                    "Furniture",
                    "Clothes",
                    "Book",
                    "Others"
            );

    @FXML
    public void initialize(){
        type.setItems(optionsT);
        type.setPromptText("Select...");

        Charity.setItems(options);
        Charity.setPromptText("Select...");


    }
    @FXML
    public void Choos_Image(ActionEvent e) throws FileNotFoundException {
        Stage stage=new Stage();
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Select an Image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG","*.png"),new FileChooser.ExtensionFilter("JPEG","*.jpg"));
        File selectedfile=fileChooser.showOpenDialog(stage);
        Image originalphoto=new Image(selectedfile.toURI().toString());
        try {
            filename=new FileInputStream(selectedfile);



        } catch (Exception ex) {

        }
        img.setImage(originalphoto);

    }
    @FXML
    public void Set_donation(ActionEvent event) {
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
            ps.setString(2,Charity.getValue().toString());
            ps.setInt(3,0);
            ps.setInt(4,0);
            ps.setString(5,type.getValue().toString());
            ps.setString(6,desc.getText());
            ps.setInt(7,Integer.parseInt(quantity.getText()));
            ps.setInt(8,0);
            ps.setInt(9,1);
            ps.setString(10,ssn);
            ps.setBinaryStream(11,filename);
            ps.executeUpdate();
            con.setAutoCommit(false);
            con.commit();
            con.close();
            txt.setText("Thanks for donate!!");

        } catch (Exception e) {
            txt.setText("Make sure you have entered everything correctly");
        }


    }
    @FXML
    void Gohome(ActionEvent e)throws IOException {
        if(e.getSource()==home){
            Stage stage = (Stage)home.getScene().getWindow();
            stage.close();
            Stage primaryStage=new Stage();
            Parent root= FXMLLoader.load(getClass().getResource("welcome.fxml"));
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
    void GoMoney(ActionEvent e)throws IOException {
        if(e.getSource()==donatemoney){
            Stage stage = (Stage)donatemoney.getScene().getWindow();
            stage.close();
            Stage primaryStage=new Stage();
            Parent root= FXMLLoader.load(getClass().getResource("DonateMoney.fxml"));
            primaryStage.setTitle("Official Donation");
            Image image=new Image(("sample/give-love.png"));
            primaryStage.getIcons().add(image);
            Scene scene=new Scene(root, 782, 790);
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
            Parent root= FXMLLoader.load(getClass().getResource("list.fxml"));
            primaryStage.setTitle("Official Donation");
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
            Parent root= FXMLLoader.load(getClass().getResource("achivments.fxml"));
            primaryStage.setTitle("Official Donation");
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
            Parent root= FXMLLoader.load(getClass().getResource("About.fxml"));
            primaryStage.setTitle("Official Donation");
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
            primaryStage.setTitle("Official Donation");
            Image image=new Image(("sample/give-love.png"));
            primaryStage.getIcons().add(image);
            Scene scene=new Scene(root,882, 790);
            primaryStage.setScene(scene);
            primaryStage.show();
            scene.getStylesheets().add(getClass().getResource("Main.css").toExternalForm());
        }
    }
}
