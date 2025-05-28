package com.example.Strategy;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RettangoloTemporaneoStrategy implements FiguraTemporaneaStrategy {

    @Override
    public Node crea(double x1, double y1) {
        Rectangle r = new Rectangle(x1, y1, 0, 0);
        r.setStroke(Color.BLACK);
        r.setFill(Color.LIGHTGRAY.deriveColor(1, 1, 1, 0.4));
        return r;
    }

    @Override
    public void aggiorna(Node node, double x1, double y1, double x2, double y2) {
        Rectangle r = (Rectangle) node;
        r.setX(Math.min(x1, x2));
        r.setY(Math.min(y1, y2));
        r.setWidth(Math.abs(x2 - x1));
        r.setHeight(Math.abs(y2 - y1));
    }
}
