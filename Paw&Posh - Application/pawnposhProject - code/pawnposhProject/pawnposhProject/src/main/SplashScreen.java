package main;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashScreen extends BorderPane {

    private MainPetShop app;  

    public SplashScreen(MainPetShop app) {
        this.app = app;
        start();
    }

    public void start() {
        // Logo
        ImageView logo = new ImageView(new Image("file:resources/pawnposh.png"));
        logo.setFitWidth(300);
        logo.setPreserveRatio(true);
        
        // Layout untuk   logo
        this.setCenter(logo);
        this.setStyle("-fx-background-color: #FDB0C0;");  
        
        // Delay 2 detik
        PauseTransition delay = new PauseTransition(Duration.seconds(2)); 
        delay.setOnFinished(event -> {
        	//arahin ke class aftersplash
            app.showAfterSplash();  
        });
        delay.play();
    }
}