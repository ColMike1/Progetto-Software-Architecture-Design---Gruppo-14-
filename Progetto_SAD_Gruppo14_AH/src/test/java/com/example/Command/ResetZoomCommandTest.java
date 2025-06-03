package com.example.Command;

import com.example.View.LavagnaView;
import javafx.scene.Group;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ResetZoomCommandTest {

    @Test
    void testExecute() {
        // Mock della LavagnaView e del Group
        LavagnaView lavagnaViewMock = mock(LavagnaView.class);
        Group figureInseriteMock = mock(Group.class);

        // Quando getFigureZoomabili() viene chiamato, restituiamo il mock del Group
        when(lavagnaViewMock.getFigureZoomabili()).thenReturn(figureInseriteMock);

        // Creo il comando da testare
        ResetZoomCommand command = new ResetZoomCommand(lavagnaViewMock);

        // Eseguo il comando
        command.execute();

        // Verifico le chiamate effettuate
        verify(figureInseriteMock).setScaleX(1.0);
        verify(figureInseriteMock).setScaleY(1.0);
        verify(figureInseriteMock).setTranslateX(0);
        verify(figureInseriteMock).setTranslateY(0);
    }
}
