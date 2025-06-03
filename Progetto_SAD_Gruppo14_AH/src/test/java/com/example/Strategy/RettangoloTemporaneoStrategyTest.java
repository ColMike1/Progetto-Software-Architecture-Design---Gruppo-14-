package com.example.Strategy;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RettangoloTemporaneoStrategyTest {

    @Test
    void testCrea() {
        RettangoloTemporaneoStrategy strategy = new RettangoloTemporaneoStrategy();

        double x1 = 15;
        double y1 = 25;
        double rotazione = 60;

        Node node = strategy.crea(x1, y1, rotazione);

        assertNotNull(node);
        assertTrue(node instanceof Rectangle);

        Rectangle r = (Rectangle) node;
        assertEquals(x1, r.getX());
        assertEquals(y1, r.getY());
        assertEquals(0, r.getWidth());
        assertEquals(0, r.getHeight());
        assertEquals(rotazione, r.getRotate());
        // Puoi aggiungere assert per colore stroke e fill se vuoi
    }

    @Test
    void testAggiorna() {
        RettangoloTemporaneoStrategy strategy = new RettangoloTemporaneoStrategy();

        Rectangle rect = new Rectangle();
        double x1 = 10;
        double y1 = 20;
        double x2 = 40;
        double y2 = 50;
        double rotazione = 30;

        strategy.aggiorna(rect, x1, y1, x2, y2, rotazione);

        assertEquals(Math.min(x1, x2), rect.getX());
        assertEquals(Math.min(y1, y2), rect.getY());
        assertEquals(Math.abs(x2 - x1), rect.getWidth());
        assertEquals(Math.abs(y2 - y1), rect.getHeight());
        assertEquals(rotazione, rect.getRotate());
    }
}

