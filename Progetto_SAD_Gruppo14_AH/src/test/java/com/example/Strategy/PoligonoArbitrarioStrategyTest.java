package com.example.Strategy;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.Strategy.PoligonoArbitrarioStrategy;
import javafx.scene.Node;
import javafx.scene.shape.Polygon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class PoligonoArbitrarioStrategyTest {

    private PoligonoArbitrarioStrategy strategy;

    @BeforeEach
    public void setUp() {
        strategy = new PoligonoArbitrarioStrategy();
    }

    @Test
    public void testCreaPoligono() {
        List<Double> punti = Arrays.asList(0.0, 0.0, 10.0, 0.0, 5.0, 5.0);
        double rotazione = 45;

        Node node = strategy.creaPoligono(punti, rotazione);

        assertTrue(node instanceof Polygon);
        Polygon poligono = (Polygon) node;

        // Controlla i punti
        assertEquals(punti.size(), poligono.getPoints().size());
        for (int i = 0; i < punti.size(); i++) {
            assertEquals(punti.get(i), poligono.getPoints().get(i));
        }

        // Controlla la rotazione
        assertEquals(rotazione, poligono.getRotate(), 0.0001);
    }

    @Test
    public void testAggiornaPoligono() {
        Polygon poligono = new Polygon();
        poligono.getPoints().addAll(0.0, 0.0, 1.0, 1.0);

        List<Double> nuoviPunti = Arrays.asList(2.0, 2.0, 3.0, 3.0, 4.0, 4.0);
        double nuovaRotazione = 90;

        strategy.aggiornaPoligono(poligono, nuoviPunti, nuovaRotazione);

        // Controlla i punti aggiornati
        assertEquals(nuoviPunti.size(), poligono.getPoints().size());
        for (int i = 0; i < nuoviPunti.size(); i++) {
            assertEquals(nuoviPunti.get(i), poligono.getPoints().get(i));
        }

        // Controlla la nuova rotazione
        assertEquals(nuovaRotazione, poligono.getRotate(), 0.0001);
    }
}
