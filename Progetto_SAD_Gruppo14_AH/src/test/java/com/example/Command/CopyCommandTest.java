package com.example.Command;

import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CopyCommandTest {

    @Test
    void testExecute() {
        // Creo i mock
        LavagnaModel lavagnaModelMock = mock(LavagnaModel.class);
        Figura figuraMock = mock(Figura.class);

        // Creo il comando da testare
        CopyCommand command = new CopyCommand(lavagnaModelMock, figuraMock);

        // Eseguo il comando
        command.execute();

        // Verifico che sia stato chiamato setFiguraCopiata() con la figura corretta
        verify(lavagnaModelMock).setFiguraCopiata(figuraMock);
    }
}
