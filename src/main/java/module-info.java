module com.example.jabnation {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;


    opens JabNation to javafx.fxml;
    exports JabNation;
    exports JabNation.Boxer;
    opens JabNation.Boxer to javafx.fxml;
}