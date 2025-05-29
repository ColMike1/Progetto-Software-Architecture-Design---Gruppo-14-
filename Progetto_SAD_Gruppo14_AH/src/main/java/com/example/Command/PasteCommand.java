package com.example.Command;

import com.example.Model.Figura;
import com.example.Model.LavagnaModel;

public class PasteCommand implements Command {

    private LavagnaModel lavagnaModel;
    private Figura figuraIncollata;

    public PasteCommand(LavagnaModel lavagnaModel) {
        this.lavagnaModel = lavagnaModel;
    }

    public void execute(){
        Figura figuraCopiata = lavagnaModel.getFiguraCopiata();
        Figura figuraClonata = figuraCopiata.getClone();
        lavagnaModel.aggiungiFigura(figuraClonata);
        lavagnaModel.setFiguraCopiata(figuraClonata);
        figuraIncollata = figuraClonata;
        System.out.println("Paste " + figuraIncollata.toString() + "\n");
    }

    public void undo(){
        lavagnaModel.rimuoviFigura(figuraIncollata);
        System.out.println("Rimossa figura: " + figuraIncollata.toString() + " \n");
    }
    public boolean isUndoable(){
        return true;
    }


}
