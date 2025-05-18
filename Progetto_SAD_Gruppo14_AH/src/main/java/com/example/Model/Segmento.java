package com.example.Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Segmento extends Figura {

    public Segmento(double x1, double y1, double x2, double y2, Color strokeColor, Color fillColor) {
        super(x1, y1, x2, y2, strokeColor, fillColor); // salva tutto nella superclasse
    }

    @Override
    public Line creaNodoJavaFX() {
        Line line = new Line(x1, y1, x2, y2);
        line.setStrokeWidth(3);
        line.setStroke(strokeColor);
        line.setUserData(this);
        return line;
    }
}
