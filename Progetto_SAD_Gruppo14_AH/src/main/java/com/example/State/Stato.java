package com.example.State;

import com.example.Model.Figura;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public interface Stato {

    void onMousePressed(MouseEvent event);
    void onMouseDragged(MouseEvent event);
    void onMouseReleased(MouseEvent event);

}
