/**
 * Strategia specifica per la gestione temporanea e l'interazione con poligoni arbitrari.
 *
 * A differenza delle strategie usate per le figure classiche rettangoli, segmenti, ellissi,
 * questa classe gestisce figure composte da un numero variabile di vertici definiti
 * da una lista di coordinate List<Double>.
 *
 * Funzionalità principali:
 * - Il metodo creaPoligono genera un nodo Polygon JavaFX semi-trasparente
 *   da usare come feedback visivo temporaneo durante il disegno o la manipolazione del poligono.
 * - I metodi {aggiornaPoligono e aggiornaRotazione permettono di aggiornare
 *   dinamicamente i punti e la rotazione del poligono durante l'interazione dell'utente.
 * - I metodi standard definiti dall'interfaccia FiguraTemporaneaStrategy, come
 *   crea e aggiorna, non sono utilizzati nel contesto dei poligoni arbitrari
 *   e vengono lasciati vuoti o restituiscono null.
 * - Il metodo creaRotazionePoligono fornisce un'alternativa per creare un'anteprima
 *   del poligono durante una trasformazione di rotazione, se necessario.
 *
 * Questa strategia è parte integrante dell'estensibilità del sistema,
 * consentendo il supporto per forme complesse e dinamiche oltre le primitive geometriche standard.
 */

package com.example.Strategy;

import com.example.Model.PoligonoArbitrario;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;

import java.util.List;

public class PoligonoArbitrarioStrategy implements FiguraTemporaneaStrategy{

    @Override
    public Node crea(double x1, double y1, double rotazione) {
        return null;
    }
    public Node creaPoligono(List<Double> punti, double rotazione) {
        Polygon poligono = new Polygon();
        poligono.getPoints().addAll(punti);
        poligono.setStroke(Color.BLACK);
        poligono.setFill(Color.LIGHTGRAY.deriveColor(1,1,1,0.4));
        poligono.setRotate(rotazione);
        return poligono;
    }

    @Override
    public void aggiorna(Node node, double x1, double y1, double x2, double y2, double rotazione) {}

    public void aggiornaPoligono(Node node, List<Double> punti, double rotazione) {
        Polygon poligono = (Polygon) node;
        poligono.getPoints().clear();
        poligono.getPoints().addAll(punti);
        poligono.setRotate(rotazione);
    }

    @Override
    public void aggiornaRotazione(Node node, double rotazione){
        Polygon poligono = (Polygon) node;
        poligono.setRotate(rotazione);
    }
    @Override
    public Node creaRotazione(double x1, double y1, double x2, double y2) {
        // Non implementato per PoligonoArbitrarioStrategy, poiché non è necessario un nodo di rotazione specifico
        return null;
    }
    public Node creaRotazionePoligono(List<Double> punti, double rotazione) {
        Polygon poligono = new Polygon();
        poligono.getPoints().addAll(punti);
        poligono.setStroke(Color.BLACK);
        poligono.setFill(Color.LIGHTGRAY.deriveColor(1, 1, 1, 0.4));
        return poligono;
    }
}
