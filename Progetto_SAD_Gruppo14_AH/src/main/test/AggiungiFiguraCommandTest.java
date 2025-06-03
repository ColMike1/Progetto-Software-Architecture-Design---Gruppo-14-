/*import com.example.Command.AggiungiFiguraCommand;
import com.example.Factory.FiguraFactory;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;

public class AggiungiFiguraCommandTest {

    private LavagnaModel mockModel;
    private FiguraFactory mockFactory;
    private AnchorPane mockLavagna;
    private Figura mockFigura;
    private AggiungiFiguraCommand command;

    @BeforeEach
    void setUp() {
        mockModel = mock(LavagnaModel.class);
        mockFactory = mock(FiguraFactory.class);
        mockLavagna = mock(AnchorPane.class);
        mockFigura = mock(Figura.class);

        // Simuliamo che la factory restituisca la figura finta
        when(mockFactory.creaFigura(anyDouble(), anyDouble(), anyDouble(), anyDouble(), any(), any()))
                .thenReturn(mockFigura);

        // Simuliamo dimensioni iniziali della lavagna
        when(mockLavagna.getPrefWidth()).thenReturn(100.0);
        when(mockLavagna.getPrefHeight()).thenReturn(100.0);

        command = new AggiungiFiguraCommand(
                mockModel, mockFactory, mockLavagna,
                10, 20, 300, 400, Color.BLACK, Color.RED
        );
    }

    @Test
    void testExecuteAggiungeFiguraENonRidimensiona() {
        when(mockLavagna.getPrefWidth()).thenReturn(500.0);
        when(mockLavagna.getPrefHeight()).thenReturn(500.0);

        command.execute();

        // verifica che sia stata creata la figura
        verify(mockFactory).creaFigura(10, 20, 300, 400, Color.BLACK, Color.RED);
        // verifica che sia stata aggiunta al model
        verify(mockModel).aggiungiFigura(mockFigura);

        // verifica che NON abbia ridimensionato lavagna (perché già abbastanza grande)
        verify(mockLavagna, never()).setPrefWidth(anyDouble());
        verify(mockLavagna, never()).setPrefHeight(anyDouble());
    }

    @Test
    void testExecuteRidimensionaLavagna() {
        // caso in cui lavagna è troppo piccola
        when(mockLavagna.getPrefWidth()).thenReturn(200.0);  // serve almeno 300
        when(mockLavagna.getPrefHeight()).thenReturn(200.0); // serve almeno 400

        command.execute();

        // verifica che la lavagna sia stata ridimensionata
        verify(mockLavagna).setPrefWidth(300 + 100); // MaxX = 300
        verify(mockLavagna).setPrefHeight(400 + 100);
    }

    @Test
    void testUndoRimuoveFigura() {
        // esegui prima execute per assegnare figuraAggiunta
        command.execute();

        command.undo();

        // verifica che rimuova la figura dal model
        verify(mockModel).rimuoviFigura(mockFigura);
    }
}
*/