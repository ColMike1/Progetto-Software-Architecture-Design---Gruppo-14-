package com.example.State;

import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;


public class SelezionaFiguraStato implements Stato {


    FiguraSelezionataManager figuraSelezionataManager = FiguraSelezionataManager.getInstance();



    public SelezionaFiguraStato() {

        LavagnaModel.getInstance().notificaOsservatori();

    }

    @Override
    public void onMousePressed(MouseEvent event){
        Object source = event.getTarget();

        if (source instanceof Node node && node.getUserData() instanceof Figura figura) {

            if (figuraSelezionataManager.get() == figura) {
                figuraSelezionataManager.clear();
                LavagnaModel.getInstance().deselezionaFigura(figura);
                System.out.println("Figura deselezionata: " + figura.toString() + "\n");
            } else {
                figuraSelezionataManager.set(figura);
                LavagnaModel.getInstance().selezionaFigura(figura);
                System.out.println("Figura selezionata: " + figura.toString() + "\n");
            }
        } else if(!(source instanceof Circle circle))  {
            // click sulla lavagna → deseleziona
            Figura figuraSelezionata = figuraSelezionataManager.get();
            if (figuraSelezionata != null) {
                System.out.println("Figura deselezionata: " + figuraSelezionata.toString() + "\n");
                figuraSelezionataManager.clear();
                LavagnaModel.getInstance().deselezionaFigura(figuraSelezionata);
            }
        }
    }

    @Override
    public void onMouseDragged(MouseEvent event){
        return;
    }

    @Override
    public void onMouseReleased(MouseEvent event){
        return;
    }


}