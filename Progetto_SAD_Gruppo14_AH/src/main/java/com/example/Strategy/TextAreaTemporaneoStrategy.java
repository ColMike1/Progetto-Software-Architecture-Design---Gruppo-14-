package com.example.Strategy;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TextArea;


public class TextAreaTemporaneoStrategy implements FiguraTemporaneaStrategy {


    @Override
    public Node crea(double x1, double y1,double rotazione) {
        TextArea textArea = new TextArea();
        textArea.setLayoutX(x1);
        textArea.setLayoutY(y1);
        textArea.setPrefWidth(0);
        textArea.setPrefHeight(0);
        textArea.setWrapText(true);
        textArea.setVisible(false);
        textArea.setStyle("-fx-background-color:white;-fx-border-color:black;");
        textArea.setRotate(rotazione);
        return textArea;
    }

    @Override
    public void aggiorna(Node nodo, double x1, double y1, double x2, double y2, double rotazione) {
        TextArea textArea = (TextArea) nodo;
        textArea.setLayoutX(x1);
        textArea.setLayoutY(y1);
        textArea.setPrefWidth(Math.abs(x2 - x1));
        textArea.setPrefHeight(Math.abs(y2 - y1));
        textArea.setWrapText(true);
        textArea.setMinWidth(20);
        textArea.setMinHeight(20);
        textArea.setStyle("-fx-background-color:white;-fx-border-color:white;");
        textArea.setVisible(true);
        textArea.setRotate(rotazione);

    }
    @Override
    public void aggiornaRotazione(Node node, double rotazione) {
    }
    @Override
    public Node creaRotazione(double x1, double y1, double x2, double y2) {
        return null;
    }
}
