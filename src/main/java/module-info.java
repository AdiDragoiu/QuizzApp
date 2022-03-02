module com.example.proiectfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.proiectfinal to javafx.fxml;
    exports com.example.proiectfinal;
}