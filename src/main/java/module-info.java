module fr.afpa.javafx {
    requires transitive javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens fr.afpa.javafx to javafx.fxml;
    exports fr.afpa.javafx;
    exports fr.afpa.javafx.components;
    opens fr.afpa.javafx.components to javafx.fxml;
}