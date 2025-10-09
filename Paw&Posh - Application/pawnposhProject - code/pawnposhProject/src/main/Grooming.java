package main;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.HashMap;

import helper.Connect;
import helper.PackageMetadata;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Grooming {

	Connect connect = Connect.getInstance();
    private MainPetShop app;
    private BorderPane layout;
    private String petName;
    private ToggleGroup packageGroup;
    private ToggleGroup serviceGroup;
    private VBox packageContainer;
    PackageMetadata[] packageMetadata = new PackageMetadata[3];
    
    // Constructor
    public Grooming(MainPetShop app, String petName) {
        this.app = app;
        this.petName = petName;
        layout = new BorderPane();
        layout.setPadding(new Insets(10));
        layout.setStyle("-fx-background-color: #FDB0C0;"); // Pink background
        setupUI();
    }

    private void setupUI() {
        Label groomingLabel = new Label("Choose Grooming Package for " + petName);
        groomingLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Pet Owner Name
        Label nameLabel = new Label("Pet Owner Name:");
        TextField ownerNameField = new TextField();
        ownerNameField.setPromptText("Enter Pet Owner's Name");

        // Pet Owner Phone Number
        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter Pet Owner's Phone Number");

        // Set up the ToggleGroup for grooming package selection
        packageGroup = new ToggleGroup();

        // Package container for grooming options
        packageContainer = new VBox(20);
        packageContainer.setPadding(new Insets(10));

        // Add grooming packages with 'Click More' button
        packageContainer.getChildren().addAll(
            createPackageOption(0, "Tiny Tails Package", 
                "Services: Bath & blow-dry, nail trimming, ear cleaning.\nRequirements: Suitable for small pets under 10 kg.\nPrice: IDR 150,000", "150000"),
            createPackageOption(1, "Fluffy Friends Package", 
                "Services: Bath & blow-dry, nail trimming, ear cleaning, light fur trimming.\nRequirements: For medium-sized pets (10-20 kg).\nPrice: IDR 250,000", "250000"),
            createPackageOption(2, "Majestic Paws Package", 
                "Services: Bath & blow-dry, nail trimming, ear cleaning, full grooming (fur trimming and styling).\nRequirements: For large pets over 20 kg.\nPrice: IDR 350,000", "350000")
        );

        // Service Type (Home or In-store)
        Label serviceLabel = new Label("Select Grooming Type:");
        serviceGroup = new ToggleGroup();
        RadioButton homeServiceRadio = new RadioButton("Home Service");
        RadioButton inStoreServiceRadio = new RadioButton("In-Store Service");
        homeServiceRadio.setToggleGroup(serviceGroup);
        inStoreServiceRadio.setToggleGroup(serviceGroup);

        VBox serviceContainer = new VBox(10, serviceLabel, homeServiceRadio, inStoreServiceRadio);
        serviceContainer.setPadding(new Insets(10));
        serviceContainer.setAlignment(Pos.CENTER);

        // TextField for home service address input
        TextField addressField = new TextField();
        addressField.setPromptText("Enter your address...");
        addressField.setDisable(true);  // Initially disable until Home Service is selected

        // Show store location when In-Store is selected
        Label storeLocationLabel = new Label("Store Location: Jl Rawa Belong 24B, Kebon Jeruk, Jakarta Barat.");
        storeLocationLabel.setStyle("-fx-text-fill: #F17794;");
        storeLocationLabel.setVisible(false);

        serviceGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle == homeServiceRadio) {
                addressField.setDisable(false); // Enable address field for home service
                storeLocationLabel.setVisible(false); // Hide store location
            } else if (newToggle == inStoreServiceRadio) {
                addressField.setDisable(true); // Disable address field for in-store service
                storeLocationLabel.setVisible(true); // Show store location
            }
        });

        // Payment Method
        Label paymentLabel = new Label("Select Payment Method:");
        ToggleGroup paymentGroup = new ToggleGroup();
        RadioButton transferRadio = new RadioButton("Bank Transfer");
        RadioButton cashRadio = new RadioButton("Cash");
        transferRadio.setToggleGroup(paymentGroup);
        cashRadio.setToggleGroup(paymentGroup);

        VBox paymentContainer = new VBox(10, paymentLabel, transferRadio, cashRadio);
        paymentContainer.setPadding(new Insets(10));
        paymentContainer.setAlignment(Pos.CENTER);
        paymentContainer.setStyle("-fx-border-color: #ccc; -fx-border-width: 1px;");

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            String selectedPackage = packageGroup.getSelectedToggle() != null
                ? ((RadioButton) packageGroup.getSelectedToggle()).getText()
                : "No package selected";
            String selectedPayment = paymentGroup.getSelectedToggle() != null
                ? ((RadioButton) paymentGroup.getSelectedToggle()).getText()
                : "No payment method selected";
            String serviceType = homeServiceRadio.isSelected() ? "Home Service" : "In-Store Service";
            String address = homeServiceRadio.isSelected() ? addressField.getText() : storeLocationLabel.getText();
            String ownerName = ownerNameField.getText();
            String phoneNumber = phoneField.getText();

            String confirmationText = "Pet Owner: " + ownerName + "\nPhone: " + phoneNumber + "\n" +
                                      "Grooming Package: " + selectedPackage + "\n" +
                                      "Payment Method: " + selectedPayment + "\n" +
                                      "Service Type: " + serviceType + "\n" +
                                      "Address: " + address + "\nThank you for using our service!";

            // Include bank transfer details
            if (selectedPayment.equals("Bank Transfer")) {
                confirmationText += "\n\nPlease transfer the payment to:\n" +
                                    "Bank: BCA\nAccount No: 54128937\nAccount Name: Paw & Posh Pet Shop";
            }
            

            int indexedPackage = 0;
            if (selectedPackage  == "Fluffy Friends Package") {
            	indexedPackage = 1;
            } else if (selectedPackage == "Majestic Paws Package") {
            	indexedPackage = 2;
            } else {
            	indexedPackage = 0;
            }

            String query = "INSERT INTO orders (total_amount, payment_method, recipient_name, address, created_at) VALUES ('" + this.packageMetadata[indexedPackage].price + "', '" + selectedPayment + "', '" + ownerName + "', '" + address + "', now())";
            connect.execUpdate(query);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Payment Confirmation");
            alert.setHeaderText("Payment Successful");
            alert.setContentText(confirmationText);
            alert.showAndWait();

            app.showMainMenu();
        });

        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> app.showMainMenu());

        HBox buttonContainer = new HBox(10, submitButton, backButton);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPadding(new Insets(10));

        // grooming Layout
        VBox mainContainer = new VBox(20, groomingLabel, ownerNameField, phoneField, packageContainer, serviceContainer, addressField, storeLocationLabel, paymentContainer, buttonContainer);
        mainContainer.setAlignment(Pos.CENTER);

        // Wrap the main container in a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(mainContainer);
        scrollPane.setFitToWidth(true);  // Ensure it fits the width of the window
        scrollPane.setStyle("-fx-background-color: #FDB0C0;"); // Ensure pink background on scroll pane as well

        layout.setCenter(scrollPane);
    }

    private VBox createPackageOption(int index, String name, String description, String price) {
        VBox packageOptionContainer = new VBox(10);
        packageOptionContainer.setStyle("-fx-background-color: #fff; -fx-border-color: #F17794; -fx-border-radius: 5px; -fx-padding: 10px;");
        packageOptionContainer.setAlignment(Pos.CENTER);

        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #F17794;");

        // Initialize description with brief info
        Label descriptionLabel = new Label(description.split("\n")[0]);
        descriptionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");

        // Store full description with detailed information
        String fullDescription = description;
        
        this.packageMetadata[index].name = name;
        this.packageMetadata[index].price = price;

        // Create a toggle for the button
        Button moreButton = new Button("Click More");
        moreButton.setStyle("-fx-background-color: #F17794; -fx-text-fill: white; -fx-font-size: 12px;");

        // Add an action to toggle between "showing more" and "showing less"
        moreButton.setOnAction(e -> {
            if (descriptionLabel.getText().equals(description.split("\n")[0])) {
                descriptionLabel.setText(fullDescription); // Show full description
                moreButton.setText("Show Less"); // Change button text
            } else {
                descriptionLabel.setText(description.split("\n")[0]); // Show brief description
                moreButton.setText("Click More"); // Reset button text
            }
        });

        packageOptionContainer.getChildren().addAll(nameLabel, descriptionLabel, moreButton);

        // Add the RadioButton to the package selection group
        RadioButton packageRadioButton = new RadioButton(name);
        packageRadioButton.setToggleGroup(packageGroup);
        packageOptionContainer.getChildren().add(0, packageRadioButton); // Add radio button at the top

        return packageOptionContainer;
    }

    public BorderPane getLayout() {
        return layout;
    }
}
