package com.example.Strategy;

import javafx.scene.Node;
import javafx.scene.shape.Line;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SegmentoTemporaneoStrategyTest {

    @Test
    void testCrea() {
        SegmentoTemporaneoStrategy strategy = new SegmentoTemporaneoStrategy();

        double x1 = 10;
        double y1 = 20;
        double rotazione = 45;

        Node node = strategy.crea(x1, y1, rotazione);

        assertNotNull(node);
        assertTrue(node instanceof Line);

        Line line = (Line) node;

        // All'inizio start e end coincidono
        assertEquals(x1, line.getStartX());
        assertEquals(y1, line.getStartY());
        assertEquals(x1, line.getEndX());
        assertEquals(y1, line.getEndY());

        assertEquals(rotazione, line.getRotate());
    }

    @Test
    void testAggiorna() {
        SegmentoTemporaneoStrategy strategy = new SegmentoTemporaneoStrategy();

        Line line = new Line();
        double x1 = 5;
        double y1 = 10;
        double x2 = 30;
        double y2 = 40;
        double rotazione = 90;

        strategy.aggiorna(line, x1, y1, x2, y2, rotazione);

        assertEquals(x1, line.getStartX());
        assertEquals(y1, line.getStartY());
        assertEquals(x2, line.getEndX());
        assertEquals(y2, line.getEndY());
        assertEquals(rotazione, line.getRotate());
    }
}
