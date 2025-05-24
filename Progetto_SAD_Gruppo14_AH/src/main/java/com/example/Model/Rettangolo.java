package com.example.Model;
import com.example.State.FiguraSelezionataManager;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Rettangolo extends Figura {

    private double larghezza;
    private double altezza;


    public Rettangolo(double x1, double y1, double x2, double y2, Color strokeColor, Color fillColor) {
        super(x1, y1, x2, y2, strokeColor, fillColor);

        larghezza = Math.abs(x2 - x1);
        altezza = Math.abs(y2 - y1);
        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);

    }

    @Override
    public Rectangle creaNodoJavaFX() {
        Rectangle r = new Rectangle(x1, y1, larghezza, altezza);
        r.setStrokeWidth(3);
        r.setStroke(strokeColor);
        r.setFill(fillColor);
        r.setUserData(this); // salvo nell'istanza Rectangle il riferimento all'istanza Rettangolo per poi poter recuperare la figura Rettangolo dal model, è un metadato.


        if (FiguraSelezionataManager.getInstance().get() == this) {
            r.setEffect(new DropShadow(20, Color.GREY));
        }

        return r;
    }

}