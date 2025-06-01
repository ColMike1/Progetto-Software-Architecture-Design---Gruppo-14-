package com.example.State;

import javafx.scene.input.MouseEvent;

public interface Stato {

    void onMousePressed(MouseEvent event);
    void onMouseDragged(MouseEvent event);
    void onMouseReleased(MouseEvent event);
    void onSliderChanged(double nuovoAngolo);
    void onSliderReleased(double angoloFinale);

}
