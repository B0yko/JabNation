package JabNation.Boxer;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import static JabNation.Utils.DataManagement.saveBoxersToFile;

public class DeleteBoxer {
    public static void deleteBoxer(Button deleteBoxerButton, TableView<Boxer> tableView, ObservableList<Boxer> boxers) {
        deleteBoxerButton.setOnAction(e -> {
            Boxer selectedBoxer = tableView.getSelectionModel().getSelectedItem();
            if (selectedBoxer != null) {
                boxers.remove(selectedBoxer);
                saveBoxersToFile(boxers);
                tableView.getSelectionModel().clearSelection();
                tableView.refresh();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Boxer Selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select a boxer to delete.");
                alert.showAndWait();
            }
        });
    }
}

