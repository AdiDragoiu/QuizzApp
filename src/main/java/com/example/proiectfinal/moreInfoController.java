package com.example.proiectfinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.*;
import java.util.HashMap;

public class moreInfoController {

    private final String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    private final String user = "postgres";
    private final String password = "rel12345";
    Connection con = null;
    PreparedStatement pst =null;
    ResultSet rs = null;

    @FXML
    public Button backButton;
    @FXML
    public Label biology;
    @FXML
    public Label history;
    @FXML
    public Label geography;
    @FXML
    public Label art;
    @FXML
    public Label music;
    @FXML
    public Label ratio;
    @FXML
    public Label all;
    @FXML
    public Button resetButton;
    String username;
    public void initialize(){
        try {

            String query = "SELECT * from users FULL OUTER JOIN profileinfo ON users.username = profileinfo.username WHERE loggedin= true";
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                all.setText(rs.getString("q_type0"));
                history.setText( rs.getString("q_type4")) ;
                geography.setText( rs.getString("q_type3"));
                biology.setText(rs.getString("q_type5"));
                art.setText(rs.getString("q_type2"));
                music.setText(rs.getString("q_type1"));
                int totalq= rs.getInt("totalq");
                int a = rs.getInt("correct_ans");
                totalq= totalq* 10;

                ratio.setText( a + " / "+  totalq );
            }
        } catch (SQLException y)
        {
            System.out.println(y.getMessage());
        }
    }
    public void onBack() throws  Exception{
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("myProfile.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 536, 474);
        stage.setTitle("QuizzApp!");
        stage.setScene(scene);
        stage.show();
    }

    public void onReset(){
        jdbc();
        all.setText("0");
        history.setText("0") ;
        geography.setText( "0");
        biology.setText("0");
        art.setText("0");
        music.setText("0");
        ratio.setText("0");
       /* FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("leaderboard.fxml"));
        leaderboardController lc = fxmlLoader.getController();
        lc.initialize();*/
    }

    public void jdbc(){
        try {
            String query = "SELECT * from users FULL OUTER JOIN profileinfo ON users.username = profileinfo.username WHERE loggedin= true ";
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                username = rs.getString("username");
            }
        } catch (Exception z) {
            System.out.println(z);
        }
        System.out.println(username);
        try{
        String query = "update profileinfo set q_type0 =0,q_type1=0,q_type2=0,q_type3=0,q_type4=0,q_type5=0,totalq=0,scoreratio=0,correct_ans=0 where username =?";
        con = DriverManager.getConnection(url, user, password);
        pst = con.prepareStatement(query);
        pst.setString(1,username);
        pst.executeUpdate();
    } catch (SQLException y)
    {
        System.out.println(y.getMessage());
    }
    }




}
