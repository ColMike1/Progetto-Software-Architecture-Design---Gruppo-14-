package com.example.Command;

import com.example.Model.LavagnaModel;
import com.example.Model.Figura;
public class SpostaSopraCommand implements Command{

    final private LavagnaModel lavagnaModel;
    final private Figura element;


    public SpostaSopraCommand(LavagnaModel lavagnaModel, Figura element) {
        this.lavagnaModel = lavagnaModel;
        this.element = element;
    }

    @Override
    public void execute() {
        lavagnaModel.spostaSopra(element);
    }

    @Override
    public void undo() {
        lavagnaModel.spostaSotto(element);
    }
    @Override
    public boolean canExecute() {
        int index = lavagnaModel.getFigure().indexOf(element);

        if (index == lavagnaModel.getFigure().size() - 1) {
            System.out.println("Elemento già in cima, non può essere spostato più in alto.");
        }

        return index < lavagnaModel.getFigure().size() - 1;
    }
}