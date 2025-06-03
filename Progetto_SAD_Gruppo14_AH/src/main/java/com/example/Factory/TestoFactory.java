package com.example.Factory;
import com.example.Model.Figura;
import com.example.Model.Testo;
import javafx.scene.paint.Color;

public class TestoFactory implements FiguraFactory{

    @Override
    public Figura creaFigura(double x1, double y1, double x2, double y2, Color strokeColor, Color fillColor, int fontSize) {
        return new Testo(x1, y1, x2, y2, strokeColor, fillColor, fontSize,"");
    }
}
