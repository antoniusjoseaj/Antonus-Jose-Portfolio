package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AfterSplash extends VBox {

    public AfterSplash(MainPetShop app) {
        // Set VBox nya
        setPadding(new Insets(40));
        setSpacing(20);
        setStyle("-fx-background-color: #FDB0C0;");

         Text title = new Text("Welcome to Paw & Posh!");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #4A4A4A;");
         
        //login button
         Button loginButton = new Button("Login");
        loginButton.setStyle(
            "-fx-background-color: #FF6F91; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 16px; " +
            "-fx-padding: 10 20; " +
            "-fx-background-radius: 5; " +
            "-fx-border-radius: 5;"
        );

        // Register button  
        Button registerButton = new Button("Register");
        registerButton.setStyle(
            "-fx-background-color: #FF6F91; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 16px; " +
            "-fx-padding: 10 20; " +
            "-fx-background-radius: 5; " +
            "-fx-border-radius: 5;"
        );

       //arahin ke login page
        loginButton.setOnAction(e -> {
            app.showLoginPage();  
        });

        // arahin ke registpage
        registerButton.setOnAction(e -> {
            app.showRegistPage();  
        });

        
        getChildren().addAll(title, loginButton, registerButton);

        //di center
        setAlignment(Pos.CENTER);
    }
}
