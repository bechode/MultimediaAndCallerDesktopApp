module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.media;
    requires java.desktop;

    opens com.multimedia.reproductor to javafx.fxml;
    exports com.multimedia.chatsocket;
    exports com.multimedia.reproductor;
    exports com.multimedia.dto;
    opens com.multimedia.chatsocket to javafx.fxml;
}