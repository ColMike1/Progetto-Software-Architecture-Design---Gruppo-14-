package com.example.Command;

import com.example.Model.Griglia;
import com.example.View.LavagnaView;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

class AggiungiGrigliaCommandTest {

    @Test
    void testExecute() {
        // Creo il mock di LavagnaView
        LavagnaView lavagnaViewMock = mock(LavagnaView.class);

        try (MockedStatic<LavagnaView> lavagnaViewStatic = mockStatic(LavagnaView.class)) {

            // Configuro il singleton per restituire il mock
            lavagnaViewStatic.when(LavagnaView::getInstance).thenReturn(lavagnaViewMock);

            // Creo il comando da testare
            AggiungiGrigliaCommand command = new AggiungiGrigliaCommand(10, 15, 500, 400, Color.BLACK);

            // Eseguo il comando
            command.execute();

            // Verifico che sia stato chiamato aggiungiGriglia() con una qualsiasi Griglia
            verify(lavagnaViewMock).aggiungiGriglia(any(Griglia.class));
        }
    }
}
