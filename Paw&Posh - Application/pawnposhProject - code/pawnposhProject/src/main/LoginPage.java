package main;

import java.sql.SQLException;
import java.util.Optional;

import helper.Connect;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginPage extends VBox {
	 TextField emailField = new TextField();
	 PasswordField passwordField = new PasswordField();
	 Connect connect = Connect.getInstance();
	 
    public LoginPage(MainPetShop primaryStage) {
    	
        // layout VBox
        setPadding(new Insets(20));
        setSpacing(15);
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #FDB0C0;");

        // styling text
        Text title = new Text("Welcome to Paw & Posh");
        title.setFont(Font.font("Arial", 24));
        title.setStyle("-fx-fill: #4A4A4A;");

        // email 
       
        emailField.setPromptText("Enter username (5-50 characters)");
        emailField.setMaxWidth(300);
        
        Label emailLabel = new Label("Email");
        emailLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #4A4A4A;");
        

        // Password  
       
        passwordField.setPromptText("Enter password (alphanumeric)");
        passwordField.setMaxWidth(300);
        
        Label passwordLabel = new Label("Password");
        passwordLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #4A4A4A;");

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setStyle(
            "-fx-background-color: #FF6F91; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 14px; " +
            "-fx-padding: 10 20; " +
            "-fx-background-radius: 5;"
        );
        loginButton.setOnAction(event -> {
        if (emailField.getText().isBlank() && passwordField.getText().isBlank()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Login");
			alert.setContentText("please fill out all fields");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				event.consume();
				return;
			}
		};
		String query = "SELECT * FROM users " + "WHERE email = '" + emailField.getText() + "'";
	 	connect.rs = connect.execQuery(query);
	 	
	 	try {
			if (connect.rs.next()) {
				if (emailField.getText().equals(connect.rs.getString("email")) && passwordField.getText().equals(connect.rs.getString("password")) ) {
				primaryStage.showMainMenu();

				}else{
						Alert alertCredential = new Alert(AlertType.ERROR);
						alertCredential.setTitle("Wrong Credential");
						alertCredential.setContentText("You entered a wrong email or password");
						Optional<ButtonType> resultCredential = alertCredential.showAndWait();
						if (resultCredential.get() == ButtonType.OK) {
							event.consume();
						}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	});
        getChildren().addAll(title,emailLabel, emailField, passwordLabel , passwordField, loginButton);

    }
  

	
	}

//            if (isValidInput(username, password)) {
//                System.out.println("Login successful!");
//                primaryStage.showMainMenu(); // Navigate to the main menu
//            } else {
//                System.out.println("Invalid input. Please check your entries.");
//                showAlert("Invalid Input", "Please ensure all fields are filled correctly.");
//            }
//        });

         
//        

//     
//    private boolean isValidInput(String username, String password) {
//        return username.length() >= 5 && username.length() <= 50 &&
//               password.matches("^[a-zA-Z0-9]+$");
//    }
//
//    
//    private void showAlert(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.WARNING);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//}
