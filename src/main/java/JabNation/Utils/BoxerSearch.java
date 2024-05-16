package JabNation.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import JabNation.Boxer.Boxer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class BoxerSearch {

    public static void searchAndDisplay(String query, TableView<Boxer> tableView) {
        ObservableList<Boxer> boxers = FXCollections.observableArrayList();
        String filePath = "boxers.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 12 && (parts[0].toLowerCase().contains(query.toLowerCase()) || parts[2].toLowerCase().contains(query.toLowerCase()))) {
                    Boxer boxer = new Boxer(
                            parts[0], parts[1], parts[2],
                            Integer.parseInt(parts[3]), parts[4],
                            parts[5], Double.parseDouble(parts[6]),
                            Double.parseDouble(parts[7]), Double.parseDouble(parts[8]),
                            parts[9], Boolean.parseBoolean(parts[10]), parts[11]
                    );
                    boxers.add(boxer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableView.setItems(boxers);
    }
}
