package com.example.Strategy;

import javafx.scene.Node;

public interface FiguraTemporaneaStrategy {

    Node crea(double x1, double y1,double rotazione);
    void aggiorna(Node node, double x1, double y1, double x2, double y2, double rotazione);

    void aggiornaRotazione(Node node, double rotazione);
    Node creaRotazione(double x1, double y1, double x2, double y2);

}
