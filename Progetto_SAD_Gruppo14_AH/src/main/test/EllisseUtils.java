import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class EllisseUtils {
    public static Ellipse creaEllisse(double x1, double y1, double x2, double y2, Color stroke, Color fill) {
        double centerX = (x1 + x2) / 2;
        double centerY = (y1 + y2) / 2;
        double radiusX = Math.abs(x2 - x1) / 2;
        double radiusY = Math.abs(y2 - y1) / 2;

        Ellipse e = new Ellipse(centerX, centerY, radiusX, radiusY);
        e.setStroke(stroke);
        e.setFill(fill);
        return e;
    }
}

