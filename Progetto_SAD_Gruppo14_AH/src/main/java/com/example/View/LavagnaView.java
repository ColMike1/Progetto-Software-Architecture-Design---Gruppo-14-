package com.example.View;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import com.example.State.Stato;
//import com.example.State.FiguraSelezionataManager;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;


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

                if(statoCorrente != null) {
                    statoCorrente.onFiguraClicked(f, nodo, event);

                }

                if(FiguraSelezionataManager.getInstance().get()==f) {

                }

            });*/


            lavagna.getChildren().add(nodo);

        }
    }


    @Override
    public void run() {
        aggiornaLavagna();
    }




}
