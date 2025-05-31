package com.example.Command;


import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import com.example.State.FiguraSelezionataManager;

public class EliminaCommand implements Command {

    private final LavagnaModel lavagnaModel;
    private final Figura figura;

    public EliminaCommand(LavagnaModel lavagnaModel, Figura figura) {
        this.lavagnaModel = lavagnaModel;
        this.figura = figura;
    }


    @Override
    public void execute() {

        FiguraSelezionataManager.getInstance().clear();
        lavagnaModel.rimuoviFigura(figura);
        System.out.println("Figura " + figura.toString() + " eliminata\n");
    }

    @Override
    public void undo() {
        lavagnaModel.aggiungiFigura(figura);
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}