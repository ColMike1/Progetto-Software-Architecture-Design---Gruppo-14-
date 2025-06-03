/*
* Verificare che il metodo creaFigura(...):
*    - Ritorni un oggetto non nullo.
*    - Ritorni un oggetto del tipo corretto (Ellisse).
*    - Inizializzi correttamente i valori passati (se Ellisse li espone).
*
* */

package com.example.Factory;

import com.example.Model.Ellisse;
import com.example.Model.Figura;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EllisseFactoryTest {

    @Test
    void testCreaFigura_restituisceEllisseValida() {
        // Arrange
        EllisseFactory factory = new EllisseFactory();
        double x1 = 10.0;
        double y1 = 20.0;
        double x2 = 30.0;
        double y2 = 40.0;
        Color stroke = Color.BLACK;
        Color fill = Color.RED;
        int fontSize = 12;

        // Act
        Figura figura = factory.creaFigura(x1, y1, x2, y2, stroke, fill, fontSize);

        // Assert
        assertNotNull(figura);
        assertTrue(figura instanceof Ellisse);

        Ellisse ellisse = (Ellisse) figura;
        assertEquals(x1, ellisse.getX1());
        assertEquals(y1, ellisse.getY1());
        assertEquals(x2, ellisse.getX2());
        assertEquals(y2, ellisse.getY2());
        assertEquals(stroke, ellisse.getStrokeColor());
        assertEquals(fill, ellisse.getFillColor());
    }
}
