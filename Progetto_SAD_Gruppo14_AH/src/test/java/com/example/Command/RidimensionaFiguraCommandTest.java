package com.example.Command;

import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import com.example.State.FiguraSelezionataManager;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

class RidimensionaFiguraCommandTest {

    @Test
    void testExecute() {
        // Creo i mock
        Figura figuraMock = mock(Figura.class);
        LavagnaModel lavagnaModelMock = mock(LavagnaModel.class);
        FiguraSelezionataManager figuraSelezionataManagerMock = mock(FiguraSelezionataManager.class);

        // Mock dei metodi statici dentro try-with-resources
        try (MockedStatic<FiguraSelezionataManager> figuraSelezionataManagerStatic = mockStatic(FiguraSelezionataManager.class);
             MockedStatic<LavagnaModel> lavagnaModelStatic = mockStatic(LavagnaModel.class)) {

            // Configuro i singleton per restituire i mock
            figuraSelezionataManagerStatic.when(FiguraSelezionataManager::getInstance).thenReturn(figuraSelezionataManagerMock);
            when(figuraSelezionataManagerMock.get()).thenReturn(figuraMock);

            lavagnaModelStatic.when(LavagnaModel::getInstance).thenReturn(lavagnaModelMock);

            // Creo il comando da testare
            RidimensionaFiguraCommand command = new RidimensionaFiguraCommand(100, 200, 50, 60);

            // Eseguo il comando
            command.execute();

            // Verifico che il model abbia ricevuto la chiamata corretta
            verify(lavagnaModelMock).ridimensionaFigura(figuraMock, 100, 200);
        }
    }

    @Test
    void testUndo() {
        // Creo i mock
        Figura figuraMock = mock(Figura.class);
        LavagnaModel lavagnaModelMock = mock(LavagnaModel.class);
        FiguraSelezionataManager figuraSelezionataManagerMock = mock(FiguraSelezionataManager.class);

        // Mock dei metodi statici dentro try-with-resources
        try (MockedStatic<FiguraSelezionataManager> figuraSelezionataManagerStatic = mockStatic(FiguraSelezionataManager.class);
             MockedStatic<LavagnaModel> lavagnaModelStatic = mockStatic(LavagnaModel.class)) {

            // Configuro i singleton per restituire i mock
            figuraSelezionataManagerStatic.when(FiguraSelezionataManager::getInstance).thenReturn(figuraSelezionataManagerMock);
            when(figuraSelezionataManagerMock.get()).thenReturn(figuraMock);

            lavagnaModelStatic.when(LavagnaModel::getInstance).thenReturn(lavagnaModelMock);

            // Creo il comando da testare
            RidimensionaFiguraCommand command = new RidimensionaFiguraCommand(100, 200, 50, 60);

            // Eseguo l'undo
            command.undo();

            // Verifico che il manager abbia fatto clear
            verify(figuraSelezionataManagerMock).clear();

            // Verifico che il model abbia ricevuto la chiamata per il rollback
            verify(lavagnaModelMock).ridimensionaFigura(figuraMock, 50, 60);
        }
    }
}
