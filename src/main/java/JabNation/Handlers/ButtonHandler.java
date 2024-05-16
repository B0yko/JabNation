package JabNation.Handlers;

import JabNation.Boxer.Boxer;
import JabNation.Boxer.DeleteBoxer;
import JabNation.Boxer.AddBoxer;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ButtonHandler {
    private Button addBoxerButton;
    private Button deleteBoxerButton;
    private Stage primaryStage;
    private TableView<Boxer> tableView;
    private ObservableList<Boxer> boxers;

    public ButtonHandler(Button addBoxerButton, Button deleteBoxerButton, Stage primaryStage, TableView<Boxer> tableView, ObservableList<Boxer> boxers) {
        this.addBoxerButton = addBoxerButton;
        this.deleteBoxerButton = deleteBoxerButton;
        this.primaryStage = primaryStage;
        this.tableView = tableView;
        this.boxers = boxers;

        addBoxerButton.setOnAction(event -> AddBoxer.addBoxer(addBoxerButton, primaryStage, tableView, boxers));

        deleteBoxerButton.setOnAction(event -> DeleteBoxer.deleteBoxer(deleteBoxerButton, tableView, boxers));
    }
}


