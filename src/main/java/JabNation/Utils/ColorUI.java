package JabNation.Utils;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ColorUI {
    public static GridPane createColoredBox(Color color) {
        GridPane box = new GridPane();
        box.setStyle("-fx-background-color: " + toRGBCode(color) + ";");
        return box;
    }

    public static String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
