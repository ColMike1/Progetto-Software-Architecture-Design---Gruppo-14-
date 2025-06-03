/**
 * Stato dell'applicazione che gestisce la rotazione di una figura selezionata tramite uno slider.
 *
 * Quando l'utente interagisce con lo slider:
 *
 * Viene creata una rappresentazione temporanea della figura da ruotare.
 * La rotazione visiva viene aggiornata in tempo reale.
 * Alla conferma (rilascio dello slider), la rotazione viene applicata definitivamente tramite comando.
 * Autori: Kevin
 */

package com.example.State;

import com.example.Command.Command;
import com.example.Command.Invoker;
import com.example.Command.RotazioneFiguraCommand;
import com.example.Model.Figura;
import com.example.Strategy.FiguraTemporaneaStrategy;
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
        // Nessuna azione richiesta in questo stato
        return;
    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        // Nessuna azione richiesta in questo stato
        return;
    }

    @Override
    public void onMouseReleased(MouseEvent event) {
        // Nessuna azione richiesta in questo stato
        return;
    }
}