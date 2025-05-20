import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RettangoloUtils {
    public static Rectangle creaRettangolo(double x1, double y1, double x2, double y2, Color stroke, Color fill) {
        Rectangle r = new Rectangle();
        r.setX(Math.min(x1, x2));
        r.setY(Math.min(y1, y2));
        r.setWidth(Math.abs(x2 - x1));
        r.setHeight(Math.abs(y2 - y1));
        r.setStroke(stroke);
        r.setFill(fill);
        return r;
    }
}
