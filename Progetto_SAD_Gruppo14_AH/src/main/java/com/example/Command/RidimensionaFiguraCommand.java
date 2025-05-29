package com.example.Command;

import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import com.example.State.FiguraSelezionataManager;

public class RidimensionaFiguraCommand implements Command {

    double x2, y2;
    double handle_x, handle_y;

    public RidimensionaFiguraCommand(double x2, double y2, double handle_x, double handle_y) {
        this.y2 = y2;
        this.x2 = x2;
        this.handle_x = handle_x;
        this.handle_y = handle_y;
    }

    double x1 = FiguraSelezionataManager.getInstance().get().getX1();
    double y1 = FiguraSelezionataManager.getInstance().get().getY1();

    Figura figura_selezionata = FiguraSelezionataManager.getInstance().get();


    public void execute() {

        if(x2<x1){
            x2 = x1+10;
        }

        if (y2<y1){
            y2 = y1+10;
        }
        LavagnaModel.getInstance().ridimensionaFigura(figura_selezionata, x2, y2);
    }


    @Override
    public void undo() {

        FiguraSelezionataManager.getInstance().clear();
        LavagnaModel.getInstance().ridimensionaFigura(figura_selezionata, handle_x, handle_y);

    }

    @Override
    public boolean isUndoable() {
        return true;
    }

}
