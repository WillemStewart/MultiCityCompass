module koester {
    requires javafx.controls;
    requires javafx.fxml;

    opens koester to javafx.fxml;
    exports koester;
}
