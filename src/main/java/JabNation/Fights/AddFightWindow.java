package JabNation.Fights;

import JabNation.Boxer.Boxer;
import JabNation.Utils.FightManagement;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AddFightWindow {

    public static void display(ObservableList<Boxer> boxers) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Fight");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label selectBoxer1Label = new Label("Select Boxer 1:");
        ComboBox<Boxer> boxer1ComboBox = new ComboBox<>();
        boxer1ComboBox.setPromptText("Select Boxer 1");

        Label selectBoxer2Label = new Label("Select Boxer 2:");
        ComboBox<Boxer> boxer2ComboBox = new ComboBox<>();
        boxer2ComboBox.setPromptText("Select Boxer 2");

        loadBoxersFromFile(boxers, boxer1ComboBox, boxer2ComboBox);

        Button addButton = new Button("Add Fight");
        addButton.setOnAction(e -> {
            Boxer boxer1 = boxer1ComboBox.getValue();
            Boxer boxer2 = boxer2ComboBox.getValue();
            if (boxer1 == null || boxer2 == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Boxers not selected");
                alert.setContentText("Please select both boxers");
                alert.showAndWait();
            } else if (boxer1.equals(boxer2)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Same boxers selected");
                alert.setContentText("Please select different boxers");
                alert.showAndWait();
            } else {
                FightManagement.saveFight(boxer1.getName(), boxer2.getName());
                window.close();
            }
        });

        grid.add(selectBoxer1Label, 0, 0);
        grid.add(boxer1ComboBox, 1, 0);
        grid.add(selectBoxer2Label, 0, 1);
        grid.add(boxer2ComboBox, 1, 1);
        grid.add(addButton, 1, 2);

        Scene scene = new Scene(grid, 400, 200);
        window.setScene(scene);
        window.showAndWait();
    }

    private static void loadBoxersFromFile(ObservableList<Boxer> boxers, ComboBox<Boxer> boxer1ComboBox, ComboBox<Boxer> boxer2ComboBox) {
        try (BufferedReader reader = new BufferedReader(new FileReader("boxers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Boxer boxer = new Boxer(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), parts[4], parts[5], Double.parseDouble(parts[6]), Double.parseDouble(parts[7]), Double.parseDouble(parts[8]), parts[9], Boolean.parseBoolean(parts[10]), parts[11]);
                boxers.add(boxer);
            }
            boxer1ComboBox.setItems(boxers);
            boxer2ComboBox.setItems(boxers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
