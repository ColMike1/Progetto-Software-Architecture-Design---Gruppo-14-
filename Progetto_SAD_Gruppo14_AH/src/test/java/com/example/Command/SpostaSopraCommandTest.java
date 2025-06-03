package com.example.Command;

import com.example.Command.SpostaSopraCommand;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SpostaSopraCommandTest {

    // Mock del modello LavagnaModel su cui opera il comando
    private LavagnaModel lavagnaModel;
    // Mock della figura da spostare
    private Figura figura;
    // Istanza del comando che stiamo testando
    private SpostaSopraCommand command;

    @BeforeEach
    void setUp() {
        // Inizializza i mock prima di ogni test
        lavagnaModel = mock(LavagnaModel.class);
        figura = mock(Figura.class);
        // Crea il comando con i mock appena creati
        command = new SpostaSopraCommand(lavagnaModel, figura);
    }

    @Test
    void testExecuteChiamaSpostaSopra() {
        // Esegue il comando
        command.execute();
        // Verifica che il metodo spostaSopra sul modello venga chiamato una volta con la figura giusta
        verify(lavagnaModel, times(1)).spostaSopra(figura);
    }

    @Test
    void testUndoChiamaSpostaSotto() {
        // Esegue l'undo del comando
        command.undo();
        // Verifica che il metodo spostaSotto sul modello venga chiamato una volta con la figura giusta
        verify(lavagnaModel, times(1)).spostaSotto(figura);
    }

    @Test
    void testCanExecuteTrueSeNonUltimo() {
        // Crea una seconda figura mockata per la lista
        Figura altraFigura = mock(Figura.class);
        // Costruisce una lista in cui figura NON è l'ultimo elemento
        List<Figura> lista = Arrays.asList(figura, altraFigura);
        // Mocka il metodo getFigure per restituire questa lista
        when(lavagnaModel.getFigure()).thenReturn(lista);

        // Controlla che canExecute ritorni true perché figura non è l'ultima della lista
        assertTrue(command.canExecute());
    }

    @Test
    void testCanExecuteFalseSeUltimo() {
        // Crea una seconda figura mockata per la lista
        Figura altraFigura = mock(Figura.class);
        // Costruisce una lista in cui figura È l'ultimo elemento
        List<Figura> lista = Arrays.asList(altraFigura, figura);
        // Mocka il metodo getFigure per restituire questa lista
        when(lavagnaModel.getFigure()).thenReturn(lista);

        // Controlla che canExecute ritorni false perché figura è già l'ultimo nella lista
        assertFalse(command.canExecute());
    }

    @Test
    void testIsUndoable() {
        // Verifica che il comando supporta l'undo
        assertTrue(command.isUndoable());
    }
}
