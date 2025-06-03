package com.example.Command;

import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import com.example.State.FiguraSelezionataManager;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

class SpostamentoFiguraCommandTest {

    @Test
    void testExecute() {
        // Creo i mock
        Figura figuraMock = mock(Figura.class);
        LavagnaModel lavagnaModelMock = mock(LavagnaModel.class);

        try (MockedStatic<LavagnaModel> lavagnaModelStatic = mockStatic(LavagnaModel.class)) {
            // Configuro il singleton LavagnaModel
            lavagnaModelStatic.when(LavagnaModel::getInstance).thenReturn(lavagnaModelMock);

            // Creo il comando da testare
            SpostamentoFiguraCommand command = new SpostamentoFiguraCommand(figuraMock, 100, 200, 50, 60);

            // Eseguo il comando
            command.execute();

            // Verifico che abbia chiamato spostaFigura() con i nuovi valori
            verify(lavagnaModelMock).spostaFigura(figuraMock, 100, 200);
        }
    }

    @Test
    void testUndo() {
        // Creo i mock
        Figura figuraMock = mock(Figura.class);
        Figura figuraSelezionataMock = mock(Figura.class);
        LavagnaModel lavagnaModelMock = mock(LavagnaModel.class);
        FiguraSelezionataManager figuraSelezionataManagerMock = mock(FiguraSelezionataManager.class);

        try (MockedStatic<LavagnaModel> lavagnaModelStatic = mockStatic(LavagnaModel.class);
             MockedStatic<FiguraSelezionataManager> figuraSelezionataManagerStatic = mockStatic(FiguraSelezionataManager.class)) {

            // Configuro i singleton
            lavagnaModelStatic.when(LavagnaModel::getInstance).thenReturn(lavagnaModelMock);
            figuraSelezionataManagerStatic.when(FiguraSelezionataManager::getInstance).thenReturn(figuraSelezionataManagerMock);
            when(figuraSelezionataManagerMock.get()).thenReturn(figuraSelezionataMock);

            // Creo il comando da testare
            SpostamentoFiguraCommand command = new SpostamentoFiguraCommand(figuraMock, 100, 200, 50, 60);

            // Eseguo undo
            command.undo();

            // Verifico che abbia fatto clear()
            verify(figuraSelezionataManagerMock).clear();

            // Verifico che abbia spostato la figura selezionata alle coordinate originali
            verify(lavagnaModelMock).spostaFigura(figuraSelezionataMock, 50, 60);
        }
    }
}
