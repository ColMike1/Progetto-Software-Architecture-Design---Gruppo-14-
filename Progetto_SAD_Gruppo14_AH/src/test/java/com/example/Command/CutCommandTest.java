package com.example.Command;

import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import com.example.State.FiguraSelezionataManager;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

class CutCommandTest {

    @Test
    void testExecute() {
        // Creo i mock
        LavagnaModel lavagnaModelMock = mock(LavagnaModel.class);
        Figura figuraMock = mock(Figura.class);
        FiguraSelezionataManager figuraSelezionataManagerMock = mock(FiguraSelezionataManager.class);

        // Mock dei metodi statici per FiguraSelezionataManager
        try (MockedStatic<FiguraSelezionataManager> figuraSelezionataManagerStatic = mockStatic(FiguraSelezionataManager.class)) {

            // Configuro il singleton per restituire il mock
            figuraSelezionataManagerStatic.when(FiguraSelezionataManager::getInstance).thenReturn(figuraSelezionataManagerMock);

            // Creo il comando da testare
            CutCommand command = new CutCommand(lavagnaModelMock, figuraMock);

            // Eseguo il comando
            command.execute();

            // Verifico le interazioni
            verify(figuraSelezionataManagerMock).clear();
            verify(lavagnaModelMock).setFiguraCopiata(figuraMock);
            verify(lavagnaModelMock).rimuoviFigura(figuraMock);
        }
    }
}
