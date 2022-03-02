package com.example.proiectfinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class leaderboardController {
    @FXML
    public Button backButton;
    @FXML
    public Label player1;
    @FXML
    public Label player2;
    @FXML
    public Label player3;
    @FXML
    public Label player4;
    @FXML
    public Label player5;
    @FXML
    public Label player6;
    @FXML
    public Label player7;
    @FXML
    public Label player8;
    @FXML
    public Label player9;
    @FXML
    public Label player10;
    @FXML
    public Label player11;
    @FXML
    public Label player12;
    @FXML
    public Label score1;
    @FXML
    public Label score2;
    @FXML
    public Label score3;
    @FXML
    public Label score4;
    @FXML
    public Label score5;
    @FXML
    public Label score6;
    @FXML
    public Label score7;
    @FXML
    public Label score8;
    @FXML
    public Label score9;
    @FXML
    public Label score10;
    @FXML
    public Label score11;
    @FXML
    public Label score12;
    private final String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    private final String user = "postgres";
    private final String password = "rel12345";
    Connection con = null;
    PreparedStatement pst =null;
    String username;
    int score;

    @FXML
    public void initialize(){

        ArrayList<Persoane> array = new ArrayList<Persoane>();

        try{
            String query = "SELECT username,scoreratio FROM profileinfo ORDER BY scoreratio DESC LIMIT 12";
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                username = rs.getString("username");
                score = rs.getInt("scoreratio");


                System.out.println("SCORUUU  " + score);
                Persoane n = new Persoane(username,score);
                array.add(n);
            }
        }catch(Exception x){
            System.out.println(x);
        }
        Collections.sort(array);
        asdf(array);
    }

    public void asdf(ArrayList<Persoane>  array){

        int i=1;

        for(int q=0 ; q <array.size();q++){
            if(i==1){
                player1.setText(String.valueOf(array.get(q).getUsername()));
                score1.setText(String.valueOf(array.get(q).getScor()) );

            }

            if(i==2){
                player2.setText(String.valueOf(array.get(q).getUsername()));
                score2.setText(String.valueOf(array.get(q).getScor()));
            }
            if(i==3){
                player3.setText(String.valueOf(array.get(q).getUsername()));
                score3.setText(String.valueOf(array.get(q).getScor()));
            }
            if(i==4){
                player4.setText(String.valueOf(array.get(q).getUsername()));
                score4.setText(String.valueOf(array.get(q).getScor()));
            }
            if(i==5){
                player5.setText(String.valueOf(array.get(q).getUsername()));
                score5.setText(String.valueOf(array.get(q).getScor()));
            }
            if(i==6){
                player6.setText(String.valueOf(array.get(q).getUsername()));
                score6.setText(String.valueOf(array.get(q).getScor()));
            }
            if(i==7){
                player7.setText(String.valueOf(array.get(q).getUsername()));
                score7.setText(String.valueOf(array.get(q).getScor()));
            }
            if(i==8){
                player8.setText(String.valueOf(array.get(q).getUsername()));
                score8.setText(String.valueOf(array.get(q).getScor()));
            }
            if(i==9){
                player9.setText(String.valueOf(array.get(q).getUsername()));
                score9.setText(String.valueOf(array.get(q).getScor()));
            }
            if(i==10){
                player10.setText(String.valueOf(array.get(q).getUsername()));
                score10.setText(String.valueOf(array.get(q).getScor()));
            }
            if(i==11){
                player11.setText(String.valueOf(array.get(q).getUsername()));
                score11.setText(String.valueOf(array.get(q).getScor()));
            }
            if(i==12){
                player12.setText(String.valueOf(array.get(q).getUsername()));
                score12.setText(String.valueOf(array.get(q).getScor()));
            }
            i++;
        }

    }


    public void onBack() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginSuccessful.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("QuizzApp!");
        stage.setScene(scene);
        stage.show();
    }
}
