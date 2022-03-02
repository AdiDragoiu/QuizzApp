package com.example.proiectfinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class myProfileController {
    private final String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    private final String user = "postgres";
    private final String password = "rel12345";
    Connection con = null;
    PreparedStatement pst =null;
    ResultSet rs = null;

    @FXML
    public Button backButton;
    @FXML
    public Label label1;
    @FXML
    public Label label2;
    @FXML
    public Label label3;
    @FXML
    public Label label4;
    @FXML
    public Button quizzesButton;

    public void initialize(){
        try {

            String query = "SELECT * from users FULL OUTER JOIN profileinfo ON users.username = profileinfo.username WHERE loggedin= true";
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                label1.setText(rs.getString("first_name"));
                label2.setText( rs.getString("last_name")) ;
                label3.setText( rs.getString("username"));
                label4.setText(rs.getString("email"));
                quizzesButton.setText(rs.getString("totalq"));
            }
        } catch (SQLException y)
        {
            System.out.println(y.getMessage());
        }
    }
    public void onback() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginSuccessful.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("QuizzApp!");
        stage.setScene(scene);
        stage.show();
    }

    public void onQuizzesButton() throws  IOException{
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("moreInfo.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 463);
        stage.setTitle("QuizzApp!");
        stage.setScene(scene);
        stage.show();

    }
}
