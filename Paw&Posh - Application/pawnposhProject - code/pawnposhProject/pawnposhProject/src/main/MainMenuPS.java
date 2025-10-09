package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainMenuPS {

    private MainPetShop app;
    private VBox layout;

    public MainMenuPS(MainPetShop primaryStage) {
        this.app = primaryStage;
        layout = new VBox();
        setupUI();
    }

    private void setupUI() {
        // Welcome Label
        Label welcomeLabel = new Label("Welcome to Paw & Posh Pet Shop!");
        welcomeLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: #4A4A4A;");
        welcomeLabel.setPadding(new Insets(20, 0, 30, 0));
        
        // Button functionalities
        Button groomingButton = createStyledButton("Grooming");
        groomingButton.setOnAction(e -> goToGrooming());

        Button productButton = createStyledButton("Products");
        productButton.setOnAction(e -> goToProductPage());

//        Button addPetButton = createStyledButton("Add Pet");
//        addPetButton.setOnAction(e -> goToAddPetPage());

        // Layout for buttons
        VBox buttonLayout = new VBox(20);  
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setPadding(new Insets(70));
        buttonLayout.getChildren().addAll(groomingButton, productButton);

        // Background and Layout
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setSpacing(40);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, #FDB0C0, #FFD1E0);");

        layout.getChildren().addAll(welcomeLabel, buttonLayout);
    }

    private Button createStyledButton(String text) {
        // Styling button
        Button button = new Button(text);
        button.setPrefSize(180, 50);
        button.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white; "
                      + "-fx-background-color: #FF6F91; -fx-background-radius: 10; "
                      + "-fx-border-color: #E65A8C; -fx-border-width: 2px;");

        return button;
    }

    private void goToProductPage() {
        // Create the ProductPet page using the correct constructor
        ProductPet productPage = new ProductPet(app); // Ensure app is passed correctly
        Scene scene = new Scene(productPage.getLayout(), 1280, 720); // Match dimensions as needed
        Stage stage = (Stage) layout.getScene().getWindow(); // Get current stage
        stage.setScene(scene); // Set the new scene
    }

    private void goToAddPetPage() {
        AddPet addPetPage = new AddPet(app);  // Navigate to AddPet page
        Scene scene = new Scene(addPetPage, 1280, 720);
        Stage stage = (Stage) layout.getScene().getWindow();
        stage.setScene(scene);
    }

    private void goToGrooming() {
        PetCheck petCheckPage = new PetCheck(app);  // Navigate to Grooming page
        Scene scene = new Scene(petCheckPage, 1280, 720);
        Stage stage = (Stage) layout.getScene().getWindow();
        stage.setScene(scene);
    }

    public VBox getLayout() {
        return layout;
    }
}
