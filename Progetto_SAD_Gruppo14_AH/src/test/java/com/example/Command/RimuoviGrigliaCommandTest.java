package com.example.Command;

import com.example.View.LavagnaView;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

class RimuoviGrigliaCommandTest {

    @Test
    void testExecute() {
        // Creo il mock della LavagnaView
        LavagnaView lavagnaViewMock = mock(LavagnaView.class);

        // Uso il mockStatic per LavagnaView
        try (MockedStatic<LavagnaView> lavagnaViewStatic = mockStatic(LavagnaView.class)) {

            // Quando qualcuno chiama getInstance() restituiamo il mock
            lavagnaViewStatic.when(LavagnaView::getInstance).thenReturn(lavagnaViewMock);

            // Creo il comando da testare
            RimuoviGrigliaCommand command = new RimuoviGrigliaCommand();

            // Eseguo il comando
            command.execute();

            // Verifico che sia stato chiamato rimuoviGriglia()
            verify(lavagnaViewMock).rimuoviGriglia();
        }
    }
}
