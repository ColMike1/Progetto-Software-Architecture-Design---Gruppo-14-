import com.example.Command.AggiungiFiguraCommand;
import com.example.Command.Command;
import com.example.Factory.EllisseFactory;
import com.example.Model.LavagnaModel;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DisegnaEllisseStatoTest {

    @Test
    void testCreaEllisse() {
        Ellipse e = EllisseUtils.creaEllisse(0, 0, 100, 50, Color.BLACK, Color.RED);

        assertEquals(50, e.getCenterX());
        assertEquals(25, e.getCenterY());
        assertEquals(50, e.getRadiusX());
        assertEquals(25, e.getRadiusY());
        assertEquals(Color.BLACK, e.getStroke());
        assertEquals(Color.RED, e.getFill());
    }

    @Test
    void testAggiuntaFiguraAlModel() {
        LavagnaModel model = new LavagnaModel(); // deve essere testabile
        EllisseFactory factory = new EllisseFactory();
        Command cmd = new AggiungiFiguraCommand(model, factory, 10, 20, 30, 40, Color.BLACK, Color.BLUE);

        cmd.execute();

        assertEquals(1, model.getFigure().size());
    }


}
