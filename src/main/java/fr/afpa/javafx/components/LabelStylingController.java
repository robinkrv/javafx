package fr.afpa.javafx.components;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class LabelStylingController {
    @FXML
    private ToggleGroup colorGroup;
    @FXML
    private ToggleGroup caseGroup;
    @FXML
    private RadioButton redRadio;
    @FXML
    private RadioButton greenRadio;
    @FXML
    private RadioButton blueRadio;
    @FXML
    private RadioButton uppercaseRadio;
    @FXML
    private RadioButton lowercaseRadio;
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
    @FXML
    private CheckBox backgroundCheckbox;
    @FXML
    private CheckBox charCheckbox;
    @FXML
    private CheckBox caseCheckbox;
    @FXML
    private Slider redSlider;
    @FXML
    private Slider greenSlider;
    @FXML
    private Slider blueSlider;

    @FXML
    public void initialize() {
        setupToggleGroups();
        setupBindings();
        setupListeners();
    }

    private void setupToggleGroups() {
        colorGroup = new ToggleGroup();
        redRadio.setToggleGroup(colorGroup);
        greenRadio.setToggleGroup(colorGroup);
        blueRadio.setToggleGroup(colorGroup);
        caseGroup = new ToggleGroup();
        uppercaseRadio.setToggleGroup(caseGroup);
        lowercaseRadio.setToggleGroup(caseGroup);
    }

    private void setupBindings() {
        labelParams.setDisable(true);
        backgroundParams.setDisable(true);
        charParams.setDisable(true);
        caseParams.setDisable(true);
        if (redSlider != null)
            redSlider.setValue(0);
        if (greenSlider != null)
            greenSlider.setValue(0);
        if (blueSlider != null)
            blueSlider.setValue(0);
    }

    private void setupListeners() {
        entryField.textProperty().addListener((obs, oldVal, newVal) -> {
            boolean active = !newVal.trim().isEmpty();
            labelParams.setDisable(!active);
            updateBottomTitledPanes(active);
            if (!active) {
                resetAllStylesAndCases();
            }
            updateDisplayText();
        });

        colorGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (backgroundCheckbox.isSelected()) {
                applyBackgroundColor();
            }
        });

        backgroundCheckbox.selectedProperty().addListener((obs, wasChecked, isNowChecked) -> {
            updateBottomTitledPanes(!entryField.getText().trim().isEmpty());
            if (isNowChecked) {
                applyBackgroundColor();
            } else {
                resetBackgroundColor();
            }
        });

        charCheckbox.selectedProperty().addListener((obs, wasChecked, isNowChecked) -> {
            updateBottomTitledPanes(!entryField.getText().trim().isEmpty());
            if (!isNowChecked)
                resetTextColor();
        });

        caseGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> updateDisplayText());
        caseCheckbox.selectedProperty().addListener((obs, wasChecked, isNowChecked) -> {
            updateBottomTitledPanes(!entryField.getText().trim().isEmpty());
            updateDisplayText();
        });

        if (redSlider != null)
            redSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (charCheckbox.isSelected()) {
                    applyCharColor();
                }
            });
        if (greenSlider != null)
            greenSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (charCheckbox.isSelected()) {
                    applyCharColor();
                }
            });
        if (blueSlider != null)
            blueSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (charCheckbox.isSelected()) {
                    applyCharColor();
                }
            });
    }

    private void updateBottomTitledPanes(boolean active) {
        backgroundParams.setDisable(!(active && backgroundCheckbox.isSelected()));
        charParams.setDisable(!(active && charCheckbox.isSelected()));
        caseParams.setDisable(!(active && caseCheckbox.isSelected()));
        if (redSlider != null)
            redSlider.setDisable(!active || !charCheckbox.isSelected());
        if (greenSlider != null)
            greenSlider.setDisable(!active || !charCheckbox.isSelected());
        if (blueSlider != null)
            blueSlider.setDisable(!active || !charCheckbox.isSelected());
    }

    private void applyBackgroundColor() {
        if (!backgroundCheckbox.isSelected())
            return;

        // TODO Okay pour solution avec du CSS
        // mais le traitement d'une chaîne de caractère peut être couteuse en temps
        // Il est possible de passer par une solution sans CSS afin de s'affranchir de ce traitement
        // Par exemple, pour changer la couleur de fond d'un label il est possible d'utiliser le code suivant :
        //            displayBox.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));

        String oldStyle = displayBox.getStyle().replaceAll("-fx-background-color:[^;]*;?", "");
        if (colorGroup.getSelectedToggle() == redRadio) {
            displayBox.setStyle(oldStyle + "-fx-background-color: red;");
        } else if (colorGroup.getSelectedToggle() == greenRadio) {
            displayBox.setStyle(oldStyle + "-fx-background-color: green;");
        } else if (colorGroup.getSelectedToggle() == blueRadio) {
            displayBox.setStyle(oldStyle + "-fx-background-color: blue;");
        } else {
            resetBackgroundColor();
        }
    }

    private void resetBackgroundColor() {
        displayBox.setStyle(displayBox.getStyle().replaceAll("-fx-background-color:[^;]*;?", ""));
    }

    // Si tu veux ajouter une couleur de texte (charParams), fais comme pour
    // ci-dessus
    private void resetTextColor() {
        displayBox.setStyle(displayBox.getStyle().replaceAll("-fx-text-fill:[^;]*;?", ""));
    }

    private void applyCharColor() {

        if (!charCheckbox.isSelected())
            return;

        // TODO Okay pour solution avec du CSS mais même remarque que précédemment
        String oldStyle = displayBox.getStyle().replaceAll("-fx-text-fill:[^;]*;?", "");

        // TODO comment ne pas multiplier les ternaires ?
        // 1 seul "if" possible ?
        int r = (redSlider != null) ? (int) redSlider.getValue() : 0;
        int g = (greenSlider != null) ? (int) greenSlider.getValue() : 0;
        int b = (blueSlider != null) ? (int) blueSlider.getValue() : 0;

        String rgb = String.format("-fx-text-fill: rgb(%d,%d,%d);", r, g, b);
        
        displayBox.setStyle(oldStyle + rgb);
    }

    private void updateDisplayText() {
        String txt = entryField.getText();
        if (caseCheckbox.isSelected()) {
            if (caseGroup.getSelectedToggle() == uppercaseRadio) {
                displayBox.setText(txt.toUpperCase());
            } else if (caseGroup.getSelectedToggle() == lowercaseRadio) {
                displayBox.setText(txt.toLowerCase());
            } else {
                displayBox.setText(txt);
            }
        } else {
            displayBox.setText(txt);
        }
    }

    private void resetAllStylesAndCases() {
        displayBox.setStyle("");
        displayBox.setText("");
        colorGroup.selectToggle(null);
        caseGroup.selectToggle(null);
        backgroundCheckbox.setSelected(false);
        charCheckbox.setSelected(false);
        caseCheckbox.setSelected(false);
        if (redSlider != null)
            redSlider.setValue(0);
        if (greenSlider != null)
            greenSlider.setValue(0);
        if (blueSlider != null)
            blueSlider.setValue(0);
    }
}
