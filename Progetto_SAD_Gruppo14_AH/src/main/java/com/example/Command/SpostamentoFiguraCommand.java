package com.example.Command;

import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import com.example.Model.PoligonoArbitrario;
import com.example.State.FiguraSelezionataManager;

import java.util.ArrayList;
import java.util.List;

public class SpostamentoFiguraCommand implements Command {

    private Figura figura;
    private double x1;
    private double y1;
    private double handle_x;
    private double handle_y;


    public SpostamentoFiguraCommand(Figura figura, double x1, double y1, double handle_x, double handle_y) {
        this.figura = figura;
        this.x1 = x1;
        this.y1 = y1;
        this.handle_x = handle_x;
        this.handle_y = handle_y;
    }

    Figura figura_selezionata = FiguraSelezionataManager.getInstance().get();

    @Override

    public void execute() {
        if(figura_selezionata.getClass()== PoligonoArbitrario.class)
            LavagnaModel.getInstance().spostaPoligono((PoligonoArbitrario) figura_selezionata, x1, y1);
        else
            LavagnaModel.getInstance().spostaFigura(figura, x1, y1);
        System.out.println("Figura " + figura.toString() + " spostata\n");
    }
    @Override
    public void undo() {
        if(figura_selezionata.getClass()== PoligonoArbitrario.class) {
            FiguraSelezionataManager.getInstance().clear();
            LavagnaModel.getInstance().spostaPoligono((PoligonoArbitrario) figura_selezionata, handle_x, handle_y);
        }else {
            FiguraSelezionataManager.getInstance().clear();
            LavagnaModel.getInstance().spostaFigura(figura_selezionata, handle_x, handle_y);
        }
        System.out.println("Undo: Figura " + figura.toString() + " spostata\n");
    }
    @Override
    public boolean isUndoable() {
        return true;
    }
}
