package view;

import javafx.scene.paint.Paint;

import java.util.Arrays;
import java.util.List;

public class PaintProvider {

    private static final List<Paint> paints = Arrays.asList(javafx.scene.paint.Color.RED,
            javafx.scene.paint.Color.BLUE, javafx.scene.paint.Color.BLACK, javafx.scene.paint.Color.GREEN,
            javafx.scene.paint.Color.ORANGE, javafx.scene.paint.Color.YELLOW, javafx.scene.paint.Color.BROWN,
            javafx.scene.paint.Color.PURPLE, javafx.scene.paint.Color.PINK, javafx.scene.paint.Color.CYAN,
            javafx.scene.paint.Color.GRAY, javafx.scene.paint.Color.OLIVE, javafx.scene.paint.Color.GOLD);

    public static Paint getPaint(int id) {
        return paints.get(id);
    }

}
