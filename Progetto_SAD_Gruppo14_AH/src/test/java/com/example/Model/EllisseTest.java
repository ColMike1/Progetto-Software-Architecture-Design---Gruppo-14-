/*
* Questo richiede l’ambiente JavaFX configurato correttamente
* altrimenti darà errore all’avvio del test.
*
* */
package com.example.Model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class EllisseTest {

    @Test
    void testCreaNodoJavaFX_creaEllipseCorretta() {
        Ellisse ellisse = new Ellisse(10, 20, 30, 40, Color.BLUE, Color.YELLOW);
        Node node = ellisse.creaNodoJavaFX();

        assertNotNull(node);
        assertTrue(node instanceof Ellipse);

        Ellipse shape = (Ellipse) node;
        assertEquals(Color.BLUE, shape.getStroke());
        assertEquals(Color.YELLOW, shape.getFill());
        assertEquals(1.0, shape.getStrokeWidth());
        assertEquals(ellisse, shape.getUserData());
    }

    @Test
    void testCloneCreaNuovaEllisseConOffset() {
        Ellisse originale = new Ellisse(10, 20, 110, 120, Color.RED, Color.GREEN);
        originale.setRotazione(30);

        Ellisse clone = (Ellisse) originale.getClone();

        assertEquals(30, clone.getX1()); // 10 + 20
        assertEquals(40, clone.getY1());
        assertEquals(130, clone.getX2());
        assertEquals(140, clone.getY2());
        assertEquals(Color.RED, clone.getStrokeColor());
        assertEquals(Color.GREEN, clone.getFillColor());
        assertEquals(30, clone.getRotazione());
    }

    @Test
    void testToStringRestituisceNomeClasse() {
        Ellisse e = new Ellisse(0, 0, 10, 10, Color.BLACK, Color.WHITE);
        assertEquals("Ellisse", e.toString());
    }
}
