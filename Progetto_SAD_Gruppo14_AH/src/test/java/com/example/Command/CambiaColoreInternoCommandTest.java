package com.example.Command;

import com.example.Command.CambiaColoreInternoCommand;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CambiaColoreInternoCommandTest {

    private LavagnaModel lavagnaModel; // Mock del modello Lavagna
    private Figura figura;             // Mock della figura da modificare
    private CambiaColoreInternoCommand command; // Comando da testare

    // Colore iniziale simulato (blue)
    private final Color coloreIniziale = Color.BLUE;
    // Nuovo colore da impostare (yellow)
    private final Color nuovoColore = Color.YELLOW;

    @BeforeEach
    void setUp() {
        // Creo il mock del modello Lavagna
        lavagnaModel = mock(LavagnaModel.class);
        // Creo il mock della figura
        figura = mock(Figura.class);

        // Quando viene chiamato getFillColor() sulla figura, ritorna il colore iniziale
        when(figura.getFillColor()).thenReturn(coloreIniziale);

        // Creo l’istanza del comando con il modello, la figura e il nuovo colore da impostare
        command = new CambiaColoreInternoCommand(lavagnaModel, figura, nuovoColore);
    }

    @Test
    void testExecuteCambiaColoreInterno() {
        // Eseguo il comando, che dovrebbe cambiare il colore interno della figura nel modello
        command.execute();

        // Verifico che il metodo cambiaColoreInterno sia stato chiamato esattamente una volta
        // con la figura e il nuovo colore passato
        verify(lavagnaModel, times(1)).cambiaColoreInterno(figura, nuovoColore);
    }

    @Test
    void testUndoRipristinaColorePrecedente() {
        // Eseguo il comando e poi faccio undo
        command.execute();
        command.undo();

        // Verifico che l’undo abbia chiamato cambiaColoreInterno con il colore originale (blue)
        verify(lavagnaModel).cambiaColoreInterno(figura, coloreIniziale);
    }

    @Test
    void testIsUndoable() {
        // Verifico che il comando sia dichiarato undoable (che può essere annullato)
        assertTrue(command.isUndoable());
    }
}