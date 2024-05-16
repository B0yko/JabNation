package JabNation.Boxer;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import static JabNation.Utils.DataManagement.saveBoxersToFile;
import static JabNation.Utils.DataManagement.updateBoxersList;

public class AddBoxer {
    public static void addBoxer(Button addBoxerButton, Stage primaryStage, TableView<Boxer> tableView, ObservableList<Boxer> boxers) {
        addBoxerButton.setOnAction(e -> {
            AddBoxerDialog.open(primaryStage, boxers);
            saveBoxersToFile(boxers);
            updateBoxersList(tableView, boxers);
        });
    }
}
