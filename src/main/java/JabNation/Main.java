package JabNation;

import JabNation.Boxer.Boxer;
import JabNation.Filters.ApplyFilters;
import JabNation.Handlers.ButtonHandler;
import JabNation.Fights.AddFightWindow;
import JabNation.Utils.BoxerSearch;
import JabNation.Utils.BoxerSorter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.RangeSlider;

import static JabNation.Utils.ColorUI.createColoredBox;
import static JabNation.Utils.DataManagement.loadBoxersFromFile;

public class Main extends Application {
    private final ObservableList<Boxer> boxers = FXCollections.observableArrayList();
    private void showAllBoxers(TableView<Boxer> tableView) {
        tableView.setItems(loadBoxersFromFile());
    }

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        TableView<Boxer> tableView = new TableView<>();
        BoxerSorter.sortBoxers("boxers.txt");
        GridPane root = new GridPane();
        GridPane top = createColoredBox(Color.BLACK);
        GridPane top2 = createColoredBox(Color.DARKRED);
        GridPane down = createColoredBox(Color.LIGHTGRAY);
        GridPane mainPane = new GridPane();

        VBox filterBox = new VBox();
        VBox infoBox = new VBox();

        HBox applyButtonBox = new HBox();

        Button addBoxerButton = new Button("Add Boxer");
        Button deleteBoxerButton = new Button("Delete Boxer");
        Button boxerList = new Button("Boxers List");
        Button addFight = new Button("Add Fight");
        Button deleteFight = new Button("Delete Fight");
        Button matchesList = new Button("Matches List");
        Button signUp = new Button("Sign Up");
        Button login = new Button("Login");
        Button chooseAllNationsButton = new Button("Choose All");
        Button chooseAllDivisionsButton = new Button("Choose All");
        Button applyButton = new Button("Apply");

        CheckComboBox<String> nationChoiceBox = new CheckComboBox<>();
        nationChoiceBox.getItems().addAll("USA", "UK", "Ukraine", "Germany", "France", "Japan", "Ukraine");
        TextField searchField = new TextField();
        searchField.setPromptText("Search by name or surname");
        searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String query = searchField.getText().trim();
                if (!query.isEmpty()) {
                    BoxerSearch.searchAndDisplay(query, tableView);
                } else {
                    tableView.setItems(boxers);
                }
            }
        });


        CheckComboBox<String> divisionChoiceBox = new CheckComboBox<>();
        divisionChoiceBox.getItems().addAll("Flyweight", "Featherweight", "Lightweight", "Welterweight",
                "Middleweight", "Light Heavyweight", "Heavyweight", "Super Heavyweight");
        CheckBox activeFightersCheckBox = new CheckBox("Active");
        CheckBox inactiveFightersCheckBox = new CheckBox("Inactive");

        Slider ageSlider = new Slider(0, 120, 18);
        RangeSlider ageRangeSlider = new RangeSlider(0, 120, 18, 60);
        Slider heightSlider = new Slider(0, 240, 180);
        RangeSlider heightRangeSlider = new RangeSlider(0, 240, 160, 200);
        Slider reachSlider = new Slider(0, 240, 180);
        RangeSlider reachRangeSlider = new RangeSlider(0, 240, 160, 200);

        Label ageRangeLabel = new Label("Age: " + ageSlider.getValue());
        Label heightRangeLabel = new Label("Height: " + heightSlider.getValue());
        Label reachRangeLabel = new Label("Reach: " + reachSlider.getValue());

        ImageView logoImageView = new ImageView(new Image("file:///Users/andrejbojko/Downloads/logo3.png"));
        logoImageView.setFitWidth(130);
        logoImageView.setFitHeight(100);
        filterBox.setPrefWidth(300);
        tableView.setPrefSize(600, 600);

        top.setPrefSize(12000, 40);
        top2.setPrefSize(12000, 110);
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
        applyButton.setStyle("-fx-background-color: #800000; " + "-fx-text-fill: white; " + "-fx-font-weight: bold;");

        ageSlider.setShowTickLabels(true);
        ageSlider.setShowTickMarks(true);
        heightSlider.setShowTickLabels(true);
        heightSlider.setShowTickMarks(true);
        reachSlider.setShowTickLabels(true);
        reachSlider.setShowTickMarks(true);

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
            heightRangeLabel.setText("Height Range: " + heightRangeSlider.lowValueProperty().getValue().intValue() + " - " + newValue.intValue());
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

        VBox.setMargin(nationChoiceBox, new Insets(0, 0, 10, 0));
        VBox.setMargin(chooseAllNationsButton, new Insets(0, 0, 30, 0));
        VBox.setMargin(filtersLabel, new Insets(0, 0, 30, 0));
        VBox.setMargin(divisionChoiceBox, new Insets(0, 0, 10, 0));
        VBox.setMargin(chooseAllDivisionsButton, new Insets(0, 0, 30, 0));
        VBox.setMargin(ageRangeLabel, new Insets(0, 0, 30, 0));
        VBox.setMargin(heightRangeLabel, new Insets(0, 0, 30, 0));
        VBox.setMargin(reachRangeLabel, new Insets(0, 0, 30, 0));
        VBox.setMargin(activeFightersCheckBox, new Insets(10, 0, 5, 0));

        chooseAllNationsButton.setOnAction(e -> {
            if (nationChoiceBox.getCheckModel().getCheckedItems().isEmpty()) {
                nationChoiceBox.getCheckModel().checkAll();
            } else {
                nationChoiceBox.getCheckModel().clearChecks();
            }
        });

        chooseAllDivisionsButton.setOnAction(e -> {
            if (divisionChoiceBox.getCheckModel().getCheckedItems().isEmpty()) {
                divisionChoiceBox.getCheckModel().checkAll();
            } else {
                divisionChoiceBox.getCheckModel().clearChecks();
            }
        });

        applyButtonBox.getChildren().addAll(activeFightersCheckBox, applyButton);
        applyButtonBox.setSpacing(120);
        filterBox.getChildren().addAll(
                filtersLabel,
                searchField,
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

        ScrollPane listBox = new ScrollPane(tableView);
        listBox.setPrefWidth(600);
        tableView.setItems(boxers);
        ButtonHandler buttonHandler = new ButtonHandler(addBoxerButton, deleteBoxerButton, primaryStage, tableView, boxers);
        boxerList.setOnAction(event -> showAllBoxers(tableView));
        applyButton.setOnAction(event -> {
            ApplyFilters.applyFilters(
                    boxers,
                    tableView,
                    heightRangeSlider,
                    reachRangeSlider,
                    ageRangeSlider
            );
        });

        tableView.setEditable(true);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        nicknameColumn.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        nicknameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        ageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        recordColumn.setCellValueFactory(new PropertyValueFactory<>("record"));
        recordColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        divisionColumn.setCellValueFactory(new PropertyValueFactory<>("division"));
        divisionColumn.setCellFactory(ComboBoxTableCell.forTableColumn("Flyweight", "Featherweight", "Lightweight", "Welterweight",
                "Middleweight", "Light Heavyweight", "Heavyweight", "Super Heavyweight"));

        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        weightColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
        heightColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        reachColumn.setCellValueFactory(new PropertyValueFactory<>("reach"));
        reachColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        nationColumn.setCellValueFactory(new PropertyValueFactory<>("nation"));
        nationColumn.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList("USA", "UK", "Ukraine", "Germany", "France", "Japan", "Ukraine", "Poland", "Italy",
                "Spain", "Portugal", "Brazil", "Argentina", "Mexico", "Canada", "Australia", "China", "Japan", "South Korea", "India",
                "South Africa", "Nigeria", "Egypt", "Kenya", "Ethiopia", "Morocco", "Turkey", "Saudi Arabia", "Iran", "Iraq", "Syria")));

        activeColumn.setCellValueFactory(new PropertyValueFactory<>("active"));
        activeColumn.setCellFactory(CheckBoxTableCell.forTableColumn(activeColumn));

        mainPane.add(filterBox, 0, 0);
        mainPane.add(listBox, 1, 0);
        mainPane.add(infoBox, 2, 0);

        root.add(top, 0, 0);
        root.add(top2, 0, 1);
        root.add(mainPane, 0, 2);
        root.add(down, 0, 3);
        down.setPrefSize(12000, 60);

        Scene scene = new Scene(root, 1200, 900);

        primaryStage.setScene(scene);
        primaryStage.setTitle("JabNation App");
        primaryStage.show();

        addFight.setOnAction(event -> AddFightWindow.display(boxers));
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                infoBox.getChildren().clear();

                HBox hbox = new HBox();
                hbox.setAlignment(Pos.CENTER);

                Label spacer = new Label("");
                spacer.setPrefWidth(50);
                HBox redBox = new HBox();
                redBox.setStyle("-fx-background-color: red;");

                if (newSelection.getPhotoPath() != null && !newSelection.getPhotoPath().isEmpty()) {
                    ImageView photoImageView = new ImageView();
                    photoImageView.setImage(new Image(newSelection.getPhotoPath()));
                    photoImageView.setFitWidth(200);
                    photoImageView.setFitHeight(200);
                    hbox.getChildren().addAll(spacer, photoImageView);
                }

                VBox labelsVBox = new VBox();
                labelsVBox.setAlignment(Pos.CENTER);
                labelsVBox.setSpacing(20);

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

                labelsVBox.getChildren().addAll(
                        hbox,
                        redBox,
                        nameLabel,
                        nicknameLabel,
                        surnameLabel,
                        ageLabel,
                        recordLabel,
                        divisionLabel,
                        weightLabel,
                        heightLabel,
                        reachLabel,
                        nationLabel,
                        activeLabel
                );

                infoBox.getChildren().add(labelsVBox);
            }
        });
    }

}
