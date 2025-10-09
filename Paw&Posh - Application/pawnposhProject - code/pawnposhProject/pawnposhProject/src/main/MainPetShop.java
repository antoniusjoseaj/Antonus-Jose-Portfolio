package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPetShop extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Paw & Posh");
        showSplashScreen();  
    }

    private void showSplashScreen() {
        SplashScreen splash = new SplashScreen(this);  
        Scene scene = new Scene(splash, 1280, 720); 
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showAfterSplash() {
        AfterSplash afterSplash = new AfterSplash(this);
        Scene scene = new Scene(afterSplash, 1280, 720);  
        primaryStage.setScene(scene);
    }

    public void showRegistPage() {
        Register registerPage = new Register(this);
        Scene scene = new Scene(registerPage, 1280, 720);
        primaryStage.setScene(scene);
    }

    public void showLoginPage() {
        LoginPage loginPage = new LoginPage(this);
        Scene scene = new Scene(loginPage, 1280, 720);
        primaryStage.setScene(scene);
    }

    public void showMainMenu() {
        MainMenuPS mainMenu = new MainMenuPS(this);
        Scene scene = new Scene(mainMenu.getLayout(), 1280, 720);
        primaryStage.setScene(scene);
    }
    public void showAddPetPage() {
        AddPet addPetPage = new AddPet(this);
        Scene scene = new Scene(addPetPage, 1280, 720);
        primaryStage.setScene(scene);
    }
    public void showPetCheckPage() {
        PetCheck petCheckPage = new PetCheck(this);
        Scene scene = new Scene(petCheckPage, 1280, 720);
        primaryStage.setScene(scene);
    }
    public void showGroomingPage(String petName) {
        Grooming groomingPage = new Grooming(this, petName);
        Scene scene = new Scene(groomingPage.getLayout(), 1280, 720);
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}