package main;

import java.util.Optional;

import helper.Connect;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class Register extends VBox {
	
	Connect connect = Connect.getInstance();
	TextField usernameField = new TextField();
	TextField phoneField = new TextField();
	TextArea addressField = new TextArea();
	PasswordField passwordField = new PasswordField();
	TextField emailField = new TextField();
	ToggleGroup genderGroup = new ToggleGroup();
    RadioButton maleButton = new RadioButton("Male");
    RadioButton femaleButton = new RadioButton("Female");
    RadioButton otherButton = new RadioButton("Rather not say");
	
    public Register(MainPetShop primaryStage) {
        // set VBOX layout
        setPadding(new Insets(20));
        setSpacing(15);
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #FDB0C0;");

        // title regist page
        Label title = new Label("Create Your Account");
        title.setStyle("-fx-font-size: 24px; -fx-text-fill: #4A4A4A; -fx-font-weight: bold;");

        // Username  
       
        usernameField.setPromptText("Enter username (5-50 characters)");
        usernameField.setMaxWidth(300);

        // Phone  
       
        phoneField.setPromptText("Enter phone number");
        phoneField.setMaxWidth(300);

        // Address 
        
        addressField.setPromptText("Enter address (max 100 characters)");
        addressField.setPrefRowCount(3);
        addressField.setMaxWidth(300);

        // Password  
        
        passwordField.setPromptText("Enter password (alphanumeric)");
        passwordField.setMaxWidth(300);

        // Email 
        
        emailField.setPromptText("Enter email (e.g., name@email.com)");
        emailField.setMaxWidth(300);

        // Gender  
        
        
        Label usernameLabel = new Label("Username");
        usernameLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #4A4A4A;");
        
        Label phoneLabel = new Label("Phone Number");
        phoneLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #4A4A4A;");
        
        Label addressLabel = new Label("Address");
        addressLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #4A4A4A;");
        
        Label passwordLabel = new Label("Password");
        passwordLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #4A4A4A;");
        
        Label emailLabel = new Label("Email");
        emailLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #4A4A4A;");
        
        Label genderLabel = new Label("Gender:");
        genderLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #4A4A4A;");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(15);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));
        
     
       
        // VBox utama
      
        maleButton.setToggleGroup(genderGroup);
        femaleButton.setToggleGroup(genderGroup);
        otherButton.setToggleGroup(genderGroup);

        HBox genderBox = new HBox(10, maleButton, femaleButton, otherButton);
        genderBox.setAlignment(Pos.CENTER);
        
        VBox mainBox = new VBox(15, usernameLabel, usernameField, phoneLabel, phoneField, addressLabel, addressField, emailLabel, emailField, passwordLabel, passwordField, genderLabel, genderBox);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setPadding(new Insets(20));
        mainBox.setPrefWidth(400);


        // Register button
        Button registerButton = new Button("Register");
        registerButton.setStyle(
            "-fx-background-color: #FF6F91; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 14px; " +
            "-fx-padding: 10 20; " +
            "-fx-background-radius: 5;"
        );
        registerButton.setOnAction(event -> {
        	boolean isValid = true;
    		
    		//CHECK IF EMAIL IS EMPTY
    		if (usernameField.getText().isBlank() && phoneField.getText().isBlank() && addressField.getText().isBlank() && 
    				passwordField.getText().isBlank() && emailField.getText().isBlank() && genderGroup.getSelectedToggle() == null) {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Invalid Login");
    			alert.setContentText("please fill out all fields");
    			Optional<ButtonType> result = alert.showAndWait();
    			if (result.get() == ButtonType.OK) {
    				event.consume();
    				return;
    			}
    		}
    		
    		
    		//CHECK IF EMAIL ENDS WITH @gmail.com
    		else if (!(emailField.getText().endsWith("@gmail.com"))) {
    			isValid = false;
    			Alert message1 = new Alert(AlertType.ERROR);
    			message1.setTitle("Register Failed");
    			message1.setContentText("Email Must end with @gmail.com");
    			Optional<ButtonType> error1 = message1.showAndWait();
    			if (error1.get() == ButtonType.OK) {
    				event.consume();
    				return;
    			}
    		}
    		
    		
    		//CHECK PASSWORD IS ALPHANUMERIC
    		boolean letter = false;
    		boolean digit = false;
    		boolean symbol = false;
    		
    		for (char ch : passwordField.getText().toCharArray()) {
    			
    			if (Character.isLetter(ch)) {
    				letter = true;
    			} else if (Character.isDigit(ch)) {
    				digit = true;
    			} else if (ch == ' ') {
    				letter = true;
    			} else {
    				symbol = true;
    			}
    		}
    		if (!(letter == true && digit == true && symbol == false)) {
    			isValid = false;
    			Alert message1 = new Alert(AlertType.ERROR);
    			message1.setTitle("Register Failed");
    			message1.setContentText("Password must be alphanumberic");
    			Optional<ButtonType> error1 = message1.showAndWait();
    			if (error1.get() == ButtonType.OK) {
    				event.consume();
    				return;
    			}
    			
    		}
    		
    	
    		
    		
    		//CHECK IF ADDRESS CONTAINS 100 CHARACTER
    		if (!(addressField.getText().length() <= 100)) {
    			isValid = false;
    			Alert message = new Alert(AlertType.ERROR);
    			message.setTitle("Register Failed");
    			message.setContentText("Please select date of birth");
    			Optional<ButtonType> error = message.showAndWait();
    			if (error.get() == ButtonType.OK) {
    				event.consume();
    				return;
    			}
    		}
    		
    		//CHECK IF USERNAME CONTAINS MIN 5 CHARACTER MAX 50 CHARACTER
    		if (!(usernameField.getText().length() >= 5 && usernameField.getText().length() <= 50)) {
    			isValid = false;
    			Alert message = new Alert(AlertType.ERROR);
    			message.setTitle("Register Failed");
    			message.setContentText("Please select date of birth");
    			Optional<ButtonType> error = message.showAndWait();
    			if (error.get() == ButtonType.OK) {
    				event.consume();
    				return;
    			}
    		}
    		
    		//CHECK IF GENDER IS SELECTED
    		if (genderGroup.getSelectedToggle() == null) {
    			isValid = false;
    			Alert message = new Alert(AlertType.ERROR);
    			message.setTitle("Register Failed");
    			message.setContentText("Select your gender");
    			Optional<ButtonType> error = message.showAndWait();
    			if (error.get() == ButtonType.OK) {
    				event.consume();
    				return;
    			}
    		}
    		
    	
    			
    		if (isValid) {
    		String selectedGender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
    		String query = "INSERT INTO users (username, password, phone, address, email, gender)"
    						+ "VALUES ('" + usernameField.getText() +"', '" +passwordField.getText()+ "', '"+ phoneField.getText()+"', '" +addressField.getText()+ "', '"+emailField.getText()+"', '"+ selectedGender +"');";
    		connect.execUpdate(query);
    		 	
    		 	primaryStage.showLoginPage();
    		}
    });

        
//            String username = usernameField.getText();
//            String phone = phoneField.getText();
//            String address = addressField.getText();
//            String password = passwordField.getText();
//            String email = emailField.getText();
//            RadioButton selectedGender = (RadioButton) genderGroup.getSelectedToggle();
//
//            if (isValidInput(username, phone, address, password, email, selectedGender)) {
//                System.out.println("Registration successful!");
//                primaryStage.showMainMenu(); // Navigate to the main menu
//            } else {
//                showAlert("Invalid Input", "Please ensure all fields are filled correctly.");
//            }
//        });

         
        getChildren().addAll(title, mainBox,registerButton);
    }

    //validasi hasil inputnya
    private boolean isValidInput(String username, String phone, String address, String password, String email, RadioButton selectedGender) {
        return username.length() >= 5 && username.length() <= 50 &&
               phone.length() > 0 &&
               address.length() <= 100 &&
               password.matches("^[a-zA-Z0-9]+$") &&
               email.matches("^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,}$") &&
               selectedGender != null;
    }

    // pop up msg
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

//	@Override
//	public void handle(ActionEvent event) {
//		// TODO Auto-generated method stub
//		
//		boolean isValid = true;
//		
//		//CHECK IF EMAIL IS EMPTY
//		if (usernameField.getText().isBlank() && phoneField.getText().isBlank() && addressField.getText().isBlank() && 
//				passwordField.getText().isBlank() && emailField.getText().isBlank() && genderGroup.getSelectedToggle() == null) {
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setTitle("Invalid Login");
//			alert.setContentText("please fill out all fields");
//			Optional<ButtonType> result = alert.showAndWait();
//			if (result.get() == ButtonType.OK) {
//				event.consume();
//				return;
//			}
//		}
//		
//		
//		//CHECK IF EMAIL ENDS WITH @gomail.com
//		else if (!(emailField.getText().endsWith("@gmail.com"))) {
//			isValid = false;
//			Alert message1 = new Alert(AlertType.ERROR);
//			message1.setTitle("Register Failed");
//			message1.setContentText("Email Must end with @gmail.com");
//			Optional<ButtonType> error1 = message1.showAndWait();
//			if (error1.get() == ButtonType.OK) {
//				event.consume();
//				return;
//			}
//		}
//		
//		
//		//CHECK PASSWORD IS ALPHANUMERIC
//		boolean letter = false;
//		boolean digit = false;
//		boolean symbol = false;
//		
//		for (char ch : passwordField.getText().toCharArray()) {
//			
//			if (Character.isLetter(ch)) {
//				letter = true;
//			} else if (Character.isDigit(ch)) {
//				digit = true;
//			} else if (ch == ' ') {
//				letter = true;
//			} else {
//				symbol = true;
//			}
//		}
//		if (!(letter == true && digit == true && symbol == false)) {
//			isValid = false;
//			Alert message1 = new Alert(AlertType.ERROR);
//			message1.setTitle("Register Failed");
//			message1.setContentText("Password must be alphanumberic");
//			Optional<ButtonType> error1 = message1.showAndWait();
//			if (error1.get() == ButtonType.OK) {
//				event.consume();
//				return;
//			}
//			
//		}
//		
//	
//		
//		
//		//CHECK IF ADDRESS CONTAINS 100 CHARACTER
//		if (!(addressField.getText().length() <= 100)) {
//			isValid = false;
//			Alert message = new Alert(AlertType.ERROR);
//			message.setTitle("Register Failed");
//			message.setContentText("Please select date of birth");
//			Optional<ButtonType> error = message.showAndWait();
//			if (error.get() == ButtonType.OK) {
//				event.consume();
//				return;
//			}
//		}
//		
//		//CHECK IF USERNAME CONTAINS MIN 5 CHARACTER MAX 50 CHARACTER
//		if (!(usernameField.getText().length() >= 5 && usernameField.getText().length() <= 50)) {
//			isValid = false;
//			Alert message = new Alert(AlertType.ERROR);
//			message.setTitle("Register Failed");
//			message.setContentText("Please select date of birth");
//			Optional<ButtonType> error = message.showAndWait();
//			if (error.get() == ButtonType.OK) {
//				event.consume();
//				return;
//			}
//		}
//		
//		//CHECK IF GENDER IS SELECTED
//		if (genderGroup.getSelectedToggle() == null) {
//			isValid = false;
//			Alert message = new Alert(AlertType.ERROR);
//			message.setTitle("Register Failed");
//			message.setContentText("Select your gender");
//			Optional<ButtonType> error = message.showAndWait();
//			if (error.get() == ButtonType.OK) {
//				event.consume();
//				return;
//			}
//		}
//		
//	
//			
//		if (isValid) {
//		String selectedGender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
//		String query = "INSERT INTO users (username, password, phone, address, email, gender)"
//						+ "VALUES ('" + usernameField.getText() +"', '" +passwordField.getText()+ "', '"+ phoneField.getText()+"', '" +addressField.getText()+ "', '"+emailField.getText()+"', '"+ selectedGender +"');";
//		connect.execUpdate(query);
//		 	
//		 	System.out.println(" cek db masuk gk?");
//		}
//}
//
//		
			
	}

