package com.example.Command;

import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import com.example.State.FiguraSelezionataManager;
import javafx.scene.paint.Color;

public class CambiaColoreInternoCommand implements Command {

    final private LavagnaModel lavagnaModel;
    private Figura element;
    private final Color nuovoColore;
    private Color vecchioColore;

    public CambiaColoreInternoCommand(LavagnaModel lavagnaModel, Figura element, Color colore) {
        this.lavagnaModel = lavagnaModel;
        this.element = element;
        this.nuovoColore = colore;
        this.vecchioColore = element.getFillColor();
    }

    @Override
    public void execute() {
        lavagnaModel.cambiaColoreInterno(element, nuovoColore);
    }
    @Override
    public void undo() {
        FiguraSelezionataManager.getInstance().clear();
        lavagnaModel.cambiaColoreInterno(element, vecchioColore);
        System.out.println("Coloro l'interno di " + element.toString() + " con vecchio colore: " + vecchioColore + "\n");
    }
    @Override
    public boolean isUndoable() {
        return true;
    }
}
