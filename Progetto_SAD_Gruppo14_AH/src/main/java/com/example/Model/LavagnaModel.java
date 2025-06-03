package com.example.Model;


import com.example.State.FiguraSelezionataManager;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.ArrayList;

public class LavagnaModel {

    private static LavagnaModel instance;
    private List<Figura> figure = new ArrayList<>();
    private List<Runnable> osservatori = new ArrayList<>();
    private Figura figuraCopiata; // Aggiunto da: Michele


    public static LavagnaModel getInstance(){
        if(instance == null){
            instance = new LavagnaModel();
        }
        return instance;
    }

    public void selezionaFigura(Figura figura){
        notificaOsservatori();
    }

    public void deselezionaFigura(Figura figura){
        notificaOsservatori();
    }

    public void rimuoviFigura(Figura figura){
        figure.remove(figura);
        notificaOsservatori();
    }

    public void setFiguraCopiata(Figura figura){
        figuraCopiata = figura;
    }

    public Figura getFiguraCopiata(){
        return figuraCopiata;
    }


    public void ridimensionaFigura(Figura figura, double x2, double y2){

        figure.get(figure.indexOf(figura)).setX2(x2);
        figure.get(figure.indexOf(figura)).setY2(y2);

        notificaOsservatori();
    }

    public void spostaFigura(Figura figura, double x1, double y1){
        double x2_diff = figure.get(figure.indexOf(figura)).getX2() - figure.get(figure.indexOf(figura)).getX1();
        double y2_diff = figure.get(figure.indexOf(figura)).getY2() - figure.get(figure.indexOf(figura)).getY1();

        figure.get(figure.indexOf(figura)).setX1(x1);
        figure.get(figure.indexOf(figura)).setY1(y1);
        figure.get(figure.indexOf(figura)).setX2(x1+x2_diff);
        figure.get(figure.indexOf(figura)).setY2(y1+y2_diff);

        notificaOsservatori();


    }

    //Cambia Colore bordo della figura. Aggiunto da Kevin
    public void cambiaColoreBordo(Figura figura, Color colore){
        figure.get(figure.indexOf(figura)).setStrokeColor(colore);
        notificaOsservatori();

    }
    //Cambia colore interno della figura. Aggiunto da Kevin
    public void cambiaColoreInterno(Figura figura, Color colore){
        figure.get(figure.indexOf(figura)).setFillColor(colore);
        notificaOsservatori();
    }

    public void aggiungiFigura(Figura figura){
        figure.add(figura);
        notificaOsservatori();
    }


    public List<Figura> getFigure() {
        return figure;
    }

    public void aggiungiOsservatore(Runnable osservatore){
        osservatori.add(osservatore);
    }

    public void notificaOsservatori(){
        for (Runnable r : osservatori){
            r.run();
        }
    }

    public void svuotaLavagna() {
        figure.clear();
        FiguraSelezionataManager.getInstance().clear();
        notificaOsservatori();
    }

    public void caricaFigure(List <Figura> tempList){
        figure.addAll(tempList);
        notificaOsservatori();
    }

    //Aggiunto da Kevin
    public void spostaSopra(Figura figura){
        // Rimuove la figura dalla sua posizione attuale
        figure.remove(figura);
        // la aggiunge in cima
        figure.add(figura);
        notificaOsservatori();
    }

    //Aggiunto da Kevin
    public void spostaSotto(Figura figura){
        // Rimuove la figura dalla sua posizione attuale
        figure.remove(figura);
        // Inserisce la figura in fondo
        figure.add(0, figura);
        // Notifica gli osservatori che il modello è cambiato
        notificaOsservatori();
    }
    //Aggiunto da Kevin
    public void cambiaRotazione(Figura figura, double rotazione){
        // Imposta una nuova rotazione sulla figura indicata
        figure.get(figure.indexOf(figura)).setRotazione(rotazione);
        // Notifica gli osservatori della modifica
        notificaOsservatori();
    }

}
