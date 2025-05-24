package com.example.State;

import com.example.Model.Figura;

public class FiguraSelezionataManager {
    private static FiguraSelezionataManager instance;

    private Figura figuraSelezionata;

    private FiguraSelezionataManager() {
        // costruttore privato per Singleton
    }

    public static FiguraSelezionataManager getInstance() {
        if (instance == null) {
            instance = new FiguraSelezionataManager();
        }
        return instance;
    }

    public Figura get() {
        return figuraSelezionata;
    }

    public void set(Figura figura) {
        this.figuraSelezionata = figura;
    }

    public boolean isSelezionata(Figura figura) {
        return figura != null && figura.equals(figuraSelezionata);
    }

    public void clear() {
        this.figuraSelezionata = null;
    }

    public boolean isSelezionata() {
        return figuraSelezionata != null;
    }


}
