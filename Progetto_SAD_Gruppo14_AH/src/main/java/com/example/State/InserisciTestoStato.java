package com.example.State;


import com.example.Command.AggiungiFiguraCommand;
import com.example.Command.Command;
import com.example.Command.Invoker;
import com.example.Factory.TestoFactory;
import com.example.Model.LavagnaModel;
import com.example.Strategy.FiguraTemporaneaStrategy;
import com.example.Strategy.TextAreaTemporaneoStrategy;
import com.example.View.LavagnaView;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class InserisciTestoStato implements Stato {

    private double x1, y1;
    AnchorPane lavagna;
    LavagnaModel lavagnaModel;
    ColorPicker strokeColor;
    ColorPicker fillColor;
    private Group figureInserite;
    FiguraTemporaneaStrategy textAreaTemporaneoStrategy = new TextAreaTemporaneoStrategy();
    Node textArea = null;


    public InserisciTestoStato(AnchorPane lavagna, LavagnaModel lavagnaModel, ColorPicker strokeColor, ColorPicker fillColor) {
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
        textArea = textAreaTemporaneoStrategy.crea(x1,y1);
        figureInserite.getChildren().add(textArea);
    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        Point2D punto = figureInserite.sceneToLocal(event.getSceneX(), event.getSceneY());
        double x2 = punto.getX();
        double y2 = punto.getY();
        textAreaTemporaneoStrategy.aggiorna(textArea,x1,y1,x2,y2);

    }

    @Override
    public void onMouseReleased(MouseEvent event) {
        Point2D punto = figureInserite.sceneToLocal(event.getSceneX(), event.getSceneY());
        double x2 = punto.getX();
        double y2 = punto.getY();

        figureInserite.getChildren().remove(textArea);

        Command cmd = new AggiungiFiguraCommand(lavagnaModel,
                new TestoFactory(),
                lavagna,
                x1, y1, x2, y2,
                strokeColor.getValue(),
                fillColor.getValue());

        Invoker.getInstance().executeCommand(cmd);
    }
    @Override
    public void onSliderChanged(double sliderValue) {return;}
    @Override
    public void onSliderReleased(double sliderValue){return;}
}