package JabNation.Utils;

import JabNation.Boxer.Boxer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.*;

public class DataManagement {
    public static void updateBoxersList(TableView<Boxer> tableView, ObservableList<Boxer> boxers) {
        tableView.setItems(boxers);
    }

    public static ObservableList<Boxer> boxersList = FXCollections.observableArrayList();

    private static final String BOXERS_FILE_PATH = "boxers.txt";

    public static ObservableList<Boxer> loadBoxersFromFile() {
        ObservableList<Boxer> boxers = FXCollections.observableArrayList();
        ObservableList<Boxer> tempBoxers = FXCollections.observableArrayList();
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
                    tempBoxers.add(boxer);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
            boxers.addAll(tempBoxers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return boxers;
    }


    public static void saveBoxersToFile(ObservableList<Boxer> boxers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOXERS_FILE_PATH, true))) {
            for (Boxer boxer : boxers) {
                writer.write(
                        boxer.getName() + "," +
                                boxer.getNickname() + "," +
                                boxer.getSurname() + "," +
                                boxer.getAge() + "," +
                                boxer.getRecord() + "," +
                                boxer.getDivision() + "," +
                                boxer.getWeight() + "," +
                                boxer.getHeight() + "," +
                                boxer.getReach() + "," +
                                boxer.getNation() + "," +
                                (boxer.isActive() ? "true" : "false") + "," +
                                boxer.getPhotoPath());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
