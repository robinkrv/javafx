package fr.afpa.javafx.components;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FormController {
    @FXML
    private Label entryTitle;
    @FXML
    private Label copyTitle;
    @FXML
    private TextField entryField;
    @FXML
    private TextField copyField;

    @FXML
    protected void onQuitterButtonClick() {
        Platform.exit();
    }

    @FXML
    protected void onEffacerButtonClick() {
        entryField.clear();
        copyField.clear();
    }

    @FXML
    protected void onRecopierButtonClick() {
        String text = entryField.getText();
        copyField.setText(text);
    }
    }