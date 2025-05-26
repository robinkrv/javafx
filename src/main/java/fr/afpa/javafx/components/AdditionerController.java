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

    @FXML
    public void initialize() {
        for (int i = 1; i <=9; i++){
            Button btn = new Button(Integer.toString(i));
            btn.prefWidth(5);
            btn.prefHeight(5);
            int chiffre = i;
            btn.setOnAction(event -> ajouterChiffre(chiffre));
            numbersZone.getChildren().add(btn);
        }
    }

        private void ajouterChiffre(int chiffre) {
            String displayedNumber = additionArea.getText();
            if (displayedNumber.isEmpty()) {
                additionArea.setText(Integer.toString(chiffre));
            }
            else {
                additionArea.setText(displayedNumber + " + " + chiffre);
            }
        }

    @FXML
    protected void onViderButtonClick() {
        additionArea.clear();
    }

    @FXML
    protected void onCalculerButtonClick() {
        String content = additionArea.getText();
        if (content.isEmpty()) {
            return;
        }
        String[] parts = content.split("\\s*\\+\\s*");
        int somme = 0;
        for (String part : parts) {
            try {
                somme += (Integer.parseInt(part.trim()));
            } catch (NumberFormatException ignore) {}
        }
        additionArea.setText(content + " = " + somme);
    }
    }

