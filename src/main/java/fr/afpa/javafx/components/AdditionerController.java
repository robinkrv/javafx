package fr.afpa.javafx.components;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AdditionerController {

    @FXML
    private TextArea additionArea;

    @FXML
    private HBox numbersZone;

    private int currentSum = 0;
    private StringBuilder displayAddition = new StringBuilder();

    @FXML
    public void initialize() {
        for (int i = 1; i <= 9; i++) {
            Button btn = new Button(Integer.toString(i));
            btn.prefWidth(5);
            btn.prefHeight(5);
            int chiffre = i;
            btn.setOnAction(event -> ajouterChiffre(chiffre));
            numbersZone.getChildren().add(btn);
        }
    }

    private void ajouterChiffre(int chiffre) {
        currentSum += chiffre;
        if (displayAddition.length() == 0) {
            displayAddition.append(chiffre);
        } else {
            displayAddition.append(" + ").append(chiffre);
        }
        updateAffichage();
    }

    private void updateAffichage() {
        String affichage = displayAddition.toString();
        if (!affichage.contains("+")) {
            additionArea.setText(affichage);
        } else {
            additionArea.setText(affichage + " = " + currentSum);
        }
    }

    @FXML
    protected void onViderButtonClick() {
        currentSum = 0;
        displayAddition.setLength(0);
        additionArea.clear();
    }

    @FXML
    protected void onCalculerButtonClick() {
        if (displayAddition.length() == 0) {
            return;
        }
        updateAffichage();
    }
}

