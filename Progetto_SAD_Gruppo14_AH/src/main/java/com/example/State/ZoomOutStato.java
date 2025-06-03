/*
* Classe che rappresenta lo stato di selezione del tasto di zoom '+'.
*
* Al momento del click sulla lavagna, onMousePressed chiama il comando ZoomOutCommand.
* Implementa l'interfaccia Stato.
*
* Autori:
*  - Michele
*/
package com.example.State;

import com.example.Command.Command;
import com.example.Command.Invoker;
import com.example.Command.ZoomOutCommand;
import com.example.View.LavagnaView;
import javafx.scene.input.MouseEvent;

public class ZoomOutStato implements Stato{


    @Override
    public void onMousePressed(MouseEvent event){
        double x = event.getX();
        double y = event.getY();

        LavagnaView lavagnaView = LavagnaView.getInstance();
        Command cmd = new ZoomOutCommand(lavagnaView, x, y);
        Invoker.getInstance().executeCommand(cmd);
    }

    @Override
    public void onMouseDragged(MouseEvent event){}

    @Override
    public void onMouseReleased(MouseEvent event){}
}
