import com.example.Model.Rettangolo;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RettangoloTest {

    @Test
    void testCostruttoreConCoordinateNormali() {
        Rettangolo rett = new Rettangolo(10, 20, 30, 40, Color.BLACK, Color.GREEN);

        assertEquals(10, rett.getX1());
        assertEquals(20, rett.getY1());
        assertEquals(30, rett.getX2());
        assertEquals(40, rett.getY2());
        assertEquals(Color.BLACK, rett.getStrokeColor());
        assertEquals(Color.GREEN, rett.getFillColor());
    }

    @Test
    void testCostruttoreConCoordinateInversa() {
        Rettangolo rett = new Rettangolo(50, 60, 20, 10, Color.RED, Color.YELLOW);

        // X1, Y1 vengono riordinati internamente con Math.min()
        assertEquals(20, rett.getX1());
        assertEquals(10, rett.getY1());
        assertEquals(20, rett.getX2());
        assertEquals(10, rett.getY2());
    }

    @Test
    void testCreaNodoJavaFX() {
        Rettangolo rett = new Rettangolo(100, 150, 200, 250, Color.BLUE, Color.PINK);
        Rectangle node = rett.creaNodoJavaFX();

        assertEquals(100, node.getX());
        assertEquals(150, node.getY());
        assertEquals(100, node.getWidth());
        assertEquals(100, node.getHeight());
        assertEquals(Color.BLUE, node.getStroke());
        assertEquals(Color.PINK, node.getFill());
        assertEquals(3, node.getStrokeWidth());
        assertEquals(rett, node.getUserData()); // Verifica che venga salvata la figura come metadato
    }
}

