package com.example.Command;

import static org.mockito.Mockito.*;

import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import com.example.State.FiguraSelezionataManager;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

class EliminaCommandTest {

    private LavagnaModel lavagnaModel;
    private Figura figura;
    private EliminaCommand eliminaCommand;

    private MockedStatic<FiguraSelezionataManager> figuraSelezionataManagerStaticMock;
    private FiguraSelezionataManager figuraSelezionataManagerMock;

    @BeforeEach
    void setUp() {
        // Mock delle dipendenze
        lavagnaModel = mock(LavagnaModel.class);
        figura = mock(Figura.class);

        // Mock statico del singleton FiguraSelezionataManager
        figuraSelezionataManagerMock = mock(FiguraSelezionataManager.class);
        figuraSelezionataManagerStaticMock = mockStatic(FiguraSelezionataManager.class);
        figuraSelezionataManagerStaticMock.when(FiguraSelezionataManager::getInstance)
                .thenReturn(figuraSelezionataManagerMock);

        eliminaCommand = new EliminaCommand(lavagnaModel, figura);
    }

    @AfterEach
    void tearDown() {
        figuraSelezionataManagerStaticMock.close();
    }

    @Test
    void testExecute() {
        eliminaCommand.execute();

        // Verifica che venga chiamato clear sul manager
        verify(figuraSelezionataManagerMock).clear();

        // Verifica che venga chiamato rimuoviFigura su lavagnaModel con la figura
        verify(lavagnaModel).rimuoviFigura(figura);
    }

    @Test
    void testUndo() {
        eliminaCommand.undo();

        // Verifica che venga chiamato aggiungiFigura su lavagnaModel con la figura
        verify(lavagnaModel).aggiungiFigura(figura);
    }

    @Test
    void testIsUndoable() {
        Assertions.assertTrue(eliminaCommand.isUndoable());
    }
}
