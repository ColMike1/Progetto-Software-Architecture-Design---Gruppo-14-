package com.example.Strategy;

import javafx.scene.Node;
import javafx.scene.shape.Ellipse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EllisseTemporaneoStrategyTest {

    @Test
    void testCrea() {
        EllisseTemporaneoStrategy strategy = new EllisseTemporaneoStrategy();

        double x1 = 10;
        double y1 = 20;
        double rotazione = 30;

        Node node = strategy.crea(x1, y1, rotazione);
        assertNotNull(node);
        assertTrue(node instanceof Ellipse);

        Ellipse e = (Ellipse) node;
        assertEquals(x1, e.getCenterX());
        assertEquals(y1, e.getCenterY());
        assertEquals(0, e.getRadiusX());
        assertEquals(0, e.getRadiusY());
        assertEquals(rotazione, e.getRotate());
        // puoi aggiungere controlli su colore stroke e fill se vuoi
    }

    @Test
    void testAggiorna() {
        EllisseTemporaneoStrategy strategy = new EllisseTemporaneoStrategy();

        Ellipse ellipse = new Ellipse();
        double x1 = 10;
        double y1 = 20;
        double x2 = 30;
        double y2 = 50;
        double rotazione = 45;

        strategy.aggiorna(ellipse, x1, y1, x2, y2, rotazione);

        assertEquals((x1 + x2) / 2, ellipse.getCenterX());
        assertEquals((y1 + y2) / 2, ellipse.getCenterY());
        assertEquals(Math.abs(x2 - x1) / 2, ellipse.getRadiusX());
        assertEquals(Math.abs(y2 - y1) / 2, ellipse.getRadiusY());
        assertEquals(rotazione, ellipse.getRotate());
    }
}

