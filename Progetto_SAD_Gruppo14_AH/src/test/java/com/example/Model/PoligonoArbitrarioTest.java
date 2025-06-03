package com.example.Model;

import com.example.Model.PoligonoArbitrario;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PoligonoArbitrarioTest {

    private PoligonoArbitrario poligono;

    @BeforeEach
    void setUp() {
        List<Double> punti = Arrays.asList(10.0, 10.0, 20.0, 20.0, 30.0, 10.0);
        poligono = new PoligonoArbitrario(punti, Color.BLACK, Color.RED);
    }

    @Test
    void testGetPunti() {
        List<Double> punti = poligono.getPunti();
        assertEquals(6, punti.size());
        assertEquals(10.0, punti.get(0));
        assertEquals(20.0, punti.get(2));
    }

    @Test
    void testGetX1() {
        assertEquals(10.0, poligono.getX1());
    }

    @Test
    void testGetY1() {
        assertEquals(10.0, poligono.getY1());
    }

    @Test
    void testGetX2() {
        // punti.size() = 6 => 3 punti => pari => usa indice punti.siz
        assertEquals(20.0, poligono.getX2());
    }
    @Test
    void testGetY2() {
        // 3 punti => (6 valori) → (6/2)%2 == 1 → true → ritorna punti.get(3)
        assertEquals(20.0, poligono.getY2());
    }

    @Test
    void testSetX2AndY2() {
        poligono.setX2(100.0);
        poligono.setY2(200.0);

        // Verifica modifica ai giusti indici
        assertEquals(100.0, poligono.getX2());
        assertEquals(200.0, poligono.getY2());
    }

    @Test
    void testGetClone() {
        PoligonoArbitrario clone = (PoligonoArbitrario) poligono.getClone();

        assertNotSame(poligono, clone);
        assertNotSame(poligono.getPunti(), clone.getPunti());

        // Verifica traslazione
        for (int i = 0; i < poligono.getPunti().size(); i++) {
            assertEquals(poligono.getPunti().get(i) + 20, clone.getPunti().get(i));
        }

        // Verifica colori identici
        assertEquals(poligono.getStrokeColor(), clone.getStrokeColor());
        assertEquals(poligono.getFillColor(), clone.getFillColor());
    }

    @Test
    void testToString() {
        assertEquals("Poligono", poligono.toString());
    }

    @Test
    void testTemporaryRenderStrategy() {
        assertNotNull(poligono.getTemporaryRenderStrategy());
    }
}