package com.example.proiectfinal;



import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class quizzController {
    List<String> intrebari = new ArrayList<String>();

    List<Integer> raspunsuri = new ArrayList<Integer>();
    String[][] variante = {
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
    };
    int correct,total;
    String username;
    @FXML
    public Label quizzLabel;
    @FXML
    public Button A;
    @FXML
    public Button B;
    @FXML
    public Button C;
    @FXML
    public Button D;
    @FXML
    public Pane quizzPane;
    @FXML
    public Label quizzNumber;
    @FXML
    public Label correctLabel;
    @FXML
    public Button backButton;
    int completate = 0;
    int categorie = 0;
    int index = 0;
    int i = 0;
    int totalq=0;
    int correct_guesses = 0;
    private final String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    private final String user = "postgres";
    private final String password = "rel12345";
    Connection con = null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null;
    ResultSet rs = null;
    int q = 0;

    public void onA() throws IOException {
        if (raspunsuri.get(index) == 1) {
            A.setStyle("-fx-background-color: #55ff00; ");
            correct_guesses++;
        }
        displayAnswer();
    }

    public void onB() throws IOException {
        if (raspunsuri.get(index) == 2) {
            B.setStyle("-fx-background-color: #55ff00; ");
            correct_guesses++;
        }
        displayAnswer();
    }

    public void onC() throws IOException {
        if (raspunsuri.get(index) == 3) {
            C.setStyle("-fx-background-color: #55ff00; ");
            correct_guesses++;
        }
        displayAnswer();
    }

    public void onD() throws IOException {
        if (raspunsuri.get(index) == 4) {
            D.setStyle("-fx-background-color: #55ff00; ");
            correct_guesses++;
        }
        displayAnswer();
    }

    public void displayAnswer() {
        A.setDisable(true);
        B.setDisable(true);
        C.setDisable(true);
        D.setDisable(true);
        if (raspunsuri.get(index) != 1)
            A.setStyle("-fx-background-color: #ff0000; ");
        if (raspunsuri.get(index) != 2)
            B.setStyle("-fx-background-color: #ff0000; ");
        if (raspunsuri.get(index) != 3)
            C.setStyle("-fx-background-color: #ff0000; ");
        if (raspunsuri.get(index) != 4)
            D.setStyle("-fx-background-color: #ff0000; ");

      /*  Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask(){

            @Override
            public void run() {
                A.setDisable(false);
                B.setDisable(false);
                C.setDisable(false);
                D.setDisable(false);
                A.setStyle("-fx-background-color: #838080; ");
                B.setStyle("-fx-background-color: #838080; ");
                C.setStyle("-fx-background-color: #838080; ");
                D.setStyle("-fx-background-color: #838080; ");
                index++;
               /* if(index == 10){
                    // result();
                    System.out.println("asdasf");
                }
                else{
                    quizzLabel.setText(intrebari.get(index));
                    A.setText(variante[index][0]);
                    B.setText(variante[index][1]);
                    C.setText(variante[index][2]);
                    D.setText(variante[index][3]);

                }*/

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    A.setDisable(false);
                    B.setDisable(false);
                    C.setDisable(false);
                    D.setDisable(false);
                    A.setStyle("-fx-background-color: #1700FF; ");
                    B.setStyle("-fx-background-color:  #1700FF; ");
                    C.setStyle("-fx-background-color:  #1700FF; ");
                    D.setStyle("-fx-background-color:  #1700FF; ");
                    index++;
                    nextQuestion();
                });
            }
        }, 1000);


        //  index++;

        //nextQuestion();
    }


    public void initialize() {
        A.setStyle("-fx-background-color: #1700FF; ");
        B.setStyle("-fx-background-color: #1700FF; ");
        C.setStyle("-fx-background-color: #1700FF; ");
        D.setStyle("-fx-background-color: #1700FF; ");
        while (q < 10) {
            JDBC();
            q++;
        }
        nextQuestion();
    }

    public void nextQuestion() {
        // quizzFrame.setBackground(new Image());
        if (index == 10) {
            result();

        } else {
            quizzNumber.setText("Quizz number " + (index + 1));
            quizzLabel.setText(intrebari.get(index));
            A.setText(variante[index][0]);
            B.setText(variante[index][1]);
            C.setText(variante[index][2]);
            D.setText(variante[index][3]);

        }
    }


    public void result() {
        A.setVisible(false);
        B.setVisible(false);
        C.setVisible(false);
        D.setVisible(false);
        quizzLabel.setVisible(false);
        quizzNumber.setText("FINISH!!!");
        correctLabel.setVisible(true);
        correctLabel.setText("Felicitari! ati raspuns corect la " + correct_guesses + " intrebari");
        backButton.setVisible(true);
        updateProfileInfo();

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


    public void JDBC() {

        try {
            String query = "Select chosenq_type from users where loggedin= true ";
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                categorie = rs.getInt("chosenq_type");
            }
        } catch (Exception z) {
            System.out.print(z);
        }
        try {
            String query = "SELECT * from users FULL OUTER JOIN profileinfo ON users.username = profileinfo.username WHERE loggedin= true ";
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                if (categorie == 0) {
                    completate = rs.getInt("q_type0");
                }
                if (categorie == 2) {
                    completate = rs.getInt("q_type1");
                }
                if (categorie == 3) {
                    completate = rs.getInt("q_type2");
                }
                if (categorie == 4) {
                    completate = rs.getInt("q_type3");
                }
                if (categorie == 5) {
                    completate = rs.getInt("q_type4");
                }
                if (categorie == 6) {
                    completate = rs.getInt("q_type5");
                }
                //totalq = rs.getInt("totalq");
                 username = rs.getString("username");
            }
        } catch (Exception z) {
            System.out.println(z);
        }
        if (categorie == 0) {
            try {

                String query = "Select question_id,question,ans_1,ans_2,ans_3,ans_4,correct_ans from questions order by random() LIMIT 1";
                con = DriverManager.getConnection(url, user, password);
                pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    int questionId = rs.getInt("question_id");
                    variante[i][0] = rs.getString("ans_1");
                    variante[i][1] = rs.getString("ans_2");
                    variante[i][2] = rs.getString("ans_3");
                    variante[i][3] = rs.getString("ans_4");
                    raspunsuri.add(rs.getInt("correct_ans"));
                    i++;


                    intrebari.add(rs.getString("question"));
                }

            } catch (Exception x) {
                System.out.print(x);

            }
        } else {
            try {

                String query = "Select question_id,question,ans_1,ans_2,ans_3,ans_4,correct_ans from questions where q_type = ? and currently_used=0 order by random() LIMIT 1";
                con = DriverManager.getConnection(url, user, password);
                pst = con.prepareStatement(query);
                pst.setInt(1, categorie);
                // pst.setInt(1,3);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    int questionId = rs.getInt("question_id");
                    variante[i][0] = rs.getString("ans_1");
                    variante[i][1] = rs.getString("ans_2");
                    variante[i][2] = rs.getString("ans_3");
                    variante[i][3] = rs.getString("ans_4");
                    raspunsuri.add(rs.getInt("correct_ans"));
                    i++;
                    intrebari.add(rs.getString("question"));

                    pst2 = con.prepareStatement("UPDATE questions SET currently_used='1' where question_id=?");
                    pst2.setInt(1, questionId);
                    pst2.executeUpdate();
                }

            } catch (Exception x) {
                System.out.print(x);

            }

        }

    }

    public void updateProfileInfo() {



       // completate++;
       // totalq++;
        System.out.println("Astea sunt din categoria " + categorie + " si au fost completate " +completate + " chestionare de genul" );
        //String query1 = "update profileinfo SET q_type = q_type1+1 where username = 'Grigori'";
        // String format("update profileinfo SET %s = %s +1 where username = '%s'","q_type1","q_type1","Grigori");
        // String sadf = "awrfawr";
       // String query = String.format("update profileinfo set %s = %s+1 where username = '%s'", "quizz_type0","completate",username);


     //   update profileinfo set q_type0 = q_type0+1, totalq = totalq+1 where username = 'Grigori';
        try {
            String query0 = "update profileinfo set q_type0 = q_type0 +1, totalq = totalq+1 where username = ?";
            String query1 = "update profileinfo set q_type1 = q_type1 +1, totalq = totalq+1 where username = ?";
            String query2 = "update profileinfo set q_type2 = q_type2 +1, totalq = totalq+1 where username = ?";
            String query3 = "update profileinfo set q_type3 = q_type3 +1, totalq = totalq+1 where username = ?";
            String query4 = "update profileinfo set q_type4 = q_type4 +1, totalq = totalq+1 where username = ?";
            String query5 = "update profileinfo set q_type5 = q_type5 +1, totalq = totalq+1 where username = ?";
            String query6 = "update profileinfo set correct_ans= correct_ans + ? where username= ? ";
            con = DriverManager.getConnection(url, user, password);
            if(categorie == 0 ){
                pst = con.prepareStatement(query0);
                pst.setString(1,username);
                pst.executeUpdate();
            }
            if(categorie == 1 ){
                pst = con.prepareStatement(query1);
                pst.setString(1,username);
                pst.executeUpdate();
            }
            if(categorie == 2 ){
                pst = con.prepareStatement(query2);
                pst.setString(1,username);
                pst.executeUpdate();
            }
            if(categorie == 3 ){
                pst = con.prepareStatement(query3);
                pst.setString(1,username);
                pst.executeUpdate();
            }
            if(categorie == 4 ){
                pst = con.prepareStatement(query4);
                pst.setString(1,username);
                pst.executeUpdate();
            }
            if(categorie == 5 ){
                pst = con.prepareStatement(query5);
                pst.setString(1,username);
                pst.executeUpdate();
            }
            pst = con.prepareStatement(query6);
            pst.setInt(1,correct_guesses);
            pst.setString(2,username);
            pst.executeUpdate();


        } catch (Exception z) {
            System.out.print(z);
        }


        try {
            String query = "UPDATE questions SET currently_used = '0'";
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(query);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {

            String query = "SELECT * from users FULL OUTER JOIN profileinfo ON users.username = profileinfo.username WHERE loggedin= true";
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                 correct = rs.getInt("correct_ans");
                 total  = rs.getInt("totalq");
            }

        } catch (Exception x) {
            System.out.print(x);

        }
        total= total*10;
        float ratio =(float) correct/total*100;
        int a= 0;
        a= (int) ratio;
        System.out.println("Asta e correct: "+ correct + " si asta e total "+ total + " si asta e ratio: " + a);

        try{
            String query = "update profileinfo set scoreratio=? where username = ?";
            con = DriverManager.getConnection(url,user,password);
            pst = con.prepareStatement(query);
            pst.setInt(1,a);
            pst.setString(2,username);
            pst.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }


    }
}
