import com.example.Command.SpostaSottoCommand;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpostaSottoCommandTest {

    @Test
    void testExecuteAndUndo() {
        // Setup
        LavagnaModel lavagna = new LavagnaModel();
        Figura f1 = new FiguraFinta(0, 0, 10, 10, Color.BLACK, Color.RED);
        Figura f2 = new FiguraFinta(0, 0, 10, 10, Color.BLACK, Color.GREEN);
        lavagna.getFigure().add(f1); // index 0
        lavagna.getFigure().add(f2); // index 1

        // Comando per spostare f2 sotto (quindi va all'index 0)
        SpostaSottoCommand cmd = new SpostaSottoCommand(lavagna, f2);

        assertTrue(cmd.canExecute(), "canExecute() dovrebbe essere true perché f2 non è in fondo");

        // Execute: f2 dovrebbe andare sotto f1
        cmd.execute();
        assertEquals(f2, lavagna.getFigure().get(0), "Dopo execute, f2 dovrebbe essere sotto f1");
        assertEquals(f1, lavagna.getFigure().get(1));

        // Undo: f2 dovrebbe tornare sopra f1
        cmd.undo();
        assertEquals(f1, lavagna.getFigure().get(0), "Dopo undo, f1 dovrebbe tornare sotto f2");
        assertEquals(f2, lavagna.getFigure().get(1));
    }

    @Test
    void testCanExecuteWhenAtBottom() {
        // Setup
        LavagnaModel lavagna = new LavagnaModel();
        Figura f1 = new FiguraFinta(0, 0, 10, 10, Color.BLACK, Color.RED);
        Figura f2 = new FiguraFinta(0, 0, 10, 10, Color.BLACK, Color.GREEN);
        lavagna.getFigure().add(f1); // index 0 (in fondo)
        lavagna.getFigure().add(f2); // index 1

        // Proviamo a spostare f1 ancora più giù → impossibile
        SpostaSottoCommand cmd = new SpostaSottoCommand(lavagna, f1);

        assertFalse(cmd.canExecute(), "canExecute() dovrebbe essere false perché f1 è già in fondo");
    }
}
