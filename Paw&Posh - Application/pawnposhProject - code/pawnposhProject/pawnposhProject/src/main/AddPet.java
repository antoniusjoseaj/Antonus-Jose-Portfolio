package main;

import java.sql.Date;
import java.time.LocalDate;

import helper.Connect;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AddPet extends VBox {

    private MainPetShop app;
    Connect connect = Connect.getInstance();

    public AddPet(MainPetShop app) {
        this.app = app;
        setupUI();
    }

    private void setupUI() {
        setPadding(new Insets(20));
        setSpacing(10);
        setStyle("-fx-background-color: #FDB0C0;");

        TextField petNameField = new TextField();
        petNameField.setPromptText("Enter pet name");

        DatePicker dobPicker = new DatePicker();
        dobPicker.setPromptText("Select date of birth");
        
        Button btnGetDate = new Button("Get Date");

        // Menambahkan logika untuk mengambil data dari DatePicker
        btnGetDate.setOnAction(event -> {
        	LocalDate selectedDate = dobPicker.getValue(); // Mengambil nilai tanggal
            if (selectedDate != null) {
                System.out.println("Selected Date: " + selectedDate);
            } else {
                System.out.println("No date selected.");
            }
        });


        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleButton = new RadioButton("Male");
        maleButton.setToggleGroup(genderGroup);
        RadioButton femaleButton = new RadioButton("Female");
        femaleButton.setToggleGroup(genderGroup);
        RadioButton otherButton = new RadioButton("Other");
        otherButton.setToggleGroup(genderGroup);

        TextField petRaceField = new TextField();
        petRaceField.setPromptText("Enter pet race");
        

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
           
            String selectedGender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
            LocalDate selectedDate = dobPicker.getValue();

            //submitted data nya
            ;
    		String query = "INSERT INTO pets (  name, dob, gender, race)"
    						+ "VALUES ('" + petNameField.getText() +"', '" + selectedDate+ "', '"+ selectedGender+ "', '"+petRaceField.getText()+"');";
    		connect.execUpdate(query);
    		 	
            
            //arahin ke petcheck
            app.showPetCheckPage();  
        });

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(15);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));

        grid.add(new Label("Pet Name:"), 0, 0);
        grid.add(petNameField, 1, 0);
        grid.add(new Label("Date of Birth:"), 0, 1);
        grid.add(dobPicker, 1, 1);
        grid.add(new Label("Gender:"), 0, 2);
        grid.add(maleButton, 1, 2);
        grid.add(femaleButton, 1, 3);
        grid.add(new Label(" Race:"), 0, 5);
        grid.add(petRaceField, 1, 5);
        grid.add(submitButton, 1, 6);

        getChildren().add(grid);
        setAlignment(Pos.CENTER);
    }
}