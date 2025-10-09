package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PetCheck extends VBox {

    private MainPetShop app;

    public PetCheck(MainPetShop app) {
        this.app = app;
        setupUI();
    }

    private void setupUI() {
        setPadding(new Insets(20));
        setSpacing(10);
        setStyle("-fx-background-color: #FDB0C0;");

        Label messageLabel = new Label("Have you added your pet?");
        messageLabel.setStyle("-fx-font-size: 16px;");

        Button addPetButton = new Button("Add Pet");
        //diarahin ke add pet page
        addPetButton.setOnAction(e -> app.showAddPetPage());  
        Button continueGroomingButton = new Button("Continue to Grooming");
        continueGroomingButton.setOnAction(e -> app.showGroomingPage("YourPetName")); // Replace "YourPetName" with the actual pet name logic


        getChildren().addAll(messageLabel, addPetButton, continueGroomingButton);
        setAlignment(Pos.CENTER);
    }
}