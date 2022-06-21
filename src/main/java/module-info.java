module com.vsu.gameproject {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.vsu to javafx.fxml;
    exports com.vsu;
    requires lombok;
    requires log4j;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    opens com.vsu.actor.effect to com.fasterxml.jackson.databind;
}