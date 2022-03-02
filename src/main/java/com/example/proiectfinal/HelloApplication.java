package com.example.proiectfinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class HelloApplication extends Application {
    private final String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    private final String user = "postgres";
    private final String password = "rel12345";
    Connection con = null;
    PreparedStatement pst =null;
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("firstFrame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 400);
        stage.setTitle("QuizzApp!");
        JDBC();
        resetCurrently_used();
        stage.setScene(scene);
        stage.show();

    }
    public void JDBC(){
        try {
            String query = "Update users SET loggedin=false WHERE loggedin=true";
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(query);
            pst.executeQuery();
        } catch (Exception x) {
            System.out.println(x);
        }
    }
    public void resetCurrently_used() {
        try {
            String query = "UPDATE questions SET currently_used = '0'";
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(query);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}