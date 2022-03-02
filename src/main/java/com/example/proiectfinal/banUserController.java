package com.example.proiectfinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class banUserController {
    @FXML
    public Button backButton;
    @FXML
    public Button confirmButton;
    @FXML
    public TextField username;
    @FXML
    public TextField reason;
    private final String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    private final String user = "postgres";
    private final String password = "rel12345";
    Connection con = null;
    PreparedStatement pst =null;
    PreparedStatement pst2 = null;
    public void onBack() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminMainFrame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("QuizzApp!");
        stage.setScene(scene);
        stage.show();
    }

    public void onConfirm(){
        if(username.getText().equals("") || reason.getText().equals(""))
            JOptionPane.showMessageDialog(null, "Trebuie sa introduci informatii in ambele casete");
        else{
            int ok=0;

            try{
                String query ="Delete from users where username=?";
                con = DriverManager.getConnection(url,user,password);
                pst = con.prepareStatement(query);
                pst.setString(1,username.getText());
                pst.executeQuery();
                ok=1;
            }catch(Exception kk){
            }
            try{
                String query = "Delete from profileinfo where username=?";
                con = DriverManager.getConnection(url,user,password);
                pst = con.prepareStatement(query);
                pst.setString(1,username.getText());
                pst.executeQuery();
            }catch(Exception xd){
            }

            try {

                String query = "INSERT INTO bannedusers(username,reason) Values(?,?)";
                con = DriverManager.getConnection(url, user, password);
                pst = con.prepareStatement(query);
                pst.setString(1, username.getText());
                pst.setString(2, reason.getText());
                // pst.setInt(1,3);
                pst.executeQuery();
            } catch (Exception x) {
            }
            JOptionPane.showMessageDialog(null, "User banned !");
            username.setText("");
            reason.setText("");
        }

    }


}
