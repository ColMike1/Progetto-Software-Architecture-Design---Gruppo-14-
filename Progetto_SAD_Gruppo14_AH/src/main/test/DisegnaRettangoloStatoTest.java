/*import com.example.Command.AggiungiFiguraCommand;
import com.example.Command.Command;
import com.example.Factory.RettangoloFactory;
import com.example.Model.LavagnaModel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DisegnaRettangoloStatoTest {

    @Test
    void testCreaRettangoloDaCoordinate() {
        Rectangle r = RettangoloUtils.creaRettangolo(100, 50, 150, 100, Color.BLACK, Color.RED);

        assertEquals(100, r.getX());
        assertEquals(50, r.getY());
        assertEquals(50, r.getWidth());
        assertEquals(50, r.getHeight());
        assertEquals(Color.BLACK, r.getStroke());
        assertEquals(Color.RED, r.getFill());
    }

    @Test
    void testCreaRettangoloCoordinateInvertite() {
        Rectangle r = RettangoloUtils.creaRettangolo(150, 100, 100, 50, Color.BLUE, Color.GREEN);

        assertEquals(100, r.getX());
        assertEquals(50, r.getY());
        assertEquals(50, r.getWidth());
        assertEquals(50, r.getHeight());
    }

    @Test
    void testAggiuntaFiguraAlModel() {
        LavagnaModel model = new LavagnaModel(); // deve essere testabile
        RettangoloFactory factory = new RettangoloFactory();
        Command cmd = new AggiungiFiguraCommand(model, factory, 10, 20, 30, 40, Color.BLACK, Color.BLUE);

        cmd.execute();

        assertEquals(1, model.getFigure().size());
    }

}
*/