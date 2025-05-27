package com.example.State;

import com.example.Command.AggiungiFiguraCommand;
import com.example.Command.Command;
import com.example.Command.Invoker;
import com.example.Factory.EllisseFactory;
import com.example.Model.LavagnaModel;
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


    public DisegnaEllisseStato(AnchorPane lavagna, LavagnaModel lavagnaModel, ColorPicker strokeColor, ColorPicker fillColor) {
        this.lavagna= lavagna;
        this.lavagnaModel = lavagnaModel;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;

    }
    @Override
    public void onMousePressed(MouseEvent event) {
        x1 = event.getX();
        y1 = event.getY();

        figuraTemporanea = new Ellipse(x1, y1, 0, 0);
        figuraTemporanea.setFill(fillColor.getValue());
        figuraTemporanea.setStroke(strokeColor.getValue());

        lavagna.getChildren().add(figuraTemporanea);

    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        double x2 = event.getX();
        double y2 = event.getY();

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
        double x2 = event.getX();
        double y2 = event.getY();

        lavagna.getChildren().remove(figuraTemporanea);
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
