package com.example.proiectfinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class successfulController {
    @FXML
    public Button quizzButton;
    @FXML
    public Button profileButton;
    @FXML
    public Button exitButton;
    @FXML
    public Button logOutButton;
    @FXML
    public Button leaderboardButton;
    @FXML
    private final String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    private final String user = "postgres";
    private final String password = "rel12345";
    Connection con = null;
    PreparedStatement pst =null;
    public void onLeaderboard() throws IOException {
        Stage stage = (Stage) leaderboardButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("leaderboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 463, 573);
        stage.setTitle("QuizzApp!");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onQuizzButton() throws IOException {
        Stage stage = (Stage) quizzButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("quizzChooser.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("QuizzApp!");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onProfileButton() throws  IOException{
        Stage stage = (Stage) profileButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("myProfile.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 536, 474);
        stage.setTitle("QuizzApp!");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onLogOutButton() throws  IOException{
        try {
            String query = "Update users SET loggedin=false WHERE loggedin=true";
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(query);
            pst.executeQuery();
        } catch (Exception x) {
            System.out.println(x);
        }

        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("firstFrame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 400);
        stage.setTitle("QuizzApp!");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onExitButton(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }


}
