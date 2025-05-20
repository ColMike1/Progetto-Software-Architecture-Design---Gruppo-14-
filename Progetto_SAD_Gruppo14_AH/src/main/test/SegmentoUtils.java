import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class SegmentoUtils {
    public static Line creaSegmento(double x1, double y1, double x2, double y2, Color stroke) {
        Line l = new Line(x1, y1, x2, y2);
        l.setStroke(stroke);
        return l;
    }
}
