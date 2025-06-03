package com.example.Command;

import com.example.Factory.FiguraFactory;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AggiungiFiguraCommandTest {

    private LavagnaModel lavagnaModel;
    private FiguraFactory figuraFactory;
    private AnchorPane lavagna;
    private Figura figuraMock;
    private AggiungiFiguraCommand command;

    @BeforeEach
    void setUp() {
        // Creo i mock
        lavagnaModel = mock(LavagnaModel.class);
        figuraFactory = mock(FiguraFactory.class);
        lavagna = new AnchorPane();
        figuraMock = mock(Figura.class);

        // Quando il factory viene chiamato, restituisce la figura mockata
        when(figuraFactory.creaFigura(anyDouble(), anyDouble(), anyDouble(), anyDouble(),
                any(Color.class), any(Color.class), anyInt())).thenReturn(figuraMock);

        // Creo il comando da testare
        command = new AggiungiFiguraCommand(
                lavagnaModel,
                figuraFactory,
                lavagna,
                10, 20, 100, 200,
                Color.BLACK,
                Color.BLUE,
                12
        );
    }

    @Test
    void testExecute() {
        command.execute();

        // Verifico che il factory sia stato usato per creare la figura
        verify(figuraFactory).creaFigura(10, 20, 100, 200, Color.BLACK, Color.BLUE, 12);

        // Verifico che la figura sia stata aggiunta al model
        verify(lavagnaModel).aggiungiFigura(figuraMock);
    }

    @Test
    void testUndo() {
        // Prima eseguo execute per avere la figura aggiunta
        command.execute();

        // Poi eseguo undo
        command.undo();

        // Verifico che rimuove la figura dal model
        verify(lavagnaModel).rimuoviFigura(figuraMock);
    }
}
