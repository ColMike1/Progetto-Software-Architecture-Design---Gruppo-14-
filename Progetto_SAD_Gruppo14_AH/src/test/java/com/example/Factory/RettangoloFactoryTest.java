/*
 * Verificare che il metodo creaFigura(...):
 *    - Ritorni un oggetto non nullo.
 *    - Ritorni un oggetto del tipo corretto (Rettangolo).
 *    - Inizializzi correttamente i valori passati (se Rettangolo li espone).
 *
 * */
package com.example.Factory;

import com.example.Model.Figura;
import com.example.Model.Rettangolo;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RettangoloFactoryTest {

    @Test
    void testCreaFigura_restituisceRettangoloValido() {
        // Arrange
        RettangoloFactory factory = new RettangoloFactory();
        double x1 = 5.0;
        double y1 = 10.0;
        double x2 = 50.0;
        double y2 = 100.0;
        Color strokeColor = Color.BLUE;
        Color fillColor = Color.YELLOW;
        int fontSize = 14; // ignorato nella factory

        // Act
        Figura figura = factory.creaFigura(x1, y1, x2, y2, strokeColor, fillColor, fontSize);

        // Assert
        assertNotNull(figura);
        assertTrue(figura instanceof Rettangolo);

        Rettangolo rettangolo = (Rettangolo) figura;
        assertEquals(x1, rettangolo.getX1());
        assertEquals(y1, rettangolo.getY1());
        assertEquals(x2, rettangolo.getX2());
        assertEquals(y2, rettangolo.getY2());
        assertEquals(strokeColor, rettangolo.getStrokeColor());
        assertEquals(fillColor, rettangolo.getFillColor());
    }
}
