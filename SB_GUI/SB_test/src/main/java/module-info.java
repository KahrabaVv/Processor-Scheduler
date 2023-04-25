module com.nada.sb_test {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.nada.sb_test to javafx.fxml;
    exports com.nada.sb_test;
}