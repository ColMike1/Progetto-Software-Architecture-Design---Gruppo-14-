import com.example.Model.Ellisse;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import org.junit.jupiter.api.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class EllisseTest {

    @BeforeAll
    static void initJavaFX() throws InterruptedException {
        // Inizializza JavaFX se non già avviato
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await(5, TimeUnit.SECONDS);
    }

    @Test
    void testCreaNodoJavaFX() {
        // Crea una nuova Ellisse
        double x1 = 100, y1 = 150, x2 = 200, y2 = 250;
        Color stroke = Color.BLUE;
        Color fill = Color.LIGHTGRAY;

        Ellisse ellisse = new Ellisse(x1, y1, x2, y2, stroke, fill);

        // Crea il nodo JavaFX
        Node nodo = ellisse.creaNodoJavaFX();

        assertNotNull(nodo);
        assertInstanceOf(Ellipse.class, nodo);

        Ellipse e = (Ellipse) nodo;

        // Verifica centro e raggi
        assertEquals(150, e.getCenterX());
        assertEquals(200, e.getCenterY());
        assertEquals(50, e.getRadiusX());
        assertEquals(50, e.getRadiusY());

        // Verifica colori e spessore
        assertEquals(stroke, e.getStroke());
        assertEquals(fill, e.getFill());
        assertEquals(3.0, e.getStrokeWidth());

        // Verifica UserData
        assertEquals(ellisse, e.getUserData());
    }
}
