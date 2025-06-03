package com.example.Factory;

import com.example.Model.Figura;
import javafx.scene.paint.Color;

public interface FiguraFactory {

    public Figura creaFigura(double x1, double y1, double x2, double y2, Color strokeColor, Color fillColor, int fontSize);
    /* Bisogna creare la classe Figura per poter utilizzare il metodo creaFigura*/
}
