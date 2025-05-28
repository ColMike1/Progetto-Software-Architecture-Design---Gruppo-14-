package com.example.View;
import com.example.Model.Figura;
import com.example.Model.Griglia;
import com.example.Model.LavagnaModel;
import com.example.State.*;
//import com.example.State.FiguraSelezionataManager;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class LavagnaView implements Runnable{


    private static LavagnaView instance;
    private AnchorPane lavagna;
    private Group figureZoomabili = new Group();
    private Circle handle;
    private Circle handle_1;
    Node griglia = new Group();


    public static LavagnaView getInstance(){
        return instance;
    }

    public static LavagnaView getInstance(AnchorPane lavagna) {
        if (instance == null) {
            instance = new LavagnaView(lavagna);
        }
        return instance;
    }

    public LavagnaView(AnchorPane lavagna){
        this.lavagna = lavagna;
        lavagna.getChildren().add(figureZoomabili); // Aggiungilo una volta sola
        LavagnaModel.getInstance().aggiungiOsservatore(this);
    }

    public void aggiungiGriglia(Griglia griglia) {
        this.griglia = griglia.creaNodoJavaFX();
        aggiornaLavagna();
    }

    public void rimuoviGriglia() {
        this.griglia = null;
        aggiornaLavagna();
    }

    public Group getFigureZoomabili() {
        return figureZoomabili;
    }


    private void aggiungiFiguraZoomabile(Node nodo){
        figureZoomabili.getChildren().add(nodo);
    }


    public void aggiornaLavagna(){

        handle = null;
        handle_1 = null;

        figureZoomabili.getChildren().clear();

        if (this.griglia != null)
            aggiungiFiguraZoomabile(griglia);


        for (Figura f : LavagnaModel.getInstance().getFigure()) {

            System.out.println("Sto aggiornando la lavagna.");

            Node nodo = f.creaNodoJavaFX();
            aggiungiFiguraZoomabile(nodo);

            nodo.setOnMouseClicked(event -> {
                FiguraSelezionataManager.getInstance().set(f);

            if(!((StatoManager.getInstance().getStato())instanceof SelezionaFiguraStato))
                StatoManager.getInstance().setStato(new SelezionaFiguraStato());

            });
        }

        // gestione figura selezionata, handle e toFront()
        if (FiguraSelezionataManager.getInstance().get() != null) {

            System.out.println("sono la figura selezionata");
            Figura f = FiguraSelezionataManager.getInstance().get();

          //f.getNodo().toFront();

                double hx = Math.max(f.getX1(), f.getX2());
                double hy = Math.max(f.getY1(), f.getY2());

                handle = new Circle(hx, hy, 5, Color.BROWN);
                handle.setCursor(Cursor.SE_RESIZE);
                //handle.toFront();

                aggiungiFiguraZoomabile(handle);

                handle.setOnMousePressed(event -> {

                    StatoManager.getInstance().setStato(new RidimensionaFiguraStato());
                    System.out.println("inizio a ridimensionare");
                });
                handle.setOnMouseReleased(event -> {
                    System.out.println("ho ridimensionato");
                });

                // gestione handle spostamento

                double hx_1 = Math.min(f.getX1(), f.getX2());
                double hy_1 = Math.min(f.getY1(), f.getY2());

                handle_1 = new Circle(hx_1, hy_1, 5, Color.GRAY);
                handle_1.setCursor(Cursor.MOVE);
                //handle_1.toFront();

                aggiungiFiguraZoomabile(handle_1);

                handle_1.setOnMousePressed(event -> {

                    StatoManager.getInstance().setStato(new SpostamentoFiguraStato());

                });
        }
    }



    @Override
    public void run() {
        aggiornaLavagna();
    }


}
