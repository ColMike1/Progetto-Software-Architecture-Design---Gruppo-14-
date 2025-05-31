/*import com.example.Command.AggiungiFiguraCommand;
import com.example.Factory.FiguraFactory;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import javafx.scene.paint.Color;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AggiungiFiguraTest {

    @Test
    void testAggiungiFigura() {

        LavagnaModel lavagnaModel = new LavagnaModel();
        FiguraFactory fakeFactory = (x1, y1, x2, y2, stroke, fill) -> new FiguraFinta(x1, y1, x2, y2, stroke, fill);


        AggiungiFiguraCommand cmd = new AggiungiFiguraCommand(
                lavagnaModel, fakeFactory,
                10, 20, 30, 40,
                Color.BLACK, Color.RED
        );

        cmd.execute();

        // Verifica che la figura sia stata aggiunta correttamente
        assertEquals(1, lavagnaModel.getFigure().size());
        Figura figura = lavagnaModel.getFigure().get(0);
        assertEquals(10, figura.getX1());
        assertEquals(20, figura.getY1());
        assertEquals(30, figura.getX2());
        assertEquals(40, figura.getY2());
        assertEquals(Color.BLACK, figura.getStrokeColor());
        assertEquals(Color.RED, figura.getFillColor());
    }

}
*/