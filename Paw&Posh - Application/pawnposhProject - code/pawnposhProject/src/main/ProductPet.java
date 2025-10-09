package main;
import helper.Connect;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.PetProduct;


public class ProductPet {
	Connect connect = Connect.getInstance();
    private MainPetShop app;
    private VBox layout; // Main layout
    private GridPane grid; // Product grid
    private Cart cart; // Shopping cart
    private Label totalPriceLabel;
    private TextField addressField;
    private TextField recipientField;
    private ComboBox<String> paymentMethodComboBox;
    private Button confirmOrderButton; // Declare confirmOrderButton here

    public ProductPet(MainPetShop app) {
        this.app = app;
        this.cart = new Cart(); // Initialize the cart
        setupUI();
    }

    private void setupUI() {
        layout = new VBox(20);
        layout.setPadding(new Insets(25));
        layout.setStyle("-fx-background-color: #FDB0C0;");

        // Title
        Label titleLabel = new Label("Pet Products");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2B2B2B;");
        layout.getChildren().add(titleLabel);

        // Separator
        Separator separator = new Separator();
        layout.getChildren().add(separator);

        // Product grid inside a scrollable area
        ScrollPane scrollPane = new ScrollPane();
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(15);
        grid.setHgap(10);
        scrollPane.setContent(grid);
        scrollPane.setFitToWidth(true);
        layout.getChildren().add(scrollPane);

        // Add product rows
        addProductRow("Dog Food", "Food", 20000);
        addProductRow("Cat Food", "Food", 15000);
        addProductRow("Dog Treats", "Treats", 5000);
        addProductRow("Cat Treats", "Treats", 4000);
        addProductRow("Dog Toys", "Toys", 10000);
        addProductRow("Cat Toys", "Toys", 8000);

        // Total Price Label
        totalPriceLabel = new Label("Total: IDR 0");
        totalPriceLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        layout.getChildren().add(totalPriceLabel);

        // Address and recipient fields
        recipientField = new TextField();
        recipientField.setPromptText("Recipient Name");
        addressField = new TextField();
        addressField.setPromptText("Delivery Address");
        layout.getChildren().addAll(recipientField, addressField);

        // Payment method combo box with only Bank Transfer and COD
        paymentMethodComboBox = new ComboBox<>();
        paymentMethodComboBox.getItems().addAll("Bank Transfer", "Cash");
        paymentMethodComboBox.setPromptText("Select Payment Method");
        layout.getChildren().add(paymentMethodComboBox);

        // View Cart Button
        Button viewCartButton = new Button("View Cart");
        viewCartButton.setOnAction(e -> showCart());
        layout.getChildren().add(viewCartButton);

        // Confirm Order Button
        confirmOrderButton = new Button("Confirm Order");
        confirmOrderButton.setOnAction(e -> confirmOrder());
        layout.getChildren().add(confirmOrderButton);

        // Back to Home Button
        Button backToHomeButton = new Button("Back to Home");
        backToHomeButton.setOnAction(e -> app.showMainMenu());
        layout.getChildren().add(backToHomeButton);
    }

    private void addProductRow(String name, String category, double price) {
        PetProduct product = new PetProduct(name,  price, 0);

        // Labels and buttons for product
        Label nameLabel = new Label(product.getName());
        Label priceLabel = new Label("IDR " + formatCurrency(product.getPrice()));
        Button addButton = new Button("Add to Cart");

        // Add to Cart action
        addButton.setOnAction(e -> {
            cart.addItem(new PetProduct(product.getName(), product.getPrice(), 1)); // Add one unit
            updateTotalPrice();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, product.getName() + " has been added to your cart.");
            alert.showAndWait();
        });

        // Add elements to the grid
        int rowIndex = grid.getRowCount(); // Get next available row
        grid.add(nameLabel, 0, rowIndex);
        grid.add(priceLabel, 1, rowIndex);
        grid.add(addButton, 2, rowIndex);
    }

    private void updateTotalPrice() {
        double totalAmount = 0;
        for (PetProduct item : Cart.getItems()) {
            totalAmount += item.getPrice() * item.getQuantity();
        }
        totalPriceLabel.setText("Total: IDR " + formatCurrency(totalAmount));
    }

    private void showCart() {
        VBox cartLayout = new VBox(10);
        cartLayout.setPadding(new Insets(20));

        for (PetProduct item : cart.getItems()) {
            HBox cartItemLayout = new HBox(10);
            cartItemLayout.setAlignment(Pos.CENTER_LEFT);

            Label nameLabel = new Label(item.getName() + " - Price: IDR " + formatCurrency(item.getPrice()) + " x " + item.getQuantity());
            nameLabel.setPrefWidth(300);

            TextField quantityField = new TextField(String.valueOf(item.getQuantity()));
            quantityField.setPrefWidth(50);

            Button updateButton = new Button("Update");
            updateButton.setOnAction(e -> {
                try {
                    int newQuantity = Integer.parseInt(quantityField.getText());
                    if (newQuantity <= 0) throw new NumberFormatException();
                    item.setQuantity(newQuantity);
                    updateTotalPrice();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Updated quantity for " + item.getName());
                    alert.showAndWait();
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a valid quantity.");
                    alert.showAndWait();
                }
            });

            Button removeButton = new Button("Remove");
            removeButton.setOnAction(e -> {
                cart.removeItem(item);
                cartLayout.getChildren().remove(cartItemLayout);
                updateTotalPrice();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, item.getName() + " removed from cart.");
                alert.showAndWait();
            });

            cartItemLayout.getChildren().addAll(nameLabel, quantityField, updateButton, removeButton);
            cartLayout.getChildren().add(cartItemLayout);
        }

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        });

        cartLayout.getChildren().add(closeButton);

        Stage cartStage = new Stage();
        cartStage.setScene(new Scene(cartLayout, 600, 400));
        cartStage.setTitle("Cart");
        cartStage.show();
    }

    private void confirmOrder() {
        String recipient = recipientField.getText();
        String address = addressField.getText();
        String paymentMethod = paymentMethodComboBox.getValue();

        if (recipient.isEmpty() || address.isEmpty() || paymentMethod == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill in all fields.");
            alert.showAndWait();
            return;
        }

        String orderConfirmation = "Name: " + recipient + "\n" +
                                   "Address: " + address + "\n" +
                                   "Order: " + cart.getItems().size() + " item(s)\n";

        if (paymentMethod.equals("Bank Transfer")) {
            orderConfirmation += "Please transfer the payment to:\n" +
                                 "Bank: BCA\n" +
                                 "Account No: 54128937\n" +
                                 "Account Name: Paw & Posh Pet Shop";
        }

        double price = 0;
        for (PetProduct item : Cart.getItems()) {
            price += item.getPrice() * item.getQuantity();
        }

        String query = "INSERT INTO orders (total_amount, payment_method, recipient_name, address, created_at) VALUES ('" + price + "', '" + paymentMethod + "', '" + recipient + "', '" + address + "', now())";
        connect.execUpdate(query);

//        String query = "INSERT INTO pets (  name, dob, gender, race)"
//				+ "VALUES ('" + petNameField.getText() +"', '" + selectedDate+ "', '"+ selectedGender+ "', '"+petRaceField.getText()+"');";
//        connect.execUpdate(query);
        

        Alert orderAlert = new Alert(Alert.AlertType.INFORMATION, orderConfirmation);
        orderAlert.showAndWait();

        // Close the current window (with "Back to Home" button)
        Stage stage = (Stage) confirmOrderButton.getScene().getWindow();
        stage.close();

        // Optionally, navigate back to the home screen
        app.showMainMenu();
    }

    private String formatCurrency(double amount) {
        return String.format("%,.0f", amount).replace(",", ".") + " IDR";
    }

    public VBox getLayout() {
        return layout;
    }

    // New getScene method to set the size to 600x600
    public Scene getScene() {
        return new Scene(layout, 600, 600); // Set scene size to 600x600
    }
}
