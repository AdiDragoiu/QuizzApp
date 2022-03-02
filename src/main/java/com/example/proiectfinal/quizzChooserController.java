package com.example.proiectfinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class quizzChooserController {
    private final String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    private final String user = "postgres";
    private final String password = "rel12345";
    Connection con = null;
    PreparedStatement pst =null;
    @FXML
    public Button All;
    @FXML
    public Button Music;
    @FXML
    public Button Biology;
    @FXML
    public Button Geography;
    @FXML
    public Button History;
    @FXML
    public Button Art;
    @FXML
    public Button Back;

    public void onAll() throws IOException {
          database(0);
          goToQuizz();
    }
    public void onMusic() throws IOException {
          database(1);
          goToQuizz();
    }
    public void onBiology() throws IOException {
         database(5);
         goToQuizz();
    }
    public void onGeography() throws IOException {
        database(3);
        goToQuizz();
    }
    public void onHistory() throws IOException {
         database(4);
         goToQuizz();
    }
    public void onArt() throws IOException {
        database(2);
        goToQuizz();
    }
    public void goToQuizz() throws IOException {
        Stage stage = (Stage) All.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Quizz.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 983, 500);
        stage.setTitle("QuizzApp!");
        stage.setScene(scene);
        stage.show();
    }
    public void onBack() throws IOException {
        Stage stage = (Stage) Back.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginSuccessful.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("QuizzApp!");
        stage.setScene(scene);
        stage.show();
    }
    public void database(int q){
        try {
            String query = "Update users Set chosenq_type = ? where loggedin= 'true'";
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(query);
            pst.setInt(1, q);
            pst.executeUpdate();
        } catch (SQLException y)
        {
            System.out.println(y.getMessage());
        }

    }






}
