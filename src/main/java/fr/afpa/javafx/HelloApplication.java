package fr.afpa.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    // TODO ajouter "transitive" après le "requires" "requires javafx.controls;" dans le "module-info.java"
    // plus d'informations sur les "module-info" ici : https://stackoverflow.com/questions/46502453/whats-the-difference-between-requires-and-requires-transitive-statements-in-jav
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("additioner-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("JavaFXApplication");
        stage.setScene(scene);
        stage.show();
        stage.setWidth(600);
        stage.setHeight(250);
    }

    public static void main(String[] args) {
        launch();
    }
}