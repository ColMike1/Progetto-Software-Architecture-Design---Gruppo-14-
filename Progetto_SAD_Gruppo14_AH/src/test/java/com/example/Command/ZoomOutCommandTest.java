package com.example.Command;

import com.example.View.LavagnaView;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

class ZoomOutCommandTest {

    @Test
    void testExecuteReset() {
        Group figureZoomabiliMock = mock(Group.class);
        LavagnaView lavagnaViewMock = mock(LavagnaView.class);

        try (MockedStatic<LavagnaView> lavagnaViewStatic = mockStatic(LavagnaView.class)) {

            lavagnaViewStatic.when(LavagnaView::getInstance).thenReturn(lavagnaViewMock);
            when(lavagnaViewMock.getFigureZoomabili()).thenReturn(figureZoomabiliMock);

            // Caso reset: scala ≈ 1.0
            when(figureZoomabiliMock.getScaleX()).thenReturn(1.0);

            ZoomOutCommand command = new ZoomOutCommand(50, 50);
            command.execute();

            verify(figureZoomabiliMock).setScaleX(1.0);
            verify(figureZoomabiliMock).setScaleY(1.0);
            verify(figureZoomabiliMock).setTranslateX(0);
            verify(figureZoomabiliMock).setTranslateY(0);
        }
    }

    @Test
    void testExecuteNormalZoomOut() {
        Group figureZoomabiliMock = mock(Group.class);
        LavagnaView lavagnaViewMock = mock(LavagnaView.class);

        try (MockedStatic<LavagnaView> lavagnaViewStatic = mockStatic(LavagnaView.class)) {

            lavagnaViewStatic.when(LavagnaView::getInstance).thenReturn(lavagnaViewMock);
            when(lavagnaViewMock.getFigureZoomabili()).thenReturn(figureZoomabiliMock);

            // Caso normale: scala > 1.0
            when(figureZoomabiliMock.getScaleX()).thenReturn(2.0);
            when(figureZoomabiliMock.localToScene(anyDouble(), anyDouble()))
                    .thenReturn(new Point2D(100, 100))  // prima
                    .thenReturn(new Point2D(110, 110)); // dopo

            when(figureZoomabiliMock.getTranslateX()).thenReturn(20.0);
            when(figureZoomabiliMock.getTranslateY()).thenReturn(30.0);

            ZoomOutCommand command = new ZoomOutCommand(50, 50);
            command.execute();

            // Scala nuova calcolata manualmente:
            // scalaNuova = 2.0 - (2.0 - 1.0)*0.2 = 1.8
            verify(figureZoomabiliMock).setScaleX(1.8);
            verify(figureZoomabiliMock).setScaleY(1.8);

            // Verifica chiamata traslazione
            verify(figureZoomabiliMock).setTranslateX((20.0 - 10.0) * 0.8);
            verify(figureZoomabiliMock).setTranslateY((30.0 - 10.0) * 0.8);
        }
    }
}
