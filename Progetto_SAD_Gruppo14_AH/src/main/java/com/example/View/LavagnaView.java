package com.example.View;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class LavagnaView implements Runnable{

    private static LavagnaView instance;
    private LavagnaModel lavagnaModel;
    private AnchorPane lavagna;

    public static LavagnaView getInstance(LavagnaModel model, AnchorPane lavagna) {
        if (instance == null) {
            instance = new LavagnaView(model, lavagna);
        }
        return instance;
    }



    public LavagnaView(LavagnaModel model, AnchorPane lavagna){
        this.lavagnaModel = model;
        this.lavagna = lavagna;
        model.aggiungiOsservatore(this);
    }


    public void aggiornaLavagna(){
        lavagna.getChildren().clear();
        for (Figura f : lavagnaModel.getFigure()) {

            Node nodo = f.creaNodoJavaFX();

            /*nodo.setOnMouseClicked(event -> {
                // sbagliato secondo MVC, da gestire.
                lavagnaModel.setFiguraSelezionata(f);
                event.consume();
            });

            if (f == lavagnaModel.getFiguraSelezionata()) {
                DropShadow glow = new DropShadow(20, Color.PURPLE);
                nodo.setEffect(glow);
                System.out.println("ho selezionato");

            }*/

            lavagna.getChildren().add(nodo);

        }
    }


    @Override
    public void run() {
        aggiornaLavagna();
    }




}
