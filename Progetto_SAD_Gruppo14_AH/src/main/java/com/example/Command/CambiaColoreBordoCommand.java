package com.example.Command;

import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import javafx.scene.paint.Color;

public class CambiaColoreBordoCommand implements Command {
    final private LavagnaModel lavagnaModel;
    private Figura element;
    private final Color nuovoColore;
    private Color vecchioColore;

    public CambiaColoreBordoCommand(LavagnaModel lavagnaModel, Figura element, Color colore) {
        this.lavagnaModel = lavagnaModel;
        this.element = element;
        this.nuovoColore = colore;
        this.vecchioColore = element.getStrokeColor();
    }

    @Override
    public void execute() {
        lavagnaModel.cambiaColoreBordo(element, nuovoColore);
    }
    @Override
    public void undo() {
        lavagnaModel.cambiaColoreBordo(element, vecchioColore);
    }
}
