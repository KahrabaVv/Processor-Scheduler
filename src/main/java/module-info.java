module proccesor {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.asu.scheduler to javafx.fxml;
    exports com.asu.scheduler;

    opens com.asu.scheduler.model.processor to javafx.fxml;
    exports com.asu.scheduler.model.processor;

    opens com.asu.scheduler.model.controller to javafx.fxml;
    exports com.asu.scheduler.model.controller;

    opens com.asu.scheduler.model.process to javafx.fxml;
    exports com.asu.scheduler.model.process;
}