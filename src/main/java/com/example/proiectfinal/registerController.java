package com.example.proiectfinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class registerController {
    private final String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    private final String user = "postgres";
    private final String password = "rel12345";
    Connection con = null;
    PreparedStatement pst =null;
    PreparedStatement pst2 = null;
    @FXML
    public Button backButton;
    @FXML
    public Button confirmButton;

    @FXML
    public TextField firstName;
    @FXML
    public TextField username;
    @FXML
    public TextField lastName;
    @FXML
    public TextField email;
    @FXML
    public TextField pass;
    @FXML
    public TextField pass2;
    @FXML
    public Label error;


    @FXML
    public void onBackButton() throws IOException {

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("firstFrame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 415);
        stage.setTitle("QuizzApp!");
        stage.setScene(scene);
        stage.show();
    }
    public void onConfirmButton() throws  IOException {
        String pas = pass.getText();
        String pas2 = pass2.getText();
        int ok =0;
        if (!pas.equalsIgnoreCase(pas2)){
            error.setVisible(true);
            ok =1;
        }


        else {
            try {
                String query = "INSERT INTO users(first_name,last_name,username,email,password) VALUES(?, ?, ?, ?, ?)";
                String query2 = "Insert into profileinfo(username) Values(?)";
              //  String query3 = "Update profileinfo Set q_type0=0,q_type1=0,q_type2=0,q_type3=0,q_type4=0,q_type5=0 where"

                con = DriverManager.getConnection(url, user, password);
                pst = con.prepareStatement(query);
                pst2 = con.prepareStatement(query2);
                pst.setString(1, firstName.getText());
                pst.setString(2, lastName.getText());
                pst.setString(3, username.getText());
                pst.setString(4, email.getText());
                pst.setString(5, pass.getText());
                pst2.setString(1, username.getText());
                pst.executeUpdate();
                pst2.executeUpdate();

            } catch (SQLException y) {
                error.setVisible(true);
                ok =1;
            }

        }
        if(ok==0) {
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("firstFrame.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("QuizzApp!");
            stage.setScene(scene);
            stage.show();
        }

    }

}
