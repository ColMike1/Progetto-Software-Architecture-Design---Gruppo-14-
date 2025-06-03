package com.example.Command;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import com.example.Command.RotazioneFiguraCommand;

class RotazioneFiguraCommandTest {

    private Figura figura;  // Mock della figura da ruotare
    private RotazioneFiguraCommand command;  // Comando da testare
    private MockedStatic<LavagnaModel> lavagnaModelMockedStatic;  // Mock statico per LavagnaModel.getInstance()
    private LavagnaModel lavagnaModelMock;  // Mock dell'istanza LavagnaModel

    @BeforeEach
    void setUp() {
        // Creo il mock dell'istanza LavagnaModel
        lavagnaModelMock = mock(LavagnaModel.class);

        // Mocko il metodo statico LavagnaModel.getInstance() per restituire il mock dell'istanza
        lavagnaModelMockedStatic = mockStatic(LavagnaModel.class);
        lavagnaModelMockedStatic.when(LavagnaModel::getInstance).thenReturn(lavagnaModelMock);

        // Creo il mock della figura e definisco il valore della rotazione iniziale (10.0)
        figura = mock(Figura.class);
        when(figura.getRotazione()).thenReturn(10.0);

        // Creo il comando da testare, con la figura e la nuova rotazione 45.0
        command = new RotazioneFiguraCommand(figura, 45.0);
    }

    @AfterEach
    void tearDown() {
        // Chiudo il mock statico per evitare interferenze con altri test
        lavagnaModelMockedStatic.close();
    }

    @Test
    void testExecuteChiamaCambiaRotazioneConNuovoAngolo() {
        // Eseguo il comando, che dovrebbe chiamare cambiaRotazione sul modello con la nuova rotazione
        command.execute();

        // Verifico che il metodo cambiaRotazione sia stato chiamato con la figura e la nuova rotazione 45.0
        verify(lavagnaModelMock).cambiaRotazione(figura, 45.0);
    }

    @Test
    void testUndoRipristinaVecchiaRotazione() {
        // Eseguo l'undo del comando, che dovrebbe ripristinare la rotazione originale (10.0)
        command.undo();

        // Verifico che cambiaRotazione sia stato chiamato con la rotazione originale
        verify(lavagnaModelMock).cambiaRotazione(figura, 10.0);
    }

    @Test
    void testIsUndoableRestituisceTrue() {
        // Verifico che il comando dichiari di essere undoable (può essere annullato)
        assertTrue(command.isUndoable());
    }
}