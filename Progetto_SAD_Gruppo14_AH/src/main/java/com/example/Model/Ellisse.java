package com.example.Model;

import com.example.State.FiguraSelezionataManager;
import com.example.Strategy.EllisseTemporaneoStrategy;
import com.example.Strategy.FiguraTemporaneaStrategy;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;


public class Ellisse extends Figura {

    public Ellisse(double x1, double y1, double x2, double y2, Color strokeColor, Color fillColor) {
        super(x1, y1, x2, y2, strokeColor, fillColor,0); // salva tutte le coordinate nella superclasse
    }

    @Override
    public Node creaNodoJavaFX() {
        // Calcola centro e raggi
        double centerX = (x1 + x2) / 2;
        double centerY = (y1 + y2) / 2;
        double radiusX = Math.abs(x2 - x1) / 2;
        double radiusY = Math.abs(y2 - y1) / 2;

        Ellipse e = new Ellipse(centerX, centerY, radiusX, radiusY);

        this.setNodo(e);

        e.setRotate(rotazione);
        e.setStrokeWidth(1);
        e.setStroke(strokeColor);
        e.setFill(fillColor);
        e.setUserData(this);


        if (FiguraSelezionataManager.getInstance().get() == this) {
            e.setEffect(new DropShadow(20, Color.GREY));
        }

        return e;
    }

    @Override
    public FiguraTemporaneaStrategy getTemporaryResizeStrategy() {
        return new EllisseTemporaneoStrategy();
    }


    // serve per Paste()

    @Override
    public Figura getClone() {
        int dx = 20;
        Ellisse e = new Ellisse(x1+dx, y1+dx, x2+dx, y2+dx, strokeColor, fillColor);
        e.setRotazione(rotazione);
        return e;
    }

    public String toString(){
        return "Ellisse";
    }
}
