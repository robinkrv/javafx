package fr.afpa.javafx.components;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private TextField entryField;
    @FXML
    private Label displayBox;
    @FXML
    private TitledPane labelParams;
    @FXML
    private TitledPane backgroundParams;
    @FXML
    private TitledPane charParams;
    @FXML
    private TitledPane caseParams;
    @FXML private CheckBox backgroundCheckbox;
    @FXML private CheckBox charCheckbox;
    @FXML private CheckBox caseCheckbox;

    @FXML
    public void initialize() {
        colorGroup = new ToggleGroup();
        redRadio.setToggleGroup(colorGroup);
        greenRadio.setToggleGroup(colorGroup);
        blueRadio.setToggleGroup(colorGroup);
        caseGroup = new ToggleGroup();
        uppercaseRadio.setToggleGroup(caseGroup);
        lowercaseRadio.setToggleGroup(caseGroup);
        displayBox.textProperty().bind(entryField.textProperty()); //textProperty est une propriété native des éléments
        labelParams.setDisable(true);
        backgroundParams.setDisable(true);
        charParams.setDisable(true);
        caseParams.setDisable(true);
        entryField.textProperty().addListener((obs, oldVal, newVal) -> {
            boolean active = !newVal.trim().isEmpty(); // active si NON vide, grace à trim qui supprime les espaces
            labelParams.setDisable(!active);
            updateBottomTitledPanes(active);
        });

        backgroundCheckbox.selectedProperty().addListener((obs, ov, nv) -> updateBottomTitledPanes(!entryField.getText().trim().isEmpty()));
        charCheckbox.selectedProperty().addListener((obs, ov, nv) -> updateBottomTitledPanes(!entryField.getText().trim().isEmpty()));
        caseCheckbox.selectedProperty().addListener((obs, ov, nv) -> updateBottomTitledPanes(!entryField.getText().trim().isEmpty()));
    }

    private void updateBottomTitledPanes(boolean active) {
        backgroundParams.setDisable(!(active && backgroundCheckbox.isSelected()));
        charParams.setDisable(!(active && charCheckbox.isSelected()));
        caseParams.setDisable(!(active && caseCheckbox.isSelected()));
    }
}

