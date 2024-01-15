package sample;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import oracle.jdbc.datasource.OracleDataSource;
import oracle.jdbc.driver.OracleDriver;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.swing.*;

public class ControllerlistA implements Initializable {
    @FXML
    private Text lbl;
    @FXML
    private TableView<mylist> table;
    @FXML
    private TableColumn<mylist, String> Tssn;
    @FXML
    private TableColumn<mylist, String> Tfname;
    @FXML
    private TableColumn<mylist, String> Tlname;
    @FXML
    private TableColumn<mylist, String> Tdonatefor;
    @FXML
    private TableColumn<mylist, Integer> Tmoney;
    @FXML
    private TableColumn<mylist, String> Titem;
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
private Button Report;
    @FXML
    ArrayList<String> data = new ArrayList<String>();

    ObservableList<mylist> My_List = FXCollections.observableArrayList();




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            DriverManager.registerDriver((new OracleDriver()));
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(url, "C##ZLProject", "12345");
            String query = ("SELECT * FROM login ORDER BY logid desc");
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            rs.next();
            boolean flge=false;
            String query1 = ("SELECT Ssn FROM Doner ");
            PreparedStatement st1 = con.prepareStatement(query1);
            ResultSet rs1 = st1.executeQuery();
            String Tempssn = null;
            while(rs1.next()){
                if(rs1.getString("Ssn").equals(rs.getString("logssn"))) flge=true;
                if(flge==true) Tempssn=rs.getString("logssn");

            }
            System.out.println(Tempssn);
            if(flge){

                    String q1 = "select d.Ssn,d.Fname,d.Lname,do.DonateFor,do.money,do.forignitem " +
                            "from   Doner d " +
                            "join   donation do " +
                            "on     d.Ssn = do.DONSSN ";
                    PreparedStatement ps = con.prepareStatement(q1);
                    ResultSet rslt = ps.executeQuery();
                    String type;
                    while (rslt.next()) {
                        if (rslt.getInt(6) == 1) {
                            type = "Money";
                        } else {
                            type = "Item";
                        }
                        My_List.add(new mylist(rslt.getString(1), rslt.getString(2), rslt.getString(3), rslt.getString(4), rslt.getInt(5)
                                , type));
                    }
                }
            else{ lbl.setText("The Doner is not exist");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        Tssn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        Tfname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        Tlname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        Tdonatefor.setCellValueFactory(new PropertyValueFactory<>("donate_for"));
        Tmoney.setCellValueFactory(new PropertyValueFactory<>("money"));
        Titem.setCellValueFactory(new PropertyValueFactory<>("money_or_item"));
        table.setItems(My_List);

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
    @FXML
    public void ReporAction(ActionEvent e) {
        try {
            OracleDataSource ods=new oracle.jdbc.pool.OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("C##ZLProject");
            ods.setPassword("12345");
            Connection con= ods.getConnection();
            InputStream inputStream=new FileInputStream(new File("src/sample/Reportproject.jrxml"));
            JasperDesign jd= JRXmlLoader.load(inputStream);
            JasperReport jr= JasperCompileManager.compileReport(jd);
            JasperPrint jp= JasperFillManager.fillReport(jr,null,con);
            OutputStream output=new FileOutputStream(new File("DonReport.pdf"));
            JasperExportManager.exportReportToPdfStream(jp,output);
            output.close();
            inputStream.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
