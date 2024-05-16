package JabNation.Filters;

import JabNation.Boxer.Boxer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.controlsfx.control.RangeSlider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ApplyFilters {

    private static final String BOXERS_FILE_PATH = "boxers.txt";

    public static ObservableList<Boxer> loadBoxersFromFile() {
        ObservableList<Boxer> boxers = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(BOXERS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 12) {
                    Boxer boxer = new Boxer(
                            parts[0], parts[1], parts[2],
                            Integer.parseInt(parts[3]), parts[4],
                            parts[5], Double.parseDouble(parts[6]),
                            Double.parseDouble(parts[7]), Double.parseDouble(parts[8]),
                            parts[9], Boolean.parseBoolean(parts[10]), parts[11]
                    );
                    boxers.add(boxer);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return boxers;
    }

    public static void applyFilters(ObservableList<Boxer> boxers, TableView<Boxer> tableView,
                                    RangeSlider heightRangeSlider, RangeSlider reachRangeSlider, RangeSlider ageRangeSlider) {
        boxers = loadBoxersFromFile();
        tableView.getItems().clear();

        for (Boxer boxer : boxers) {
            double height = boxer.getHeight();
            double reach = boxer.getReach();
            int age = boxer.getAge();
            boolean isActive = boxer.isActive();

            boolean heightInRange = height >= heightRangeSlider.getLowValue() && height <= heightRangeSlider.getHighValue();
            boolean reachInRange = reach >= reachRangeSlider.getLowValue() && reach <= reachRangeSlider.getHighValue();
            boolean ageInRange = age >= ageRangeSlider.getLowValue() && age <= ageRangeSlider.getHighValue();

            if (heightInRange && reachInRange && ageInRange && isActive) {
                tableView.getItems().add(boxer);
            }
        }
    }
}
