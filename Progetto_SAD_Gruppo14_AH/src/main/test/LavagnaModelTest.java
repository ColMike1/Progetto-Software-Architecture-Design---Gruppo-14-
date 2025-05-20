import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import com.example.Model.Rettangolo;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LavagnaModelTest {

    private LavagnaModel model;

    @BeforeEach
    public void setup() {
        model = LavagnaModel.getInstance();
        // Pulisco lo stato interno per partire "puliti"
        model.getFigure().clear();
    }

    @Test
    public void testAggiungiFigura() {
        Figura rettangolo = new Rettangolo(0, 0, 10, 10, Color.BLACK, Color.RED);
        model.aggiungiFigura(rettangolo);

        assertEquals(1, model.getFigure().size());
        assertSame(rettangolo, model.getFigure().get(0));
    }

    @Test
    public void testAggiungiMolteFigure() {
        model.aggiungiFigura(new Rettangolo(0, 0, 10, 10, Color.BLACK, Color.RED));
        model.aggiungiFigura(new Rettangolo(10, 10, 20, 20, Color.BLUE, Color.GREEN));

        assertEquals(2, model.getFigure().size());
    }

    @Test
    public void testNotificaOsservatori() {
        final boolean[] osservato = {false};
        model.aggiungiOsservatore(() -> osservato[0] = true);

        model.aggiungiFigura(new Rettangolo(0, 0, 10, 10, Color.BLACK, Color.RED));

        assertTrue(osservato[0]);
    }
}
