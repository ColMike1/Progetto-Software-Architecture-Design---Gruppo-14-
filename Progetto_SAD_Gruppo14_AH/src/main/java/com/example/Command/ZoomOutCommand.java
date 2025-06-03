package com.example.Command;

import com.example.View.LavagnaView;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

public class ZoomOutCommand implements Command {

    private Group figureZoomabili;
    private LavagnaView lavagnaView;
    private double x, y, scaleFactor = 1.1;
    private AnchorPane lavagna;


    public ZoomOutCommand(LavagnaView lavagnaView, double x, double y) {
        this.lavagnaView = lavagnaView;
        this.figureZoomabili = lavagnaView.getFigureZoomabili();
        this.x = x;
        this.y = y;
        this.lavagna = lavagnaView.getLavagna();
    }


    public void execute() {
        double scalaCorrente = figureZoomabili.getScaleX();
        double scalaTarget = 1.0;



        // Se siamo già alla scala iniziale, esci
        if (Math.abs(scalaCorrente - scalaTarget) < 0.01) {
            figureZoomabili.setScaleX(1.0);
            figureZoomabili.setScaleY(1.0);
            figureZoomabili.setTranslateX(0);
            figureZoomabili.setTranslateY(0);
            return;
        }


        double scalaNuova = scalaCorrente - (scalaCorrente - scalaTarget) * 0.2;

        Point2D prima = figureZoomabili.localToScene(x, y);

        figureZoomabili.setScaleX(scalaNuova);
        figureZoomabili.setScaleY(scalaNuova);

         Point2D dopo = figureZoomabili.localToScene(x, y);
         double dx = dopo.getX() - prima.getX();
         double dy = dopo.getY() - prima.getY();

        /*
        lavagna.setPrefWidth(lavagna.getPrefWidth() * 0.8);
        lavagna.setPrefHeight(lavagna.getPrefHeight() * 0.8);
        */


         figureZoomabili.setTranslateX((figureZoomabili.getTranslateX() - dx) * 0.8);
         figureZoomabili.setTranslateY((figureZoomabili.getTranslateY() - dy) * 0.8);

        System.out.println("Zoom Out\n");

    }

    @Override
    public void undo() {
        return;
    }
    @Override
    public boolean isUndoable() {
        return false;
    }
}
