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

    @Override
    public void onMousePressed(MouseEvent event){
        double x = event.getX();
        double y = event.getY();

        Command cmd = new ZoomInCommand(lavagnaView, x, y);
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
    @Override
    public void onMouseClicked(MouseEvent event) {
        // Non gestiamo il click in questo stato
    }

}
