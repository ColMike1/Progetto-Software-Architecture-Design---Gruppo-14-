/**
 * Stato dell'applicazione dedicato alla rotazione di un PoligonoArbitrario.
 *
 * Questo stato si attiva quando l'utente seleziona un poligono arbitrario e modifica il valore
 * dello slider di rotazione. A differenza delle figure classiche: rettangoli, ellissi e segmenti,
 * il poligono arbitrario è definito da una lista dinamica di vertici e non da un semplice
 * bounding box. Per questo motivo, la rotazione non può essere gestita applicando una trasformazione
 * diretta sui due punti principali, ma deve essere visualizzata tramite una figura temporanea.
 *
 * Funzionalità principali:
 * - onSliderChanged: crea e mostra una figura temporanea (in grigio semi-trasparente)
 *   che rappresenta la forma ruotata, fornendo un'anteprima visiva all'utente.
 * - onSliderReleased: rimuove la figura temporanea e applica la rotazione reale alla figura
 *   originale, utilizzando un RotazioneFiguraCommand per supportare undo.
 *
 * Questo stato incapsula la logica necessaria per trattare correttamente
 * la trasformazione di oggetti con geometria libera, distinguendosi così
 * dalla rotazione di figure standard, dove il centro e la forma sono più facilmente determinabili.
 */

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

public class RuotaPoligonoStato implements Stato {

    Figura figura = FiguraSelezionataManager.getInstance().get();
    Node figuraTemporaneaFX = null;
    FiguraTemporaneaStrategy strategy = figura.getTemporaryResizeStrategy();


    @Override
    public void onSliderChanged(double nuovoAngolo) {
        if (figuraTemporaneaFX == null) {
            // Crea la figura temporanea ruotabile

            PoligonoArbitrario p = (PoligonoArbitrario) figura;
            figuraTemporaneaFX = ((PoligonoArbitrarioStrategy) strategy).creaRotazionePoligono(p.getPunti(), nuovoAngolo);

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