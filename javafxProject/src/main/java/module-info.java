module com.example.javafxproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.javafxproject to javafx.fxml;
    exports com.example.javafxproject;
}