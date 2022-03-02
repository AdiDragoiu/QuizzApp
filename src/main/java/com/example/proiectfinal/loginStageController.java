package com.example.proiectfinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.Text;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class loginStageController {
    private final String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    private final String user = "postgres";
    private final String password = "rel12345";
    Connection con = null;
    PreparedStatement pst =null;
    PreparedStatement pst2 = null;
    @FXML
    private Button backButton;
    @FXML
    private Button loginButton;
    @FXML
    private PasswordField pass;
    @FXML
    private TextField email;
    @FXML
    private Label error;
    @FXML
    private CheckBox box;
    @FXML
    protected void onBackButton() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("firstFrame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("QuizzApp!");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void onloginButton() throws IOException{
        String passText;
        String emailText;
        passText = pass.getText();
        emailText = email.getText();
        int ok=0;
        if( emailText.equals("Adi") && passText.equals("2")){
            ok= 1;
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminMainFrame.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("QuizzApp!");
            stage.setScene(scene);
            stage.show();
        }
        else {
            try {

                String query = "Select * FROM users WHERE email=? and password=?";
                con = DriverManager.getConnection(url,user,password);
                pst = con.prepareStatement(query);
                pst.setString(1,email.getText());
                pst.setString(2,pass.getText());
                ResultSet rs =pst.executeQuery();

                if(rs.next()){

                    String query2 = "UPDATE users SET loggedin=true where email =?";
                    pst2 = con.prepareStatement(query2);
                    pst2.setString(1, email.getText());
                    pst2.executeUpdate();


                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginSuccessful.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                    stage.setTitle("QuizzApp!");
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                   error.setVisible(true);
                }

            }catch(Exception x){
                System.out.println(x);

            }

        }

        /*Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("firstFrame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("QuizzApp!");
        stage.setScene(scene);
        stage.show();*/

    }
}
