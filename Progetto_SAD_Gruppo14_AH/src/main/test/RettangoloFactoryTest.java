import com.example.Factory.RettangoloFactory;
import com.example.Model.Figura;
import com.example.Model.Rettangolo;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RettangoloFactoryTest {

    @Test
    void creaFigura_shouldReturnRettangoloInstance() {
        RettangoloFactory factory = new RettangoloFactory();
        Figura figura = factory.creaFigura(5, 10, 15, 20, Color.BLUE, Color.YELLOW);

        assertNotNull(figura);
        assertTrue(figura instanceof Rettangolo);

        // Controlla le coordinate
        assertEquals(5, figura.getX1());
        assertEquals(10, figura.getY1());
        assertEquals(15, figura.getX2());
        assertEquals(20, figura.getY2());

        // Controlla i colori
        assertEquals(Color.BLUE, figura.getStrokeColor());
        assertEquals(Color.YELLOW, figura.getFillColor());
    }
}
