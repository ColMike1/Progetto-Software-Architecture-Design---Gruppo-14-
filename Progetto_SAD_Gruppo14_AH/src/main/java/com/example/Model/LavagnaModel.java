package com.example.Model;


import javafx.scene.paint.Color;

import java.util.List;
import java.util.ArrayList;

public class LavagnaModel {

    private static LavagnaModel instance;
    private List<Figura> figure = new ArrayList<>();
    private List<Runnable> osservatori = new ArrayList<>();
    //private Figura figuraSelezionata;

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

    public void aggiungiFigura(Figura figura){
        figure.add(figura);
        notificaOsservatori();
    }

    public void cambiaColoreBordo(Figura figura, Color colore){
        int index = figure.indexOf(figura);
        figure.get(index).setStrokeColor(colore);
        notificaOsservatori();

    }
    public void cambiaColoreInterno(Figura figura, Color colore){
        int index = figure.indexOf(figura);
        figure.get(index).setFillColor(colore);
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
        notificaOsservatori();
    }

    public void caricaFigure(List <Figura> tempList){
        figure.addAll(tempList);
        notificaOsservatori();
    }

    public void spostaSopra(Figura figura){
        int index = figure.indexOf(figura);
        Figura upper = figure.get(index + 1);
        figure.set(index, upper);
        figure.set(index + 1, figura);
        notificaOsservatori();

    }
    public void spostaSotto(Figura figura){
        int index = figure.indexOf(figura);
        Figura lower = figure.get(index - 1);
        figure.set(index, lower);
        figure.set(index - 1, figura);
        notificaOsservatori();

    }





}
