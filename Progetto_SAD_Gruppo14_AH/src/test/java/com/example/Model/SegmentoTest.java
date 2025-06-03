/*
* Questi test verificano:

Il funzionamento del metodo getClone() (con offset e rotazione).
Il corretto comportamento di toString().
Le proprietà impostate dal metodo creaNodoJavaFX().

* */
package com.example.Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SegmentoTest {

    @Test
    void testGetCloneCreaNuovoSegmentoConOffset() {
        Segmento originale = new Segmento(10, 20, 30, 40, Color.BLUE, Color.TRANSPARENT);
        originale.setRotazione(15);

        Segmento clone = (Segmento) originale.getClone();

        assertEquals(30, clone.getX1()); // 10 + 20
        assertEquals(40, clone.getY1()); // 20 + 20
        assertEquals(50, clone.getX2()); // 30 + 20
        assertEquals(60, clone.getY2()); // 40 + 20
        assertEquals(Color.BLUE, clone.getStrokeColor());
        assertEquals(Color.TRANSPARENT, clone.getFillColor());
        assertEquals(15, clone.getRotazione());
    }

    @Test
    void testToStringRestituisceNomeClasse() {
        Segmento s = new Segmento(0, 0, 100, 100, Color.BLACK, Color.WHITE);
        assertEquals("Segmento", s.toString());
    }

    @Test
    void testCreaNodoJavaFXImpostaProprietaCorrette() {
        Segmento s = new Segmento(5, 10, 50, 60, Color.GREEN, Color.TRANSPARENT);
        s.setRotazione(30);

        Line line = s.creaNodoJavaFX();

        assertEquals(5, line.getStartX());
        assertEquals(10, line.getStartY());
        assertEquals(50, line.getEndX());
        assertEquals(60, line.getEndY());
        assertEquals(2, line.getStrokeWidth());
        assertEquals(Color.GREEN, line.getStroke());
        assertEquals(30, line.getRotate());
        assertEquals(s, line.getUserData());
    }
}

