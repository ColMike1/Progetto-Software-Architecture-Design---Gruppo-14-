package com.example.Command;


import com.example.Model.LavagnaModel;
import com.example.Model.PoligonoArbitrario;
import com.example.State.FiguraSelezionataManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class SpostamentoPoligonoCommandTest {

    private PoligonoArbitrario mockPoligono;
    private LavagnaModel mockModel;
    private FiguraSelezionataManager mockManager;
    private MockedStatic<LavagnaModel> lavagnaStaticMock;
    private MockedStatic<FiguraSelezionataManager> figuraStaticMock;

    @BeforeEach
    public void setUp() {
        // Mock di PoligonoArbitrario
        mockPoligono = mock(PoligonoArbitrario.class);

        // Mock singoli per i singleton statici
        mockModel = mock(LavagnaModel.class);
        mockManager = mock(FiguraSelezionataManager.class);

        // Mock dei metodi statici
        lavagnaStaticMock = Mockito.mockStatic(LavagnaModel.class);
        figuraStaticMock = Mockito.mockStatic(FiguraSelezionataManager.class);

        // Setup ritorni dei metodi statici
        lavagnaStaticMock.when(LavagnaModel::getInstance).thenReturn(mockModel);
        figuraStaticMock.when(FiguraSelezionataManager::getInstance).thenReturn(mockManager);
        when(mockManager.get()).thenReturn(mockPoligono);
    }

    @AfterEach
    public void tearDown() {
        lavagnaStaticMock.close();
        figuraStaticMock.close();
    }

    @Test
    public void testExecuteSpostaPoligono() {
        double newX = 100.0;
        double newY = 150.0;
        double oldX = 10.0;
        double oldY = 20.0;

        SpostamentoPoligonoCommand command = new SpostamentoPoligonoCommand(
                mockPoligono, newX, newY, oldX, oldY
        );

        command.execute();

        verify(mockModel).spostaPoligono(mockPoligono, newX, newY);
    }

    @Test
    public void testUndoSpostaPoligono() {
        double newX = 100.0;
        double newY = 150.0;
        double oldX = 10.0;
        double oldY = 20.0;

        SpostamentoPoligonoCommand command = new SpostamentoPoligonoCommand(
                mockPoligono, newX, newY, oldX, oldY
        );

        command.undo();

        verify(mockManager).clear();
        verify(mockModel).spostaPoligono(mockPoligono, oldX, oldY);
    }

    @Test
    public void testIsUndoable() {
        SpostamentoPoligonoCommand command = new SpostamentoPoligonoCommand(
                mockPoligono, 0, 0, 0, 0
        );

        assert(command.isUndoable());
    }
}
