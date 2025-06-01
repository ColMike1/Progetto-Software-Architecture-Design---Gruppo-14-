package com.example.State;

import com.example.Command.Command;
import com.example.Command.Invoker;
import com.example.Command.ZoomOutCommand;
import com.example.View.LavagnaView;
import javafx.scene.input.MouseEvent;

public class ZoomOutStato implements Stato{
    private LavagnaView lavagnaView;

    public ZoomOutStato(LavagnaView lavagnaView){
        this.lavagnaView = lavagnaView;
    }

    @Override
    public void onMousePressed(MouseEvent event){
        double x = event.getX();
        double y = event.getY();

        Command cmd = new ZoomOutCommand(lavagnaView, x, y);
        Invoker.getInstance().executeCommand(cmd);
    }

    @Override
    public void onMouseDragged(MouseEvent event){}

    @Override
    public void onMouseReleased(MouseEvent event){}

    @Override
    public void onSliderChanged(double sliderValue) {return;}
    @Override
    public void onSliderReleased(double sliderValue){return;}
}
