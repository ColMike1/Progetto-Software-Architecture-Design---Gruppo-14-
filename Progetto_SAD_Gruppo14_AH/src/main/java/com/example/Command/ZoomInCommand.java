package com.example.Command;

import com.example.View.LavagnaView;
import javafx.geometry.Point2D;
import javafx.scene.Group;


public class ZoomInCommand implements Command {
    private Group figureInserite;
    private double scaleFactor = 1.1;
    private double x, y;


    public ZoomInCommand(LavagnaView lavagnaView, double x, double y) {
        this.figureInserite = lavagnaView.getFigureZoomabili();
        this.x = x;
        this.y = y;
    }

    public void execute() {

        // 1. Trasforma le coordinate locali del punto cliccato in coordinate della scena
        Point2D puntoNellaScena = figureInserite.localToScene(x, y);

        // 2. Applica lo zoom
        figureInserite.setScaleX(figureInserite.getScaleX() * scaleFactor);
        figureInserite.setScaleY(figureInserite.getScaleY() * scaleFactor);

        // 3. Ricalcola la posizione del punto dopo lo zoom
        Point2D nuovoPuntoNellaScena = figureInserite.localToScene(x, y);

        // 4. Calcola differenza e trasla il contenuto per mantenere il punto sotto il mouse
        double dx = nuovoPuntoNellaScena.getX() - puntoNellaScena.getX();
        double dy = nuovoPuntoNellaScena.getY() - puntoNellaScena.getY();

        figureInserite.setTranslateX(figureInserite.getTranslateX() - dx);
        figureInserite.setTranslateY(figureInserite.getTranslateY() - dy);

        System.out.println("Contenuto zoomato + -> " + figureInserite.getChildren());
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

