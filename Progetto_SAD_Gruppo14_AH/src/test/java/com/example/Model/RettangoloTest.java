package com.example.Model;

import com.example.Model.Rettangolo;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RettangoloTest {

    @Test
    void testCostruttoreImpostaCoordinateCorrette() {
        Rettangolo r = new Rettangolo(100, 50, 200, 150, Color.BLUE, Color.YELLOW);

        assertEquals(100, r.getX1());
        assertEquals(50, r.getY1());
        assertEquals(200, r.getX2());
        assertEquals(150, r.getY2());

        assertEquals(Color.BLUE, r.getStrokeColor());
        assertEquals(Color.YELLOW, r.getFillColor());
    }

    @Test
    void testCreaNodoJavaFXCreaRectangleCorretto() {
        Rettangolo rettangolo = new Rettangolo(100, 50, 200, 150, Color.BLACK, Color.LIGHTGRAY);
        Node node = rettangolo.creaNodoJavaFX();

        assertNotNull(node);
        assertTrue(node instanceof Rectangle);

        Rectangle rect = (Rectangle) node;
        assertEquals(100, rect.getX());
        assertEquals(50, rect.getY());
        assertEquals(100, rect.getWidth());  // larghezza: 200 - 100
        assertEquals(100, rect.getHeight()); // altezza: 150 - 50
        assertEquals(Color.BLACK, rect.getStroke());
        assertEquals(Color.LIGHTGRAY, rect.getFill());
        assertEquals(1.0, rect.getStrokeWidth());
        assertEquals(rettangolo, rect.getUserData());
    }

    @Test
    void testCloneCreaNuovoRettangoloConOffset() {
        Rettangolo originale = new Rettangolo(10, 20, 110, 120, Color.RED, Color.GREEN);
        originale.setRotazione(45);

        Rettangolo clone = (Rettangolo) originale.getClone();

        assertEquals(30, clone.getX1()); // 10 + 20
        assertEquals(40, clone.getY1());
        assertEquals(130, clone.getX2());
        assertEquals(140, clone.getY2());
        assertEquals(Color.RED, clone.getStrokeColor());
        assertEquals(Color.GREEN, clone.getFillColor());
        assertEquals(45, clone.getRotazione());
    }

    @Test
    void testToStringRestituisceNomeClasse() {
        Rettangolo r = new Rettangolo(0, 0, 10, 10, Color.BLACK, Color.WHITE);
        assertEquals("Rettangolo", r.toString());
    }
}

