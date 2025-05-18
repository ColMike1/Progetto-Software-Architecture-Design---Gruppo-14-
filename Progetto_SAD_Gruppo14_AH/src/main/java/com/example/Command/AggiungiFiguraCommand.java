package com.example.Command;

import com.example.Factory.FiguraFactory;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import javafx.scene.paint.Color;

public class AggiungiFiguraCommand implements Command {
    private LavagnaModel lavagnaModel;
    private FiguraFactory figuraFactory;
    private double x1, y1, x2, y2;
    Color strokeColor;
    Color fillColor;

    public AggiungiFiguraCommand(LavagnaModel lavagnaModel, FiguraFactory factory, double x1, double y1, double x2, double y2, Color strokeColor, Color fillColor) {
        this.lavagnaModel = lavagnaModel;
        this.figuraFactory = factory;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.strokeColor= strokeColor;
        this.fillColor = fillColor;

    }

    public void execute() {
        Figura figura = figuraFactory.creaFigura(x1, y1, x2 , y2, strokeColor, fillColor );
        lavagnaModel.aggiungiFigura(figura);
    }
}

