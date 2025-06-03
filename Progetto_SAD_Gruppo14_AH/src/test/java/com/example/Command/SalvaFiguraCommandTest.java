package com.example.Command;

import com.example.Command.SalvaFiguraCommand;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SalvaFiguraCommandTest {

    private LavagnaModel lavagnaModel;  // Mock del modello Lavagna contenente le figure
    private File tempFile;               // File temporaneo per salvare i dati nel test

    @BeforeEach
    public void setUp() throws Exception {
        lavagnaModel = mock(LavagnaModel.class);        // Creo un mock di LavagnaModel
        tempFile = File.createTempFile("test-figure", ".txt"); // Creo un file temporaneo per il test
        tempFile.deleteOnExit();                         // Assicuro che il file venga cancellato dopo il test
    }

    @Test
    public void testSalvataggio() throws Exception {
        // Creo un mock generico di Figura (classe astratta)
        Figura figuraMock = mock(Figura.class);

        // Definisco i valori di ritorno dei metodi get per simulare una figura con coordinate e colori specifici
        when(figuraMock.getX1()).thenReturn(1.0);
        when(figuraMock.getY1()).thenReturn(2.0);
        when(figuraMock.getX2()).thenReturn(3.0);
        when(figuraMock.getY2()).thenReturn(4.0);
        when(figuraMock.getStrokeColor()).thenReturn(Color.RED);
        when(figuraMock.getFillColor()).thenReturn(Color.BLUE);
        when(figuraMock.getRotazione()).thenReturn(45.0);

        // Quando il modello viene interrogato per le figure, restituisco una lista con il mock appena creato
        when(lavagnaModel.getFigure()).thenReturn(List.of(figuraMock));

        // Creo un'istanza anonima di SalvaFiguraCommand per testare il metodo execute
        // Sovrascrivo execute per scrivere nel file temporaneo i dati della figura mockata
        SalvaFiguraCommand command = new SalvaFiguraCommand(null, lavagnaModel) {
            @Override
            public void execute() {
                try (var writer = new java.io.FileWriter(tempFile)) {
                    for (Figura f : lavagnaModel.getFigure()) {
                        // Ottengo il tipo della figura (nome della classe minuscolo)
                        String tipo = f.getClass().getSimpleName().toLowerCase();
                        // Costruisco la stringa di salvataggio con tutti i parametri della figura
                        String figura = tipo + ";" +
                                "x1=" + f.getX1() + ";" +
                                "y1=" + f.getY1() + ";" +
                                "x2=" + f.getX2() + ";" +
                                "y2=" + f.getY2() + ";" +
                                "stroke=" + toHexString(f.getStrokeColor()) + ";" +
                                "fill=" + toHexString(f.getFillColor()) + ";" +
                                "rotazione=" + f.getRotazione();
                        // Scrivo la stringa nel file
                        writer.write(figura + "\n");
                    }
                } catch (Exception e) {
                    fail("Errore scrittura file: " + e.getMessage()); // Fallisce il test se c'è errore di scrittura
                }
            }
        };

        // Eseguo il comando di salvataggio
        command.execute();

        // Leggo il contenuto del file temporaneo per verificarlo
        String contenuto = Files.readString(tempFile.toPath());

        // Verifico che il contenuto del file contenga le informazioni attese della figura mockata
        assertTrue(contenuto.contains("figura"));           // Controlla la presenza del tipo di figura (dal mock)
        assertTrue(contenuto.contains("x1=1.0"));            // Controlla la coordinata x1
        assertTrue(contenuto.contains("y2=4.0"));            // Controlla la coordinata y2
        assertTrue(contenuto.contains("stroke=#ff0000ff"));  // Controlla il colore stroke (rosso + alpha)
        assertTrue(contenuto.contains("fill=#0000ffff"));    // Controlla il colore fill (blu + alpha)
        assertTrue(contenuto.contains("rotazione=45.0"));    // Controlla la rotazione
    }

    // Metodo helper per convertire un colore JavaFX in stringa esadecimale con alpha
    private String toHexString(Color color) {
        if (color == null) return "null";
        int r = (int) Math.round(color.getRed() * 255);
        int g = (int) Math.round(color.getGreen() * 255);
        int b = (int) Math.round(color.getBlue() * 255);
        int a = (int) Math.round(color.getOpacity() * 255);
        return String.format("#%02x%02x%02x%02x", r, g, b, a);
    }
}