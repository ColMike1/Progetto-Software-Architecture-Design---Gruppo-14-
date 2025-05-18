package com.example.Model;
import javafx.scene.Node;
import javafx.scene.paint.Color;


public abstract class Figura {

    protected double x1, y1;    //Coord. OnMousePressed
    protected double x2, y2;    //Coord. OnMouseReleased
    protected Color strokeColor;
    protected Color fillColor;

    public Figura(double x1, double y1, double x2, double y2, Color strokeColor, Color fillColor) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;

    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public void setFillColorColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public abstract Node creaNodoJavaFX();
}