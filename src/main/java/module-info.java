module com.example.crudjava {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.crudjava to javafx.fxml;
    exports com.example.crudjava;

    opens crud.app to javafx.graphics, javafx.fxml;
    opens crud.controller to javafx.fxml;
    exports crud.app;

}