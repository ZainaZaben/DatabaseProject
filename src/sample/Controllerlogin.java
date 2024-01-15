package sample;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.nashorn.internal.scripts.JO;
import oracle.jdbc.driver.OracleDriver;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Controllerlogin {
    @FXML
    private TextField Emailtext;
    @FXML
    private PasswordField Passtext;
    @FXML
    private Button btn;
    @FXML
    private Text lbl;
    @FXML
            private Button s;
    String SSN;
    String EMAIL;
    String ADMINUSER;
    public void Log_in(ActionEvent event){
        lbl.setDisable(true);
        String pass=Passtext.getText();
        try {
            DriverManager.registerDriver((new OracleDriver()));
            String url="jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(url, "C##ZLProject", "12345");
            String query="Select Email,Passwordd,Ssn,ADMINUSER from Doner where Email='"+Emailtext.getText()+"'";
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if(pass.equals(rs.getString("Passwordd")))
            {
                lbl.setText("Login Successes");
                PreparedStatement stmt = con.prepareStatement("insert into login values(?,?,?)");
                stmt.setInt(1,1);
                stmt.setString(2,rs.getString("Ssn"));
                stmt.setString(3,rs.getString("Email"));
                ADMINUSER=rs.getString("ADMINUSER");
                //stmt.setString(3,EMAIL);
                stmt.executeUpdate();
                con.setAutoCommit(false);
                con.commit();
                con.close();
                if(event.getSource()==btn){
                    Stage stage = (Stage)btn.getScene().getWindow();
                    stage.close();
                    if(ADMINUSER.equals("U")){
                    Stage primaryStage=new Stage();
                    Parent root= FXMLLoader.load(getClass().getResource("welcome.fxml"));
                    primaryStage.setTitle("Together we can");
                    Image image=new Image(("sample/give-love.png"));
                    primaryStage.getIcons().add(image);
                    Scene scene=new Scene(root,782, 790);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    scene.getStylesheets().add(getClass().getResource("welcome.css").toExternalForm());}
                else {
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
                }}
            else
                lbl.setText("Password or Email not correct");

        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null,ex.getMessage().toString());
            lbl.setText("Password or Email not correct");

        }
    }
    @FXML
    void GoSignUp(ActionEvent e)throws IOException {
        if(e.getSource()==s){
            Stage stage = (Stage)s.getScene().getWindow();
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

}
