module com.example.text4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.text4 to javafx.fxml;
    exports com.example.text4;
}