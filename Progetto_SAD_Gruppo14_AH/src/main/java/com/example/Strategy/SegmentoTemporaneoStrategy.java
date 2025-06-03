package com.example.Strategy;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class SegmentoTemporaneoStrategy implements FiguraTemporaneaStrategy {

    @Override
    public Node crea(double x1, double y1) {
        Line l = new Line(x1, y1, x1, y1); // inizio e fine uguali
        l.setStroke(Color.BLACK);
        return l;
    }

    @Override
    public void aggiorna(Node node, double x1, double y1, double x2, double y2) {
        Line l = (Line) node;
        l.setStartX(x1);
        l.setStartY(y1);
        l.setEndX(x2);
        l.setEndY(y2);
    }

    //Aggiunto da Kevin
    // Applica una rotazione al nodo (l'intera linea) attorno al suo punto di rotazione predefinito
    @Override
    public void aggiornaRotazione(Node node, double rotazione) {
        Line e = (Line) node;

        e.setRotate(rotazione);
    }

    // Crea una nuova linea tra due punti, da usare per visualizzare l'effetto della rotazione
    @Override
    public Node creaRotazione(double x1, double y1, double x2, double y2) {
        Line line = new Line(x1, y1, x2, y2);
        line.setStroke(Color.BLACK); // Colore del tratto nero per la visibilità
        return line;
    }
}
