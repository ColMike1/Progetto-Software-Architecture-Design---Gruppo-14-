/*package com.example.State;

import com.example.Model.Figura;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class SelezionaFiguraState implements Stato {
    @Override
    public void onFiguraClicked(Figura f, Node nodo, MouseEvent event) {
        if((FiguraSelezionataManager.getInstance().get())==null){
            FiguraSelezionataManager.getInstance().set(f); // solo selezione
            //nodo.setEffect(new DropShadow(10, Color.PURPLE)); // visivo
            System.out.println("Ho selezionato");
        }
        else if (((FiguraSelezionataManager.getInstance().get())==f)) {
            //nodo.setEffect(null);
            FiguraSelezionataManager.getInstance().clear();
            System.out.println("Ho deselezionato");
        }
    }
}
*/