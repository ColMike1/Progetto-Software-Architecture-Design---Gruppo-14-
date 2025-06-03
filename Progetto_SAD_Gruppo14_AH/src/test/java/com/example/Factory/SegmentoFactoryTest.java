/*
 * Verificare che il metodo creaFigura(...):
 *    - Ritorni un oggetto non nullo.
 *    - Ritorni un oggetto del tipo corretto (Segmento).
 *    - Inizializzi correttamente i valori passati (se Segmento li espone).
 *
 * */
package com.example.Factory;

import com.example.Model.Figura;
import com.example.Model.Segmento;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SegmentoFactoryTest {

    @Test
    void testCreaFigura_restituisceSegmentoValido() {
        // Arrange
        SegmentoFactory factory = new SegmentoFactory();
        double x1 = 0.0;
        double y1 = 0.0;
        double x2 = 100.0;
        double y2 = 100.0;
        Color strokeColor = Color.BLACK;
        Color fillColor = Color.TRANSPARENT; // spesso irrilevante per i segmenti
        int fontSize = 0; // ignorato

        // Act
        Figura figura = factory.creaFigura(x1, y1, x2, y2, strokeColor, fillColor, fontSize);

        // Assert
        assertNotNull(figura);
        assertTrue(figura instanceof Segmento);

        Segmento segmento = (Segmento) figura;
        assertEquals(x1, segmento.getX1());
        assertEquals(y1, segmento.getY1());
        assertEquals(x2, segmento.getX2());
        assertEquals(y2, segmento.getY2());
        assertEquals(strokeColor, segmento.getStrokeColor());
        assertEquals(fillColor, segmento.getFillColor());
    }
}
