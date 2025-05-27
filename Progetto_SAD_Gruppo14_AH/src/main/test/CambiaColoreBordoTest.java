import com.example.Command.CambiaColoreBordoCommand;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CambiaColoreBordoTest {

    @Test
    void testCambiaColoreBordoExecuteEUndo() {
        // Setup
        LavagnaModel lavagnaModel = new LavagnaModel();
        Figura figura = new FiguraFinta(0, 0, 100, 100, Color.BLACK, Color.RED);
        lavagnaModel.getFigure().add(figura);

        // Comando per cambiare il bordo da NERO a BLU
        CambiaColoreBordoCommand cmd = new CambiaColoreBordoCommand(lavagnaModel, figura, Color.BLUE);

        // Execute: il bordo deve diventare blu
        cmd.execute();
        assertEquals(Color.BLUE, figura.getStrokeColor(), "Dopo execute il colore del bordo deve essere BLU");

        // Undo: il bordo deve tornare nero
        cmd.undo();
        assertEquals(Color.BLACK, figura.getStrokeColor(), "Dopo undo il colore del bordo deve tornare NERO");
    }
}
