module com.mycompany.test2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.test2 to javafx.fxml;
    exports com.mycompany.test2;
}
