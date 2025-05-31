package com.example.Model;

import com.example.State.FiguraSelezionataManager;
import com.example.State.SelezionaFiguraStato;
import com.example.State.StatoManager;
import com.example.Strategy.FiguraTemporaneaStrategy;
import com.example.Strategy.TextAreaTemporaneoStrategy;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.event.MouseEvent;

public class Testo extends Figura{


    private String contenuto = null;
    private Group gruppo;
    double padding = 10;
    private TextArea textArea = new TextArea();


    public Testo(double x1, double y1, double x2, double y2, Color strokeColor, Color fillColor){
        super(x1, y1, x2, y2, strokeColor, fillColor);

    }

    public void setContenuto(String contenuto) { this.contenuto = contenuto; }


    public Node creaNodoJavaFX(){

        gruppo = new Group();
        gruppo.setPickOnBounds(true);


        textArea.textProperty().addListener((obs, oldVal, newVal) -> setContenuto(newVal));

        textArea.setLayoutX(x1+padding);
        textArea.setLayoutY(y1+padding);
        textArea.setPrefWidth(Math.abs(x2 - x1)-2*padding);
        textArea.setPrefHeight(Math.abs(y2 - y1)-2*padding);
        textArea.setWrapText(true);
        textArea.setMinWidth(10);
        textArea.setMinHeight(10);
        textArea.setStyle("-fx-background-color: transparent; -fx-border-color:gray;");


        Rectangle box = new Rectangle();
        box.setX(x1);
        box.setY(y1);
        box.setWidth(Math.abs(x2 - x1));
        box.setHeight(Math.abs(y2 - y1));
        box.setFill(fillColor);
        box.setStroke(strokeColor);

        this.setNodo(box);

        gruppo.getChildren().add(box);
        gruppo.getChildren().add(textArea);
        gruppo.setUserData(this);


        return gruppo;





    }


    public FiguraTemporaneaStrategy getTemporaryResizeStrategy(){
        return new TextAreaTemporaneoStrategy();
    }

    @Override
    public Figura getClone() {
        return null;
    }

    public String toString() {
        return ("Testo");
    }
}
