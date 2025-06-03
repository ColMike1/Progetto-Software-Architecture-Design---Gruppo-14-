package com.example.Command;

import com.example.View.LavagnaView;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;


public class ZoomInCommand implements Command {
    private Group figureZoomabili;
    private double scaleFactor = 1.1;
    private double x, y;
    private AnchorPane lavagna;


    public ZoomInCommand(LavagnaView lavagnaView, double x, double y) {
        this.figureZoomabili = lavagnaView.getFigureZoomabili();
        this.x = x;
        this.y = y;
        this.lavagna = lavagnaView.getLavagna();
    }

    public void execute() {
        /*
        lavagna.setPrefWidth(lavagna.getPrefWidth() * scaleFactor);
        lavagna.setPrefHeight(lavagna.getPrefHeight() * scaleFactor);
        */

        // 1. Trasforma le coordinate locali del punto cliccato in coordinate della scena
        Point2D puntoNellaScena = figureZoomabili.localToScene(x, y);

        // 2. Applica lo zoom
        figureZoomabili.setScaleX(figureZoomabili.getScaleX() * scaleFactor);
        figureZoomabili.setScaleY(figureZoomabili.getScaleY() * scaleFactor);

        // 3. Ricalcola la posizione del punto dopo lo zoom
        Point2D nuovoPuntoNellaScena = figureZoomabili.localToScene(x, y);

        // 4. Calcola differenza e trasla il contenuto per mantenere il punto sotto il mouse
        double dx = nuovoPuntoNellaScena.getX() - puntoNellaScena.getX();
        double dy = nuovoPuntoNellaScena.getY() - puntoNellaScena.getY();

        figureZoomabili.setTranslateX(figureZoomabili.getTranslateX() - dx);
        figureZoomabili.setTranslateY(figureZoomabili.getTranslateY() - dy);

        System.out.println("Zoom In\n");
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

