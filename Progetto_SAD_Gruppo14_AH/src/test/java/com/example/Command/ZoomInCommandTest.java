package com.example.Command;

import com.example.View.LavagnaView;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

class ZoomInCommandTest {

    @Test
    void testExecute() {
        // Mock del Group (figureZoomabili)
        Group figureZoomabiliMock = mock(Group.class);

        // Mock statico di LavagnaView
        LavagnaView lavagnaViewMock = mock(LavagnaView.class);

        try (MockedStatic<LavagnaView> lavagnaViewStatic = mockStatic(LavagnaView.class)) {

            // Configuro il singleton per restituire il mock
            lavagnaViewStatic.when(LavagnaView::getInstance).thenReturn(lavagnaViewMock);
            when(lavagnaViewMock.getFigureZoomabili()).thenReturn(figureZoomabiliMock);

            // Simulo i valori ritornati da localToScene prima e dopo lo zoom
            when(figureZoomabiliMock.localToScene(anyDouble(), anyDouble()))
                    .thenReturn(new Point2D(100, 100))  // prima dello zoom
                    .thenReturn(new Point2D(110, 110)); // dopo lo zoom

            // Simulo valori iniziali di scala e traslazione
            when(figureZoomabiliMock.getScaleX()).thenReturn(1.0);
            when(figureZoomabiliMock.getScaleY()).thenReturn(1.0);
            when(figureZoomabiliMock.getTranslateX()).thenReturn(0.0);
            when(figureZoomabiliMock.getTranslateY()).thenReturn(0.0);

            // Creo il comando da testare
            ZoomInCommand command = new ZoomInCommand(50, 50);

            // Eseguo il comando
            command.execute();

            // Verifica che abbia applicato la nuova scala
            verify(figureZoomabiliMock).setScaleX(1.0 * 1.1);
            verify(figureZoomabiliMock).setScaleY(1.0 * 1.1);

            // Verifica che abbia aggiornato le traslazioni
            verify(figureZoomabiliMock).setTranslateX(-10.0);
            verify(figureZoomabiliMock).setTranslateY(-10.0);
        }
    }
}
