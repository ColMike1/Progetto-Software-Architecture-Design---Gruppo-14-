import com.example.Model.Figura;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FiguraFinta extends Figura {

    public FiguraFinta(double x1, double y1, double x2, double y2, Color strokeColor, Color fillColor) {
        super(x1, y1, x2, y2, strokeColor, fillColor);
    }

    @Override
    public Node creaNodoJavaFX() {
        // Nodo finto per test (non verrà usato)
        return new Rectangle();
    }
}
