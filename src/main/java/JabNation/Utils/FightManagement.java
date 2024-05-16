package JabNation.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FightManagement {
    private static final String FIGHTS_FILE_PATH = "fights.txt";

    public static void saveFight(String boxer1Name, String boxer2Name) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FIGHTS_FILE_PATH, true))) {
            System.out.println("Saving fight: " + boxer1Name + " vs " + boxer2Name);
            writer.write(boxer1Name + "," + boxer2Name);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("An error occurred while saving the fight:");
            e.printStackTrace();
        }
    }
}