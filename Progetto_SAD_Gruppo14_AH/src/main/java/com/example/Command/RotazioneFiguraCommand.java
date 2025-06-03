package com.example.Command;

import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import com.example.State.FiguraSelezionataManager;

public class RotazioneFiguraCommand implements Command{
    private Figura element;
    private double rotazione;
    private double vecchiaRotazione;


    public RotazioneFiguraCommand(Figura element, double rotazione) {

        this.element = element;
        this.rotazione = rotazione;
        vecchiaRotazione = element.getRotazione();
    }

    @Override
    public void execute() {
        System.out.println("Ruoto");
        LavagnaModel.getInstance().cambiaRotazione(element, rotazione);
    }
    @Override
    public void undo() {
        FiguraSelezionataManager.getInstance().clear();
        LavagnaModel.getInstance().cambiaRotazione(element, vecchiaRotazione);
        System.out.println("Ruoto alla vecchia posizione");
    }
    @Override
    public boolean isUndoable() {
        return true;
    }
}
