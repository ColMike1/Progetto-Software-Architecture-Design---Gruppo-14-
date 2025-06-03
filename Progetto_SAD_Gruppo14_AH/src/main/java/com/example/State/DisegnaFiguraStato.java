package com.example.State;

import com.example.Command.AggiungiFiguraCommand;
import com.example.Command.Command;
import com.example.Command.Invoker;
import com.example.Factory.FiguraFactory;
import com.example.Factory.RettangoloFactory;
import com.example.Model.LavagnaModel;
import com.example.Strategy.FiguraTemporaneaStrategy;
import com.example.View.LavagnaView;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class DisegnaFiguraStato implements Stato{
    private double x1, y1;
    private Node figuraTemporanea;
    private AnchorPane lavagna;
    private LavagnaModel model;
    private ColorPicker strokeColor, fillColor;
    private Group figureInserite;
    private FiguraFactory figuraFactory;
    private FiguraTemporaneaStrategy figuraTemporaneaStrategy;


    public DisegnaFiguraStato(FiguraFactory figuraFactory, AnchorPane lavagna, LavagnaModel model, ColorPicker strokeColor, ColorPicker fillColor) {
        this.figuraFactory = figuraFactory;
        this.lavagna = lavagna;
        this.model = model;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.figureInserite = LavagnaView.getInstance().getFigureZoomabili();
    }

    @Override
    public void onMousePressed(MouseEvent event) {

        Point2D punto = figureInserite.sceneToLocal(event.getSceneX(), event.getSceneY());
        x1 = punto.getX();
        y1 = punto.getY();

        figuraTemporaneaStrategy = figuraFactory.creaFigura(0,0,0,0,null, null).getTemporaryResizeStrategy();
        figuraTemporanea = figuraTemporaneaStrategy.crea(x1,y1);
        figureInserite.getChildren().add((figuraTemporanea));
    }

    @Override
    public void onMouseDragged(MouseEvent e) {

        Point2D punto = figureInserite.sceneToLocal(e.getSceneX(), e.getSceneY());
        double x2 = punto.getX();
        double y2 = punto.getY();

        figuraTemporaneaStrategy.aggiorna(figuraTemporanea,x1,y1,x2,y2);
    }


    @Override
    public void onMouseReleased(MouseEvent e) {
        Point2D punto = figureInserite.sceneToLocal(e.getSceneX(), e.getSceneY());
        double x2 = punto.getX();
        double y2 = punto.getY();

        figureInserite.getChildren().remove((figuraTemporanea));

       if(x1<0 || y1<0 || x2<0 || y2<0) {
            figuraTemporanea = null;
            return;
        }

        // Usa Command se vuoi supportare Undo
        Command cmd = new AggiungiFiguraCommand(model, figuraFactory, lavagna, x1, y1, x2, y2, strokeColor.getValue(), fillColor.getValue());
        Invoker.getInstance().executeCommand(cmd);

        figuraTemporanea = null;
    }
}
