package com.example.State;

import com.example.Command.Command;
import com.example.Command.Invoker;
import com.example.Command.SpostamentoFiguraCommand;
import com.example.Model.LavagnaModel;
import com.example.View.LavagnaView;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class SpostamentoFiguraStato implements Stato {

    double handle_x_iniziale, handle_y_iniziale;

    FiguraSelezionataManager figuraSelezionataManager = FiguraSelezionataManager.getInstance();


    @Override
    public void onMousePressed(MouseEvent event){

        Point2D punto = LavagnaView.getInstance().getFigureZoomabili().sceneToLocal(event.getSceneX(), event.getSceneY());
        handle_x_iniziale = punto.getX();
        handle_y_iniziale = punto.getY();
    }

    @Override
    public void onMouseDragged(MouseEvent event){

    }

    @Override
    public void onMouseReleased(MouseEvent event){

        Point2D punto = LavagnaView.getInstance().getFigureZoomabili().sceneToLocal(event.getSceneX(), event.getSceneY());
        double x2 = punto.getX();
        double y2 = punto.getY();

        if(x2<0 || y2<0) {
            return;
        }

        Command cmd = new SpostamentoFiguraCommand(figuraSelezionataManager.get(),x2,y2,handle_x_iniziale,handle_y_iniziale);
        Invoker.getInstance().executeCommand(cmd);
        StatoManager.getInstance().setStato(new SelezionaFiguraStato());


    }
}
