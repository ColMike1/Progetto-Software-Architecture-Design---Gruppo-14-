package com.example.Command;

import com.example.Command.CambiaColoreBordoCommand;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CambiaColoreBordoCommandTest {

    private LavagnaModel lavagnaModel;  // Mock del modello Lavagna
    private Figura figura;              // Mock della figura da modificare
    private CambiaColoreBordoCommand command; // Comando da testare

    private final Color coloreIniziale = Color.BLACK; // Colore iniziale simulato (nero)
    private final Color nuovoColore = Color.RED;      // Nuovo colore da impostare (rosso)

    @BeforeEach
    void setUp() {
        // Creo il mock del modello Lavagna
        lavagnaModel = mock(LavagnaModel.class);
        // Creo il mock della figura
        figura = mock(Figura.class);

        // Quando viene chiamato getStrokeColor() sulla figura, ritorna il colore iniziale
        when(figura.getStrokeColor()).thenReturn(coloreIniziale);

        // Creo l’istanza del comando con il modello, la figura e il nuovo colore da impostare
        command = new CambiaColoreBordoCommand(lavagnaModel, figura, nuovoColore);
    }

    @Test
    void testExecuteCambiaColoreBordo() {
        // Eseguo il comando, che dovrebbe cambiare il colore del bordo della figura nel modello
        command.execute();

        // Verifico che il metodo cambiaColoreBordo sia stato chiamato esattamente una volta
        // con la figura e il nuovo colore passato
        verify(lavagnaModel, times(1)).cambiaColoreBordo(figura, nuovoColore);
    }

    @Test
    void testUndoRipristinaColorePrecedente() {
        // Eseguo il comando e poi faccio undo
        command.execute();
        command.undo();

        // Verifico che l’undo abbia chiamato cambiaColoreBordo con il colore originale (nero)
        verify(lavagnaModel).cambiaColoreBordo(figura, coloreIniziale);
    }

    @Test
    void testIsUndoable() {
        // Verifico che il comando sia dichiarato undoable (che può essere annullato)
        assertTrue(command.isUndoable());
    }
}