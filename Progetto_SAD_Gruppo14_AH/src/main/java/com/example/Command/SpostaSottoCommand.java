package com.example.Command;

import com.example.Model.LavagnaModel;
import com.example.Model.Figura;

public class SpostaSottoCommand implements Command {

    final private LavagnaModel lavagnaModel;
    final private Figura element;


    public SpostaSottoCommand(LavagnaModel lavagnaModel, Figura element) {
        this.lavagnaModel = lavagnaModel;
        this.element = element;
    }

    @Override
    public void execute() {
        lavagnaModel.spostaSotto(element);
    }

    @Override
    public void undo() {
        lavagnaModel.spostaSopra(element);
    }

    @Override
    public boolean canExecute() {
        int index = lavagnaModel.getFigure().indexOf(element);

        if (index == 0) {
            System.out.println("Elemento già in fondo, non può essere spostato più in basso.");
        }

        return index != 0;
    }
    @Override
    public boolean isUndoable() {
        return true;
    }
}