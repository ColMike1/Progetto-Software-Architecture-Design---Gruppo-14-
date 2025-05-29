package com.example.Command;

import com.example.Model.Figura;
import com.example.Model.LavagnaModel;

public class CopyCommand implements Command {

    private LavagnaModel lavagnaModel;
    private Figura figura;

    public CopyCommand(LavagnaModel lavagnaModel, Figura figura) {
        this.lavagnaModel = lavagnaModel;
        this.figura = figura;
    }

    public void execute(){
        lavagnaModel.setFiguraCopiata(figura);
        System.out.println("Copy " + figura.toString() + "\n");
    }
    public void undo(){}

    public boolean isUndoable(){
        return false;
    }


}
