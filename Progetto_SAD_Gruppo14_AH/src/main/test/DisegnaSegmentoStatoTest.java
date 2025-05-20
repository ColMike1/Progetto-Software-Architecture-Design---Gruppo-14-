import com.example.Command.AggiungiFiguraCommand;
import com.example.Command.Command;
import com.example.Factory.SegmentoFactory;
import com.example.Model.LavagnaModel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DisegnaSegmentoStatoTest {

    @Test
    void testCreaSegmento() {
        Line l = SegmentoUtils.creaSegmento(0, 0, 100, 50, Color.BLACK);

        assertEquals(0, l.getStartX());
        assertEquals(0, l.getStartY());
        assertEquals(100, l.getEndX());
        assertEquals(50, l.getEndY());
        assertEquals(Color.BLACK, l.getStroke());
    }

    @Test
    void testSegmentoNegativo() {
        Line l = SegmentoUtils.creaSegmento(100, 100, 0, 0, Color.RED);

        assertEquals(100, l.getStartX());
        assertEquals(100, l.getStartY());
        assertEquals(0, l.getEndX());
        assertEquals(0, l.getEndY());
    }

    @Test
    void testAggiuntaSegmentoAlModel() {
        LavagnaModel model = new LavagnaModel(); // deve essere autonomo
        Command cmd = new AggiungiFiguraCommand(
                model, new SegmentoFactory(),
                10, 10, 50, 50,
                Color.BLACK, Color.TRANSPARENT // il fill è ignorato per Line
        );

        cmd.execute();
        assertEquals(1, model.getFigure().size());
    }


}
