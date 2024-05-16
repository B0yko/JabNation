package JabNation;
/*
import JabNation.Boxer.Boxer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.RangeSlider;

import java.io.*;

public class JabNationApp extends Application {
    private ObservableList<Boxer> originalBoxers;

    public void updateBoxersList(TableView<Boxer> tableView, ObservableList<Boxer> boxers) {
        tableView.setItems(boxers);
    }
    private static final String BOXERS_FILE_PATH = "boxers.csv";
    private static final String CSV_SEPARATOR = ",";

    private ObservableList<Boxer> loadBoxersFromFile() {
        ObservableList<Boxer> boxers = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(BOXERS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(CSV_SEPARATOR);
                Boxer boxer = new Boxer(
                        parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]), parts[4],
                        parts[5], Double.parseDouble(parts[6]),
                        Double.parseDouble(parts[7]), Double.parseDouble(parts[8]),
                        parts[9], Boolean.parseBoolean(parts[10])
                );
                boxers.add(boxer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return boxers;
    }


    private void saveBoxersToFile(ObservableList<Boxer> boxers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOXERS_FILE_PATH))) {
            for (Boxer boxer : boxers) {
                writer.write(boxer.getName() + CSV_SEPARATOR +
                        boxer.getNickname() + CSV_SEPARATOR +
                        boxer.getSurname() + CSV_SEPARATOR +
                        boxer.getAge() + CSV_SEPARATOR +
                        boxer.getRecord() + CSV_SEPARATOR +
                        boxer.getDivision() + CSV_SEPARATOR +
                        boxer.getWeight() + CSV_SEPARATOR +
                        boxer.getHeight() + CSV_SEPARATOR +
                        boxer.getReach() + CSV_SEPARATOR +
                        boxer.getNation() + CSV_SEPARATOR +
                        boxer.isActive());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showAllBoxers(TableView<Boxer> tableView) {
        tableView.setItems(originalBoxers);
    }

    @Override
    public void start(Stage primaryStage) {
        TableView<Boxer> tableView = new TableView<>();

        GridPane root = new GridPane();
        GridPane top = createColoredBox(Color.BLACK);
        GridPane top2 = createColoredBox(Color.DARKRED);
        GridPane down = createColoredBox(Color.LIGHTGRAY);

        VBox filterBox = new VBox();
        VBox infoBox = new VBox();
        GridPane mainPane = new GridPane();

        top.setPrefSize(12000, 40);
        top2.setPrefSize(12000, 110);

        ImageView logoImageView = new ImageView(new Image("file:///Users/andrejbojko/Downloads/logo3.png"));
        logoImageView.setFitWidth(130);
        logoImageView.setFitHeight(100);

        Button addBoxerButton = new Button("Add Boxer");

        Button deleteBoxerButton = new Button("Delete Boxer");

        Button boxerList = new Button("Boxers List");
        boxerList.setOnAction(event -> showAllBoxers(tableView));

        Button addFight = new Button("Add Fight");
        Button deleteFight = new Button("Delete Fight");
        Button matchesList = new Button("Matches List");

        Button signUp = new Button("Sign Up");
        Button login = new Button("Login");

        top.setPadding(new Insets(10));
        top.add(signUp, 0, 0);

        GridPane.setMargin(signUp, new Insets(0, 40, 0, 20));
        top.add(login, 1, 0);

        GridPane.setMargin(login, new Insets(0, 0, 0, 0));

        top2.setPadding(new Insets(10));
        top2.add(addBoxerButton, 0, 0);

        GridPane.setMargin(addBoxerButton, new Insets(0, 100, 0, 20));
        top2.add(deleteBoxerButton, 1, 0);

        GridPane.setMargin(deleteBoxerButton, new Insets(0, 100, 0, 0));
        top2.add(boxerList, 2, 0);

        GridPane.setMargin(boxerList, new Insets(0, 60, 0, 0));
        top2.add(logoImageView, 3, 0);
        top2.add(addFight, 4, 0);

        GridPane.setMargin(addFight, new Insets(0, 0, 0, 60));
        top2.add(deleteFight, 5, 0);

        GridPane.setMargin(deleteFight, new Insets(0, 0, 0, 100));
        top2.add(matchesList, 6, 0);

        GridPane.setMargin(matchesList, new Insets(0, 20, 0, 100));

        filterBox.setPadding(new Insets(10));
        Label filtersLabel = new Label("Filters:");
        filtersLabel.setStyle("-fx-font-size: 20pt; -fx-font-weight: bold;");

        CheckComboBox<String> nationChoiceBox = new CheckComboBox<>();
        nationChoiceBox.getItems().addAll("USA", "UK", "Germany", "France", "Japan");
        Button chooseAllNationsButton = new Button("Choose All");
        chooseAllNationsButton.setOnAction(e -> {
            if (nationChoiceBox.getCheckModel().getCheckedItems().isEmpty()) {
                nationChoiceBox.getCheckModel().checkAll();
            } else {
                nationChoiceBox.getCheckModel().clearChecks();
            }
        });

        CheckComboBox<String> divisionChoiceBox = new CheckComboBox<>();
        divisionChoiceBox.getItems().addAll("Flyweight", "Featherweight", "Lightweight", "Welterweight", "Middleweight", "Light Heavyweight", "Heavyweight", "Super Heavyweight");
        Button chooseAllDivisionsButton = new Button("Choose All");
        chooseAllDivisionsButton.setOnAction(e -> {
            if (divisionChoiceBox.getCheckModel().getCheckedItems().isEmpty()) {
                divisionChoiceBox.getCheckModel().checkAll();
            } else {
                divisionChoiceBox.getCheckModel().clearChecks();
            }
        });

        Slider ageSlider = new Slider(0, 120, 18);
        RangeSlider ageRangeSlider = new RangeSlider(0, 120, 18, 60);

        Slider heightSlider = new Slider(0, 240, 180);
        RangeSlider heightRangeSlider = new RangeSlider(0, 240, 160, 200);

        Slider reachSlider = new Slider(0, 240, 180);
        RangeSlider reachRangeSlider = new RangeSlider(0, 240, 160, 200);

        ageSlider.setShowTickLabels(true);
        ageSlider.setShowTickMarks(true);
        heightSlider.setShowTickLabels(true);
        heightSlider.setShowTickMarks(true);
        reachSlider.setShowTickLabels(true);
        reachSlider.setShowTickMarks(true);

        Label ageRangeLabel = new Label("Age: " + ageSlider.getValue());
        Label heightRangeLabel = new Label("Height: " + heightSlider.getValue());
        Label reachRangeLabel = new Label("Reach: " + reachSlider.getValue());

        ageSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            ageRangeLabel.setText("Age Range: " + newValue.intValue());
        });

        ageRangeSlider.lowValueProperty().addListener((observable, oldValue, newValue) -> {
            ageSlider.setMin(newValue.intValue());
            ageRangeLabel.setText("Age Range: " + newValue.intValue() + " - " + ageRangeSlider.highValueProperty().getValue().intValue());
        });

        ageRangeSlider.highValueProperty().addListener((observable, oldValue, newValue) -> {
            ageSlider.setMax(newValue.intValue());
            ageRangeLabel.setText("Age Range: " + ageRangeSlider.lowValueProperty().getValue().intValue() + " - " + newValue.intValue());
        });


        heightSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            heightRangeLabel.setText("Height Range: " + newValue.intValue());
        });

        heightRangeSlider.lowValueProperty().addListener((observable, oldValue, newValue) -> {
            heightSlider.setMin(newValue.intValue());
            heightRangeLabel.setText("Height Range: " + newValue.intValue() + " - " + heightRangeSlider.highValueProperty().getValue().intValue());
        });

        heightRangeSlider.highValueProperty().addListener((observable, oldValue, newValue) -> {
            heightSlider.setMax(newValue.intValue());
            heightRangeLabel.setText("Age Range: " + heightRangeSlider.lowValueProperty().getValue().intValue() + " - " + newValue.intValue());
        });


        reachSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            reachRangeLabel.setText("Reach Range: " + newValue.intValue());
        });

        reachRangeSlider.lowValueProperty().addListener((observable, oldValue, newValue) -> {
            reachSlider.setMin(newValue.intValue());
            reachRangeLabel.setText("Reach Range: " + newValue.intValue() + " - " + reachRangeSlider.highValueProperty().getValue().intValue());
        });

        reachRangeSlider.highValueProperty().addListener((observable, oldValue, newValue) -> {
            reachSlider.setMax(newValue.intValue());
            reachRangeLabel.setText("Reach Range: " + reachRangeSlider.lowValueProperty().getValue().intValue() + " - " + newValue.intValue());
        });

        CheckBox activeFightersCheckBox = new CheckBox("Active");
        CheckBox inactiveFightersCheckBox = new CheckBox("Inactive");

        Button applyButton = new Button("Apply");
        applyButton.setStyle("-fx-background-color: #800000; " + "-fx-text-fill: white; " + "-fx-font-weight: bold;");

        VBox.setMargin(nationChoiceBox, new Insets(0, 0, 10, 0));
        VBox.setMargin(chooseAllNationsButton, new Insets(0, 0, 30, 0));
        VBox.setMargin(filtersLabel, new Insets(0, 0, 30, 0));
        VBox.setMargin(divisionChoiceBox, new Insets(0, 0, 10, 0));
        VBox.setMargin(chooseAllDivisionsButton, new Insets(0, 0, 30, 0));
        VBox.setMargin(ageRangeLabel, new Insets(0, 0, 30, 0));
        VBox.setMargin(heightRangeLabel, new Insets(0, 0, 30, 0));
        VBox.setMargin(reachRangeLabel, new Insets(0, 0, 30, 0));
        VBox.setMargin(activeFightersCheckBox, new Insets(10, 0, 5, 0));

        HBox applyButtonBox = new HBox();
        applyButtonBox.getChildren().addAll(activeFightersCheckBox, applyButton);
        applyButtonBox.setSpacing(120);
        filterBox.getChildren().addAll(
                filtersLabel,
                new Label("Nations:"),
                nationChoiceBox,
                chooseAllNationsButton,
                new Label("Weight Divisions:"),
                divisionChoiceBox,
                chooseAllDivisionsButton,
                new Label("Age:"),
                ageRangeSlider,
                ageRangeLabel,
                new Label("Height:"),
                heightRangeSlider,
                heightRangeLabel,
                new Label("Reach:"),
                reachRangeSlider,
                reachRangeLabel,
                new Label("Active Status:"),
                applyButtonBox,
                inactiveFightersCheckBox
        );
        filterBox.setPrefWidth(300);

        tableView.setPrefSize(600, 600);

        TableColumn<Boxer, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Boxer, String> nicknameColumn = new TableColumn<>("Nickname");
        nicknameColumn.setCellValueFactory(new PropertyValueFactory<>("nickname"));

        TableColumn<Boxer, String> surnameColumn = new TableColumn<>("Surname");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<Boxer, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Boxer, String> recordColumn = new TableColumn<>("Record");
        recordColumn.setCellValueFactory(new PropertyValueFactory<>("record"));

        TableColumn<Boxer, String> divisionColumn = new TableColumn<>("Division");
        divisionColumn.setCellValueFactory(new PropertyValueFactory<>("division"));

        TableColumn<Boxer, Double> weightColumn = new TableColumn<>("Weight");
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        TableColumn<Boxer, Double> heightColumn = new TableColumn<>("Height");
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));

        TableColumn<Boxer, Double> reachColumn = new TableColumn<>("Reach");
        reachColumn.setCellValueFactory(new PropertyValueFactory<>("reach"));

        TableColumn<Boxer, String> nationColumn = new TableColumn<>("Nation");
        nationColumn.setCellValueFactory(new PropertyValueFactory<>("nation"));

        TableColumn<Boxer, Boolean> activeColumn = new TableColumn<>("Active");
        activeColumn.setCellValueFactory(new PropertyValueFactory<>("active"));

        tableView.getColumns().addAll(
                nameColumn, nicknameColumn, surnameColumn, ageColumn,
                recordColumn, divisionColumn, weightColumn, heightColumn, reachColumn, nationColumn, activeColumn);

        ObservableList<Boxer> boxers = loadBoxersFromFile();
        tableView.setItems(boxers);

        ScrollPane listBox = new ScrollPane(tableView);
        listBox.setPrefWidth(600);

        mainPane.add(filterBox, 0, 0);
        mainPane.add(listBox, 1, 0);
        mainPane.add(infoBox, 2, 0);

        root.add(top, 0, 0);
        root.add(top2, 0, 1);
        root.add(mainPane, 0, 2);
        root.add(down, 0, 3);
        down.setPrefSize(12000, 60);

        addBoxerButton.setOnAction(e -> {
            saveBoxersToFile(boxers);
            updateBoxersList(tableView, boxers);
        });

        deleteBoxerButton.setOnAction(e -> {
            Boxer selectedBoxer = tableView.getSelectionModel().getSelectedItem();
            if (selectedBoxer != null) {
                boxers.remove(selectedBoxer);
                tableView.getSelectionModel().clearSelection();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Boxer Selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select a boxer to delete.");
                alert.showAndWait();
            }
        });

        boxerList.setOnAction(e -> {
            tableView.setItems(boxers);
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                infoBox.getChildren().clear();
                Label nameLabel = new Label("Name: " + newSelection.getName());
                Label nicknameLabel = new Label("Nickname: " + newSelection.getNickname());
                Label surnameLabel = new Label("Surname: " + newSelection.getSurname());
                Label ageLabel = new Label("Age: " + newSelection.getAge());
                Label recordLabel = new Label("Record: " + newSelection.getRecord());
                Label divisionLabel = new Label("Division: " + newSelection.getDivision());
                Label weightLabel = new Label("Weight: " + newSelection.getWeight());
                Label heightLabel = new Label("Height: " + newSelection.getHeight());
                Label reachLabel = new Label("Reach: " + newSelection.getReach());
                Label nationLabel = new Label("Nation: " + newSelection.getNation());
                Label activeLabel = new Label("Active: " + (newSelection.isActive() ? "Yes" : "No"));

                infoBox.getChildren().addAll(nameLabel, nicknameLabel, surnameLabel, ageLabel, recordLabel,
                        divisionLabel, weightLabel, heightLabel, reachLabel, nationLabel, activeLabel);
            }
        });

        Scene scene = new Scene(root, 1200, 900);

        primaryStage.setScene(scene);
        primaryStage.setTitle("JabNation App");
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> saveBoxersToFile(tableView.getItems()));
    }
    private GridPane createColoredBox(Color color) {
        GridPane box = new GridPane();
        box.setStyle("-fx-background-color: " + toRGBCode(color) + ";");
        return box;
    }

    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
    public static void main(String[] args) {
        launch(args);
    }
}*/
