package com.example.State;

import com.example.Command.Command;
import com.example.Command.Invoker;
import com.example.Command.RotazioneFiguraCommand;
import com.example.Model.Figura;
import com.example.Model.PoligonoArbitrario;
import com.example.Strategy.FiguraTemporaneaStrategy;
import com.example.Strategy.PoligonoArbitrarioStrategy;
import com.example.View.LavagnaView;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class RuotaFiguraStato implements Stato {

    Figura figura = FiguraSelezionataManager.getInstance().get();
    Node figuraTemporaneaFX = null;
    FiguraTemporaneaStrategy strategy = figura.getTemporaryResizeStrategy();


    @Override
    public void onSliderChanged(double nuovoAngolo) {
        if (figuraTemporaneaFX == null) {
            // Crea la figura temporanea ruotabile

            figuraTemporaneaFX = strategy.creaRotazione(figura.getX1(), figura.getY1(), figura.getX2(), figura.getY2());

            LavagnaView.getInstance().getFigureZoomabili().getChildren().add(figuraTemporaneaFX);
            figuraTemporaneaFX.setVisible(true);
            figuraTemporaneaFX.setOpacity(1.0);
            figuraTemporaneaFX.setRotate(nuovoAngolo);
        }

        // Applica la rotazione temporanea visiva
        strategy.aggiornaRotazione(figuraTemporaneaFX, nuovoAngolo);
    }
    @Override
    public void onSliderReleased(double angoloFinale) {
        // Rimuovi la figura temporanea
        if (figuraTemporaneaFX != null) {
            LavagnaView.getInstance().getFigureZoomabili().getChildren().remove(figuraTemporaneaFX);
            figuraTemporaneaFX = null;
        }

        // Esegui la rotazione definitiva
        Command cmd = new RotazioneFiguraCommand(figura, angoloFinale);
        Invoker.getInstance().executeCommand(cmd);

        StatoManager.getInstance().setStato(new SelezionaFiguraStato());
    }

    @Override
    public void onMousePressed(MouseEvent event) {
        return;
    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        return;
    }

    @Override
    public void onMouseReleased(MouseEvent event) {
       return;
    }

    @Override
    public void onMouseClicked(MouseEvent event) {
        // Non gestiamo il click in questo stato
    }
}