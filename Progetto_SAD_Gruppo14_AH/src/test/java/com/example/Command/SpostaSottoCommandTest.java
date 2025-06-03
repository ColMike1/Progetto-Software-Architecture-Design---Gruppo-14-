

import com.example.Command.SpostaSottoCommand;

import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpostaSottoCommandTest {

    // Mock del modello LavagnaModel
    private LavagnaModel lavagnaModel;
    // Mock della figura su cui agisce il comando
    private Figura figura;
    // Istanza del comando da testare
    private SpostaSottoCommand command;

    @BeforeEach
    void setUp() {
        // Inizializza i mock
        lavagnaModel = mock(LavagnaModel.class);
        figura = mock(Figura.class);
        // Crea il comando usando i mock
        command = new SpostaSottoCommand(lavagnaModel, figura);
    }

    @Test
    void testExecuteChiamaSpostaSotto() {
        // Esegue il comando
        command.execute();
        // Verifica che il metodo spostaSotto della lavagna venga chiamato esattamente 1 volta con la figura
        verify(lavagnaModel, times(1)).spostaSotto(figura);
    }

    @Test
    void testUndoChiamaSpostaSopra() {
        // Chiama undo sul comando
        command.undo();
        // Verifica che undo chiami spostaSopra sulla lavagna con la figura
        verify(lavagnaModel, times(1)).spostaSopra(figura);
    }

    @Test
    void testCanExecuteTrueSeNonPrimo() {
        // Simula una lista di figure dove la figura non è la prima (indice != 0)
        Figura altraFigura = mock(Figura.class);
        List<Figura> lista = Arrays.asList(altraFigura, figura);
        when(lavagnaModel.getFigure()).thenReturn(lista);

        // canExecute deve restituire true in questo caso (può essere spostata in fondo)
        assertTrue(command.canExecute());
    }

    @Test
    void testCanExecuteFalseSePrimo() {
        // Simula una lista dove la figura è la prima (indice == 0)
        Figura altraFigura = mock(Figura.class);
        List<Figura> lista = Arrays.asList(figura, altraFigura);
        when(lavagnaModel.getFigure()).thenReturn(lista);

        // canExecute deve restituire false perché la figura è già in fondo
        assertFalse(command.canExecute());
    }

    @Test
    void testIsUndoable() {
        // Verifica che il comando supporta l'undo
        assertTrue(command.isUndoable());
    }
}