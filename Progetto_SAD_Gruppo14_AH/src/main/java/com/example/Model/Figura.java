package com.example.Model;
import com.example.Strategy.FiguraTemporaneaStrategy;
import javafx.scene.Node;
import javafx.scene.paint.Color;


public abstract class Figura {

    protected double x1, y1;    //Coord. OnMousePressed
    protected double x2, y2;    //Coord. OnMouseReleased
    protected Color strokeColor;
    protected Color fillColor;
    protected Node nodo;
    protected double rotazione;

    public Figura(double x1, double y1, double x2, double y2, Color strokeColor, Color fillColor) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.rotazione = 0;

    }

    public void setNodo(Node nodo) {
        this.nodo = nodo;
    }

    public Node getNodo() {
        return nodo;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public void setFillColor( Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setRotazione(double rotazione) {this.rotazione = rotazione;}

    public double getRotazione() { return rotazione; }




    public double getX1() {
        return x1;
    }
    public double getY1() {
        return y1;
    }
    public double getX2() {
        return x2;
    }
    public double getY2() {
        return y2;
    }


    public void setX1(double x1) {
        this.x1 = x1;
    }
    public void setX2(double x2) {
        this.x2 = x2;
    }
    public void setY1(double y1) {
        this.y1 = y1;
    }
    public void setY2(double y2) {
        this.y2 = y2;
    }

    public abstract Node creaNodoJavaFX();


    public abstract FiguraTemporaneaStrategy getTemporaryResizeStrategy();

    public abstract Figura getClone();

}