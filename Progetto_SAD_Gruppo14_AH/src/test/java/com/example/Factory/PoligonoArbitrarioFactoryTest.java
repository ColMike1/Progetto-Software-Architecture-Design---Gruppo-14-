package com.example.Factory;

import com.example.Factory.PoligonoArbitrarioFactory;
import com.example.Model.Figura;
import com.example.Model.PoligonoArbitrario;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PoligonoArbitrarioFactoryTest {

    @Test
    void testCreaFigura() {
        // Dati di input
        List<Double> punti = Arrays.asList(10.0, 20.0, 30.0, 40.0, 50.0, 60.0);
        Color stroke = Color.BLACK;
        Color fill = Color.RED;

        // Crea la factory
        PoligonoArbitrarioFactory factory = new PoligonoArbitrarioFactory(punti);

        // Crea la figura
        Figura figura = factory.creaFigura(0, 0, 0, 0, stroke, fill, 0);

        // Verifica tipo
        assertTrue(figura instanceof PoligonoArbitrario);

        // Verifica contenuto (cast per accedere a metodi specifici)
        PoligonoArbitrario poligono = (PoligonoArbitrario) figura;

        assertEquals(punti, poligono.getPunti(), "I punti del poligono non corrispondono");
        assertEquals(stroke, poligono.getStrokeColor(), "Il colore del bordo non corrisponde");
        assertEquals(fill, poligono.getFillColor(), "Il colore di riempimento non corrisponde");
    }
}
