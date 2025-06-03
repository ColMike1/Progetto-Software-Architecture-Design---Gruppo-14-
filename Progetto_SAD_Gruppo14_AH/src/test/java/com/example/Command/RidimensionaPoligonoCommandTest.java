package com.example.Command;

import static org.mockito.Mockito.*;

import com.example.Model.LavagnaModel;
import com.example.Model.PoligonoArbitrario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Arrays;
import java.util.List;

class RidimensionaPoligonoCommandTest {

    private PoligonoArbitrario poligono;
    private List<Double> puntiIniziali;
    private List<Double> puntiNuovi;

    @BeforeEach
    void setup() {
        // Punti di esempio
        puntiIniziali = Arrays.asList(1.0, 2.0, 3.0, 4.0);
        puntiNuovi = Arrays.asList(5.0, 6.0, 7.0, 8.0);

        // Mock di poligono
        poligono = mock(PoligonoArbitrario.class);
        when(poligono.getPunti()).thenReturn(puntiNuovi);
    }

    @Test
    void testExecuteChiamaRidimensionaConPuntiDopo() {
        // Mock statico per LavagnaModel.getInstance()
        try (MockedStatic<LavagnaModel> lavagnaMock = mockStatic(LavagnaModel.class)) {
            LavagnaModel lavagnaModelMock = mock(LavagnaModel.class);
            lavagnaMock.when(LavagnaModel::getInstance).thenReturn(lavagnaModelMock);

            RidimensionaPoligonoCommand cmd = new RidimensionaPoligonoCommand(poligono, puntiIniziali);
            cmd.execute();

            // Verifica che ridimensionaPoligono venga chiamato con i nuovi punti
            verify(lavagnaModelMock).ridimensionaPoligono(poligono, puntiNuovi);
        }
    }

    @Test
    void testUndoChiamaRidimensionaConPuntiPrima() {
        try (MockedStatic<LavagnaModel> lavagnaMock = mockStatic(LavagnaModel.class)) {
            LavagnaModel lavagnaModelMock = mock(LavagnaModel.class);
            lavagnaMock.when(LavagnaModel::getInstance).thenReturn(lavagnaModelMock);

            RidimensionaPoligonoCommand cmd = new RidimensionaPoligonoCommand(poligono, puntiIniziali);
            cmd.undo();

            // Verifica che ridimensionaPoligono venga chiamato con i vecchi punti
            verify(lavagnaModelMock).ridimensionaPoligono(poligono, puntiIniziali);
        }
    }

    @Test
    void testIsUndoable() {
        RidimensionaPoligonoCommand cmd = new RidimensionaPoligonoCommand(poligono, puntiIniziali);
        assert(cmd.isUndoable());
    }
}

