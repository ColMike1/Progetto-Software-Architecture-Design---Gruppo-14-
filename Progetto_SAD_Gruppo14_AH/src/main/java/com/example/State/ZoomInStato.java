package com.example.State;

import com.example.Command.Command;
import com.example.Command.Invoker;
import com.example.Command.ZoomInCommand;
import com.example.View.LavagnaView;
import javafx.scene.input.MouseEvent;

public class ZoomInStato implements Stato{

    private LavagnaView lavagnaView;

    public ZoomInStato(LavagnaView lavagnaView){
        this.lavagnaView = lavagnaView;
    }

    public void onMousePressed(MouseEvent event){

        double x = event.getX();
        double y = event.getY();

        Command cmd = new ZoomInCommand(lavagnaView, x, y);
        Invoker.getInstance().executeCommand(cmd);
    }

    public void onMouseDragged(MouseEvent event){}
    public void onMouseReleased(MouseEvent event){}

}
