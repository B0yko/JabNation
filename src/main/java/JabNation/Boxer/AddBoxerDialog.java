package JabNation.Boxer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;

public class AddBoxerDialog {
    private static final String BOXERS_FILE_PATH = "boxers.csv";
    private static final String CSV_SEPARATOR = ",";
    private static class BoxersList implements Serializable {
        private List<Boxer> boxers;

        public BoxersList(List<Boxer> boxers) {
            this.boxers = boxers;
        }

        public List<Boxer> getBoxers() {
            return boxers;
        }
    }
    public static void open(Stage primaryStage, List<Boxer> boxers) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle("Add Boxer");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        TextField nameField = new TextField();
        TextField surnameField = new TextField();
        TextField nicknameField = new TextField();
        ComboBox<String> nationComboBox = new ComboBox<>();
        nationComboBox.getItems().addAll("USA", "UK", "Germany", "Ukraine", "Belarus", "Poland", "France", "Italy", "Spain", "Portugal", "Brazil", "Argentina", "Mexico", "Canada", "Australia", "China", "Japan", "South Korea", "India", "South Africa", "Nigeria", "Egypt", "Kenya", "Ethiopia", "Morocco", "Turkey", "Saudi Arabia", "Iran", "Iraq", "Syria");
        TextField nationField = new TextField();
        TextField weightField = new TextField();
        TextField ageField = new TextField();
        TextField heightField = new TextField();
        TextField reachField = new TextField();
        TextField recordField = new TextField();
        CheckBox activeCheckBox = new CheckBox("Active");


        gridPane.add(new Label("Name:"), 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(new Label("Surname:"), 0, 1);
        gridPane.add(surnameField, 1, 1);
        gridPane.add(new Label("Nickname:"), 0, 2);
        gridPane.add(nicknameField, 1, 2);
        gridPane.add(new Label("Nation:"), 0, 3);
        gridPane.add(nationComboBox, 1, 3);
        gridPane.add(nationField, 1, 4);
        gridPane.add(new Label("Weight:"), 0, 5);
        gridPane.add(weightField, 1, 5);
        gridPane.add(new Label("Age:"), 0, 6);
        gridPane.add(ageField, 1, 6);
        gridPane.add(new Label("Height:"), 0, 7);
        gridPane.add(heightField, 1, 7);
        gridPane.add(new Label("Reach:"), 0, 8);
        gridPane.add(reachField, 1, 8);
        gridPane.add(new Label("Record:"), 0, 9);
        gridPane.add(recordField, 1, 9);
        gridPane.add(new Label("Status:"), 0, 10);
        gridPane.add(activeCheckBox, 1, 10);


        Label errorLabel1 = new Label();
        Label errorLabel2 = new Label();
        Label errorLabel3 = new Label();
        Label errorLabel4 = new Label();
        Label errorLabel5 = new Label();
        Label errorLabel6 = new Label();
        Label errorLabel7 = new Label();


        Button addButton = new Button("Add");
        addButton.setOnAction(event -> {
            errorLabel1.setText("");
            errorLabel2.setText("");
            errorLabel3.setText("");
            errorLabel4.setText("");
            errorLabel5.setText("");
            errorLabel6.setText("");
            errorLabel7.setText("");

            boolean isValid = true;

            if (nameField.getText().isEmpty()) {
                errorLabel1.setText("Name is required");
                errorLabel1.setStyle("-fx-text-fill: red;");
                isValid = false;
            }

            if (surnameField.getText().isEmpty()) {
                errorLabel2.setText("Surname is required");
                errorLabel2.setStyle("-fx-text-fill: red;");
                isValid = false;
            }

            if (ageField.getText().isEmpty()) {
                errorLabel3.setText("Age is required");
                errorLabel3.setStyle("-fx-text-fill: red;");
                isValid = false;
            } else {
                try {
                    int age = Integer.parseInt(ageField.getText());
                    if (age < 0 || age > 120) {
                        errorLabel3.setText("Invalid age");
                        errorLabel3.setStyle("-fx-text-fill: red;");
                        isValid = false;
                    }
                } catch (NumberFormatException e) {
                    errorLabel3.setText("Invalid age");
                    errorLabel3.setStyle("-fx-text-fill: red;");
                    isValid = false;
                }
            }

            double weight = 0;
            if (!weightField.getText().isEmpty()) {
                weight = parseDoubleField(weightField, errorLabel4);
                if (weight == -1)
                    isValid = false;
            }

            double height = 0;
            if (!heightField.getText().isEmpty()) {
                height = parseDoubleField(heightField, errorLabel5);
                if (height == -1)
                    isValid = false;
            }

            double reach = 0;
            if (!reachField.getText().isEmpty()) {
                reach = parseDoubleField(reachField, errorLabel6);
                if (reach == -1)
                    isValid = false;
            }

            if (!isValid)
                return;

            String selectedNation = nationComboBox.getValue();

            String division = calculateDivision(weight);

            Boxer newBoxer = new Boxer(
                    nameField.getText(),
                    nicknameField.getText(),
                    surnameField.getText(),
                    Integer.parseInt(ageField.getText()),
                    recordField.getText(),
                    division,
                    weight,
                    height,
                    reach,
                    selectedNation,
                    activeCheckBox.isSelected(),
                    "file:///Users/andrejbojko/Downloads/boxers/" + nameField.getText() + ".png"
            );

            try {
                FileWriter writer = new FileWriter("boxer_photo_paths.txt", true);
                writer.write("file:///Users/andrejbojko/Downloads/boxers/" +newBoxer.getName() + ".png" + "\n");
                writer.close();
            } catch (IOException e) {
                System.err.println("Error writing photo path to file: " + e.getMessage());
            }

            boxers.add(newBoxer);
            dialogStage.close();
        });

        gridPane.add(errorLabel1, 2, 0);
        gridPane.add(errorLabel2, 2, 1);
        gridPane.add(errorLabel3, 2, 6);
        gridPane.add(errorLabel4, 2, 5);
        gridPane.add(errorLabel5, 2, 7);
        gridPane.add(errorLabel6, 2, 8);
        gridPane.add(errorLabel7, 2, 9);

        gridPane.add(addButton, 0, 11, 2, 1);
        Scene scene = new Scene(gridPane, 400, 500);
        dialogStage.setScene(scene);
        dialogStage.initOwner(primaryStage);
        dialogStage.showAndWait();
    }

    private static double parseDoubleField(TextField textField, Label errorLabel) {
        try {
            double value = Double.parseDouble(textField.getText());
            if (value <= 0) {
                errorLabel.setText("Invalid value");
                errorLabel.setStyle("-fx-text-fill: red;");
                return -1;
            }
            return value;
        } catch (NumberFormatException e) {
            errorLabel.setText("Invalid value");
            errorLabel.setStyle("-fx-text-fill: red;");
            return -1;
        }
    }
    private static String calculateDivision(double weight) {
        if (weight <= 51) {
            return "Flyweight";
        } else if (weight > 51 && weight <= 61) {
            return "Lightweight";
        } else if (weight > 61 && weight <= 67) {
            return "Welterweight";
        } else if (weight > 67 && weight <= 72.5) {
            return "Middleweight";
        } else if (weight > 72.5 && weight <= 79) {
            return "Light heavyweight";
        } else if (weight > 79 && weight <= 91) {
            return "Heavyweight";
        } else {
            return "Super heavyweight";
        }
    }
}
