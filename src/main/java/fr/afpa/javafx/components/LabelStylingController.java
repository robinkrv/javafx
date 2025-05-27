package fr.afpa.javafx.components;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class LabelStylingController {
    @FXML
    private ToggleGroup colorGroup;
    @FXML
    private ToggleGroup caseGroup;
    @FXML private RadioButton redRadio;
    @FXML private RadioButton greenRadio;
    @FXML private RadioButton blueRadio;
    @FXML private RadioButton uppercaseRadio;
    @FXML private RadioButton lowercaseRadio;

    @FXML
    public void initialize() {
        colorGroup = new ToggleGroup();
        redRadio.setToggleGroup(colorGroup);
        greenRadio.setToggleGroup(colorGroup);
        blueRadio.setToggleGroup(colorGroup);
        caseGroup = new ToggleGroup();
        uppercaseRadio.setToggleGroup(caseGroup);
        lowercaseRadio.setToggleGroup(caseGroup);
    }
}
