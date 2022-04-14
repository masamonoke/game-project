module com.vsu.gameproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.vsu.gameproject to javafx.fxml;
    exports com.vsu.gameproject;
    requires lombok;
}