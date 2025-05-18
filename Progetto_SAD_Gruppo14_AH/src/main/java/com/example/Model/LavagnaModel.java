package com.example.Model;


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


    /*public  void setFiguraSelezionata(Figura figuraSelezionata){
        this.figuraSelezionata = figuraSelezionata;
        notificaOsservatori();
        System.out.println("Ho selezionato la figura");
    }*/

   /* public  Figura getFiguraSelezionata(){
        return figuraSelezionata;
    }*/

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





}
