package com.example.Command;

import com.example.View.LavagnaView;
import javafx.geometry.Point2D;
import javafx.scene.Group;

public class ZoomOutCommand implements Command {

    private Group figureInserite;
    private LavagnaView lavagnaView;
    private double x, y, scaleFactor = 1.1;


    public ZoomOutCommand(LavagnaView lavagnaView, double x, double y) {
        this.lavagnaView = lavagnaView;
        this.figureInserite = lavagnaView.getFigureZoomabili();
        this.x = x;
        this.y = y;
    }


    public void execute() {
        double scalaCorrente = figureInserite.getScaleX();
        double scalaTarget = 1.0;

        // Se siamo già alla scala iniziale, esci
        if (Math.abs(scalaCorrente - scalaTarget) < 0.01) {
            figureInserite.setScaleX(1.0);
            figureInserite.setScaleY(1.0);
            figureInserite.setTranslateX(0);
            figureInserite.setTranslateY(0);
            return;
        }


        double scalaNuova = scalaCorrente - (scalaCorrente - scalaTarget) * 0.2;

        Point2D prima = figureInserite.localToScene(x, y);

        figureInserite.setScaleX(scalaNuova);
        figureInserite.setScaleY(scalaNuova);

         Point2D dopo = figureInserite.localToScene(x, y);
         double dx = dopo.getX() - prima.getX();
         double dy = dopo.getY() - prima.getY();

         figureInserite.setTranslateX((figureInserite.getTranslateX() - dx) * 0.8);
         figureInserite.setTranslateY((figureInserite.getTranslateY() - dy) * 0.8);

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
