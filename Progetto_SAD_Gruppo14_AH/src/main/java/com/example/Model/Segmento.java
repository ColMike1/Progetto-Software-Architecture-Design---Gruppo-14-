package com.example.Model;

import com.example.State.FiguraSelezionataManager;
import com.example.Strategy.EllisseTemporaneoStrategy;
import com.example.Strategy.FiguraTemporaneaStrategy;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Segmento extends Figura {

    public Segmento(double x1, double y1, double x2, double y2, Color strokeColor, Color fillColor) {
        super(x1, y1, x2, y2, strokeColor, fillColor); // salva tutto nella superclasse
    }

    @Override
    public Line creaNodoJavaFX() {
        Line line = new Line(x1, y1, x2, y2);

        this.setNodo(line);

        line.setStrokeWidth(2);
        line.setStroke(strokeColor);
        line.setUserData(this);



        if (FiguraSelezionataManager.getInstance().get() == this) {
            line.setEffect(new DropShadow(20, Color.GREY));
        }

        return line;



    }

    public FiguraTemporaneaStrategy getTemporaryResizeStrategy() {
        return new EllisseTemporaneoStrategy();
    }
}
