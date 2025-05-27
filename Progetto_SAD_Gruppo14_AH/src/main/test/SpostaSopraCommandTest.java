import com.example.Command.SpostaSopraCommand;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpostaSopraCommandTest {

    @Test
    void testExecuteAndUndo() {
        // Setup
        LavagnaModel lavagna = new LavagnaModel();
        Figura f1 = new FiguraFinta(0, 0, 10, 10, Color.BLACK, Color.RED);
        Figura f2 = new FiguraFinta(0, 0, 10, 10, Color.BLACK, Color.GREEN);
        lavagna.getFigure().add(f1); // index 0
        lavagna.getFigure().add(f2); // index 1

        // Comando per spostare f1 sopra (quindi index 1)
        SpostaSopraCommand cmd = new SpostaSopraCommand(lavagna, f1);

        assertTrue(cmd.canExecute(), "canExecute() dovrebbe essere true perché f1 non è in cima");

        // Execute: f1 dovrebbe andare sopra f2
        cmd.execute();
        assertEquals(f2, lavagna.getFigure().get(0));
        assertEquals(f1, lavagna.getFigure().get(1), "Dopo execute, f1 dovrebbe essere sopra f2");

        // Undo: f1 dovrebbe tornare sotto f2
        cmd.undo();
        assertEquals(f1, lavagna.getFigure().get(0), "Dopo undo, f1 dovrebbe tornare sotto f2");
        assertEquals(f2, lavagna.getFigure().get(1));
    }

    @Test
    void testCanExecuteWhenAtTop() {
        // Setup
        LavagnaModel lavagna = new LavagnaModel();
        Figura f1 = new FiguraFinta(0, 0, 10, 10, Color.BLACK, Color.RED);
        Figura f2 = new FiguraFinta(0, 0, 10, 10, Color.BLACK, Color.GREEN);
        lavagna.getFigure().add(f1);
        lavagna.getFigure().add(f2); // f2 è già in cima (ultima posizione)

        SpostaSopraCommand cmd = new SpostaSopraCommand(lavagna, f2);

        assertFalse(cmd.canExecute(), "canExecute() dovrebbe essere false perché f2 è già in cima");
    }
}