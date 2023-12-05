module koester.lab5 {
    requires javafx.controls;
    requires javafx.fxml;

    opens koester.lab5 to javafx.fxml;
    exports koester.lab5;
}
