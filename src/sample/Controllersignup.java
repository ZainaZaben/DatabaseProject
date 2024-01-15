package sample;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import oracle.jdbc.driver.OracleDriver;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Controllersignup {
    @FXML
    private TextField ssn;
    @FXML
    private TextField Fname;
    @FXML
    private PasswordField Pass;
    @FXML
    private PasswordField Reppass;
    @FXML
    private TextField Lname;
    @FXML
    private TextField email;
    @FXML
    private TextField Phonenum;
    @FXML
    private RadioButton M;
    @FXML
    public RadioButton F;
    @FXML
    public Button btn;
    @FXML
    public TextField text;
    @FXML
    ToggleGroup group;
    @FXML
    private StackPane stack;
    @FXML
    private Button btnlog;
    @FXML
    public void initialize(){
        group = new ToggleGroup();
        M.setToggleGroup(group);
        F.setToggleGroup(group);

    }
    String tmpAU="U";
    public void Sign_Up(ActionEvent event){
        text.setEditable(false);
        if(ssn.getText().equals(null)||email.getText().equals("")||Fname.getText().equals("")||Lname.getText().equals("")||Pass.getText().equals("")||Reppass.getText().equals(""))
            text.setText("You should fill all field");
        else
        if(isValidEmailAddress(email.getText())) {
            if((email.getText().equals("laila2001249@hotmail.com"))||(email.getText().equals("zaina24@gmail.com")))
                tmpAU="A";
            String e=ssn.getText();
            if(!onlyDigits( e, e.length())){text.setStyle("-fx-background-color:  transparent;");
                text.setStyle("-fx-text-inner-color: red;");
                text.setText("Your ID Should Contain Only Digit");}
            else{
                String [] tempass=Pass.getText().split(" ");
                char charArry[]= Arrays.stream(tempass).collect(Collectors.joining()).toCharArray();
                if(!(isValidPassword(charArry,tempass))){
                    text.setStyle("-fx-background-color:  transparent;");
                    text.setStyle("-fx-text-inner-color: red;");
                    text.setText("Your password should include at least one digit and one symbol");
                }
                else{
                    String password;
                    password=Pass.getText();
                    String repeatpas=Reppass.getText();
                    if (password.equals(repeatpas)){
                        try {
                            DriverManager.registerDriver(new OracleDriver());
                            String url="jdbc:oracle:thin:@localhost:1521:xe";
                            Connection con = DriverManager.getConnection(url, "C##ZLProject", "12345");{
                                PreparedStatement ps = con.prepareStatement("insert into Doner values(?,?,?,?,?,?,?,?)");
                                ps.setString(1, ssn.getText());
                                ps.setString(2, email.getText());
                                ps.setString(3, Fname.getText());
                                ps.setString(4, Lname.getText());
                                ps.setString(5, Pass.getText());
                                ps.setInt(6, Integer.parseInt((Phonenum.getText())));
                                if (M.isSelected()) {
                                    ps.setString(7, "M");
                                } else ps.setString(7, "F");
                                ps.setString(8,tmpAU);
                                ps.executeUpdate();
                                con.setAutoCommit(false);
                                con.commit();
                                con.close();
                                    text.setText("Success");
                                if(event.getSource()==btn){
                                    Stage stage = (Stage)btn.getScene().getWindow();
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


                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,ex.getMessage().toString());

                            //text.setText("Error");

                        }}
                    else          {
                        text.setText("Your password in Repeat Password not the same as in Password");
                    }
                }}}
        else{
            text.setText("Invalid E_mail");
        }

    }
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
    public static boolean isValidPassword(char[] charPassword,String[] stringPassword){
        char c;
        int count=0;
        boolean flag=false;
        if(charPassword.length <8){
            return false;}
        else{
            for(char item :charPassword){
                if(!(Character.isLetterOrDigit(item)))
                    count++;}
            if(count ==0){return false;}
            else { for(char item :charPassword){
                if(!(Character.isLetterOrDigit(item))){
                    if(item =='['||item ==']'||item =='{'||item =='}'||item =='<'||item =='>'||item =='('||item ==')'){
                        flag = true ;
                        return false;}}}}
            if (flag == false){
                for(int i=0 ; i< stringPassword.length ;i++){
                    int countlower=0;
                    int countupper=0;
                    int countdigit=0;
                    for(int j=0 ; j < stringPassword[i].length();j++){
                        c= stringPassword[i].charAt(j);
                        if(Character.isLowerCase(c)){ countlower++;}
                        if(Character.isUpperCase(c)){ countupper++;}
                        if(Character.isDigit(c)){ countdigit++;}}
                    if(!(countlower>=1 && countupper>=1 && countdigit>=1)){ return false;}}}
            return true;}}
    public void meeting(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage root = FXMLLoader.load(getClass().getResource("//IdeaProjects//Startup screen//src//sample//sample.fxml"));
        stage.setScene(root.getScene());
    }
    public static boolean onlyDigits(String e, int nm){
        int i;
        for (i = 0; i < nm; i++) {
            if ((e.charAt(i) >= '0') && (e.charAt(i) <= '9')) {
                continue;
            }
            else
                break;
        }
        if(i==nm)
            return true;
        else
            return false;
    }
    @FXML
    void GoLogIn(ActionEvent e)throws IOException{
        if(e.getSource()==btnlog){
            Stage stage = (Stage)btnlog.getScene().getWindow();
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

}}
