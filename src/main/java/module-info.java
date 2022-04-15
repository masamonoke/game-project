module com.vsu.gameproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.vsu to javafx.fxml;
    exports com.vsu;
    requires lombok;
    requires log4j;
}