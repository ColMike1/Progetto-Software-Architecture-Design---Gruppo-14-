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
}
