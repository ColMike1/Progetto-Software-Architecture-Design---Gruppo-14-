import com.example.Command.CambiaColoreInternoCommand;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CambiaColoreInternoTest {

    @Test
    void testCambiaColoreInternoExecuteEUndo() {
        // Setup
        LavagnaModel lavagnaModel = new LavagnaModel();
        Figura figura = new FiguraFinta(0, 0, 100, 100, Color.BLACK, Color.RED);
        lavagnaModel.getFigure().add(figura);

        // Comando per cambiare l'interno da ROSSO a BLU
        CambiaColoreInternoCommand cmd = new CambiaColoreInternoCommand(lavagnaModel, figura, Color.BLUE);

        // Execute: L'interno deve diventare blu
        cmd.execute();
        assertEquals(Color.BLUE, figura.getFillColor(), "Dopo execute il colore interno deve essere BLU");

        // Undo: il bordo deve tornare nero
        cmd.undo();
        assertEquals(Color.RED, figura.getFillColor(), "Dopo undo il colore del bordo deve tornare ROSSO");
    }
}