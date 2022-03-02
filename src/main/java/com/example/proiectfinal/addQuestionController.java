package com.example.proiectfinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addQuestionController {
    private final String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    private final String user = "postgres";
    private final String password = "rel12345";
    Connection con = null;
    PreparedStatement pst =null;
    PreparedStatement pst2 = null;
    @FXML
    public Button confirm;
    @FXML
    public Button cancel;
    @FXML
    public ComboBox combo;
    @FXML
    public TextField intrebare;
    @FXML
    public TextField v1;
    @FXML
    public TextField v2;
    @FXML
    public TextField v3;
    @FXML
    public TextField v4;
    @FXML
    public CheckBox A;
    @FXML
    public CheckBox B;
    @FXML
    public CheckBox C;
    @FXML
    public CheckBox D;


    @FXML
    public void initialize(){
        combo.getItems().add("All");
        combo.getItems().add("Music");
        combo.getItems().add("Art");
        combo.getItems().add("Geography");
        combo.getItems().add("History");
        combo.getItems().add("Biology");

    }
    @FXML
    public void onBack() throws IOException {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminMainFrame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("QuizzApp!");
        stage.setScene(scene);
        stage.show();
    }
    public void onConfirm() throws IOException{
        int answer = getCorrectAns();
        int type = getDropSelected();

        try {
            String query = "INSERT INTO questions(question,ans_1,ans_2,ans_3,ans_4, correct_ans,q_type) VALUES(?, ?, ?, ?, ?,?,?)";
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(query);
            pst.setString(1, intrebare.getText());
            pst.setString(2, v1.getText());
            pst.setString(3, v2.getText());
            pst.setString(4, v3.getText());
            pst.setString(5, v4.getText());
            pst.setInt(6, answer);
            pst.setInt(7, type);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Question registration successfully");
        } catch (SQLException y)
        {
            //System.out.println("Intrebare deja existenta");
            System.out.println(y.getMessage());
        }
        clearfields();
    }
    public void clearfields(){
        intrebare.setText(null);
        v1.setText(null);
        v2.setText(null);
        v3.setText(null);
        v4.setText(null);
        A.setSelected(false);
        B.setSelected(false);
        C.setSelected(false);
        D.setSelected(false);
        A.setSelected(false);
        B.setSelected(false);
        C.setSelected(false);
        D.setSelected(false);
    }
    public int getCorrectAns(){
        if(A.isSelected())
            return 1;
        if(B.isSelected())
            return 2;
        if(C.isSelected())
            return 3;
        if(D.isSelected())
            return 4;
        return 0;
    }
    public int getDropSelected(){
        if(combo.getValue() == "All")
            return 0;
        if(combo.getValue() == "Music")
            return 1;
        if(combo.getValue() == "Art")
            return 2;
        if(combo.getValue() == "Geography")
            return 3;
        if(combo.getValue() == "History")
            return 4;
        if(combo.getValue() == "Biology")
            return 5;
        return 6 ;
    }
}
