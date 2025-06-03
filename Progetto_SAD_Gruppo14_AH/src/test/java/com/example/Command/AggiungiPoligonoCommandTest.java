package com.example.Command;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.Factory.FiguraFactory;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import com.example.State.FiguraSelezionataManager;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class AggiungiPoligonoCommandTest {

    private LavagnaModel lavagnaModel;
    private FiguraFactory figuraFactory;
    private AnchorPane lavagna;
    private List<Double> punti;
    private Color strokeColor;
    private Color fillColor;
    private Figura figuraMock;

    private AggiungiPoligonoCommand command;

    @BeforeEach
    public void setup() {
        lavagnaModel = mock(LavagnaModel.class);
        figuraFactory = mock(FiguraFactory.class);
        lavagna = mock(AnchorPane.class);

        // Simuliamo una lista di punti
        punti = Arrays.asList(10.0, 20.0, 150.0, 100.0, 300.0, 200.0);

        strokeColor = Color.BLACK;
        fillColor = Color.BLUE;

        figuraMock = mock(Figura.class);
        when(figuraFactory.creaFigura(anyDouble(), anyDouble(), anyDouble(), anyDouble(), any(Color.class), any(Color.class), anyInt()))
                .thenReturn(figuraMock);

        // Mock valori prefWidth e prefHeight iniziali
        when(lavagna.getPrefWidth()).thenReturn(200.0);
        when(lavagna.getPrefHeight()).thenReturn(150.0);

        command = new AggiungiPoligonoCommand(lavagnaModel, figuraFactory, punti, lavagna, strokeColor, fillColor);
    }

    @Test
    public void testExecuteAggiungeFiguraERidimensionaAnchorPane() {
        command.execute();

        // Verifica che figura venga creata correttamente
        verify(figuraFactory).creaFigura(0, 0, 0, 0, strokeColor, fillColor, 0);

        // Verifica che la figura venga aggiunta al modello
        verify(lavagnaModel).aggiungiFigura(figuraMock);

        // Il maxX è 300, maxY è 200, quindi la lavagna deve ridimensionarsi a 400x300 (300+100, 200+100)
        verify(lavagna).setPrefWidth(400.0);
        verify(lavagna).setPrefHeight(300.0);
    }

    @Test
    public void testExecuteNonRidimensionaSePuntiMinoriDelPref() {
        // Cambiamo punti in modo che maxX e maxY siano inferiori a prefWidth e prefHeight
        punti = Arrays.asList(10.0, 20.0, 50.0, 60.0);
        command = new AggiungiPoligonoCommand(lavagnaModel, figuraFactory, punti, lavagna, strokeColor, fillColor);

        when(lavagna.getPrefWidth()).thenReturn(100.0);
        when(lavagna.getPrefHeight()).thenReturn(100.0);

        command.execute();

        // LavagnaModel deve aggiungere la figura
        verify(lavagnaModel).aggiungiFigura(figuraMock);

        // Lavagna non deve ridimensionarsi perché maxX=50 < 100 e maxY=60 < 100
        verify(lavagna, never()).setPrefWidth(anyDouble());
        verify(lavagna, never()).setPrefHeight(anyDouble());
    }

    @Test
    public void testUndoRimuoveFiguraEsvuotaSelezione() {
        command.execute();

        // Mockiamo il singleton FiguraSelezionataManager con spy per verificare clear()
        FiguraSelezionataManager manager = FiguraSelezionataManager.getInstance();

        // spy non possibile su singleton senza cambiare implementazione,
        // quindi testiamo solo la rimozione della figura nel modello
        command.undo();

        verify(lavagnaModel).rimuoviFigura(figuraMock);
        // Non possiamo verificare direttamente clear() senza modificare classe FiguraSelezionataManager
    }

    @Test
    public void testIsUndoableReturnsTrue() {
        assertTrue(command.isUndoable());
    }
}
