package com.example.State;

import com.example.Command.AggiungiFiguraCommand;
import com.example.Command.Command;
import com.example.Command.Invoker;
import com.example.Factory.EllisseFactory;
import com.example.Model.LavagnaModel;
import com.example.View.LavagnaView;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class DisegnaEllisseStato implements Stato{

    private double x1, y1;
    private Ellipse figuraTemporanea;
    AnchorPane lavagna;
    LavagnaModel lavagnaModel;
    ColorPicker strokeColor;
    ColorPicker fillColor;
    private Group figureInserite;


    public DisegnaEllisseStato(AnchorPane lavagna, LavagnaModel lavagnaModel, ColorPicker strokeColor, ColorPicker fillColor) {
        this.lavagna= lavagna;
        this.lavagnaModel = lavagnaModel;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.figureInserite = LavagnaView.getInstance().getFigureZoomabili();


    }
    @Override
    public void onMousePressed(MouseEvent event) {
        Point2D punto = figureInserite.sceneToLocal(event.getSceneX(), event.getSceneY());
        x1 = punto.getX();
        y1 = punto.getY();

        figuraTemporanea = new Ellipse(x1, y1, 0, 0);
        figuraTemporanea.setFill(fillColor.getValue());
        figuraTemporanea.setStroke(strokeColor.getValue());

        figureInserite.getChildren().add((figuraTemporanea));

    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        Point2D punto = figureInserite.sceneToLocal(event.getSceneX(), event.getSceneY());
        double x2 = punto.getX();
        double y2 = punto.getY();

        double centerX = (x1 + x2) / 2;
        double centerY = (y1 + y2) / 2;
        double radiusX = Math.abs(x2 - x1) / 2;
        double radiusY = Math.abs(y2 - y1) / 2;

        figuraTemporanea.setCenterX(centerX);
        figuraTemporanea.setCenterY(centerY);
        figuraTemporanea.setRadiusX(radiusX);
        figuraTemporanea.setRadiusY(radiusY);


    }

    @Override
    public void onMouseReleased(MouseEvent event) {
        Point2D punto = figureInserite.sceneToLocal(event.getSceneX(), event.getSceneY());
        double x2 = punto.getX();
        double y2 = punto.getY();

        figureInserite.getChildren().remove((figuraTemporanea));
        if(x1<0 || y1<0 || x2<0 || y2<0) {
            figuraTemporanea = null;
            return;
        }
        Command cmd = new AggiungiFiguraCommand(lavagnaModel,
                                                new EllisseFactory(),
                                                lavagna,
                                                x1, y1, x2, y2,
                                                strokeColor.getValue(),
                                                fillColor.getValue());
        Invoker.getInstance().executeCommand(cmd);
        figuraTemporanea = null;

    }

}
