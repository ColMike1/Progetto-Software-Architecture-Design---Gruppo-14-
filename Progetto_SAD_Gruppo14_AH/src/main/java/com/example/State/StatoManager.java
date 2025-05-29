package com.example.State;

public class StatoManager {

    private static StatoManager instance;

    private Stato statoCorrente = new IdleStato();

    private StatoManager() {}

    public static StatoManager getInstance() {
        if (instance == null) {
            instance = new StatoManager();
        }
        return instance;
    }

    public void setStato(Stato nuovoStato) {
        this.statoCorrente = nuovoStato;
    }

    public Stato getStato() {
        return statoCorrente;
    }

    public void clearStato() {
        this.statoCorrente = null;
    }

    public boolean hasStato() {
        return statoCorrente != null;
    }
}
