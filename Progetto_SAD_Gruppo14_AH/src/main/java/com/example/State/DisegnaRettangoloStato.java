package com.example.State;

import com.example.Command.AggiungiFiguraCommand;
import com.example.Command.Command;
import com.example.Command.Invoker;
import com.example.Factory.RettangoloFactory;
import com.example.Model.LavagnaModel;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DisegnaRettangoloStato implements Stato {

    private double x1, y1;
    private Rectangle figuraTemporanea;
    private AnchorPane lavagna;
    private LavagnaModel model;
    private ColorPicker strokeColor, fillColor;


    public DisegnaRettangoloStato(AnchorPane lavagna, LavagnaModel model, ColorPicker strokeColor, ColorPicker fillColor) {
        this.lavagna = lavagna;
        this.model = model;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
    }
    @Override

    public void onMousePressed(MouseEvent event) {

        x1 = event.getX();
        y1 = event.getY();
        figuraTemporanea = new Rectangle(x1, y1, 0, 0);
        figuraTemporanea.setStroke(strokeColor.getValue());
        figuraTemporanea.setFill(fillColor.getValue().deriveColor(1, 1, 1, 0.4));
        lavagna.getChildren().add(figuraTemporanea);

    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        double x2 = e.getX();
        double y2 = e.getY();

        figuraTemporanea.setX(Math.min(x1, x2));
        figuraTemporanea.setY(Math.min(y1, y2));
        figuraTemporanea.setWidth(Math.abs(x2 - x1));
        figuraTemporanea.setHeight(Math.abs(y2 - y1));
    }


    @Override
    public void onMouseReleased(MouseEvent e) {
        double x2 = e.getX();
        double y2 = e.getY();

        lavagna.getChildren().remove(figuraTemporanea);
        if(x1<0 || y1<0 || x2<0 || y2<0) {
            figuraTemporanea = null;
            return;
        }

        // Usa Command se vuoi supportare Undo
        Command cmd = new AggiungiFiguraCommand(model, new RettangoloFactory(), lavagna, x1, y1, x2, y2, strokeColor.getValue(), fillColor.getValue());
        Invoker.getInstance().executeCommand(cmd);

        figuraTemporanea = null;
    }

}
