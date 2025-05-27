package com.example.Command;

import com.example.Factory.FiguraFactory;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class AggiungiFiguraCommand implements Command {
    private LavagnaModel lavagnaModel;
    private FiguraFactory figuraFactory;
    private AnchorPane lavagna;
    private double x1, y1, x2, y2;
    Color strokeColor;
    Color fillColor;
    private Figura figuraAggiunta;

    public AggiungiFiguraCommand(LavagnaModel lavagnaModel, FiguraFactory factory, AnchorPane lavagna, double x1, double y1, double x2, double y2, Color strokeColor, Color fillColor) {
        this.lavagnaModel = lavagnaModel;
        this.figuraFactory = factory;
        this.lavagna = lavagna;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.strokeColor= strokeColor;
        this.fillColor = fillColor;

    }
    @Override
    public void execute() {
        figuraAggiunta = figuraFactory.creaFigura(x1, y1, x2 , y2, strokeColor, fillColor );
        lavagnaModel.aggiungiFigura(figuraAggiunta);

        double MaxX = Math.max(x1, x2);
        double MaxY = Math.max(y1,y2);
        if(MaxX > lavagna.getPrefWidth()) {
            lavagna.setPrefWidth(MaxX + 100);
        }
        if(MaxY > lavagna.getPrefHeight()) {
            lavagna.setPrefHeight(MaxY + 100);
        }
    }
    @Override
    public void undo() {
        if(figuraAggiunta != null) {
            lavagnaModel.rimuoviFigura(figuraAggiunta);
        }
    }
    @Override
    public boolean isUndoable() {
        return true;
    }
}

