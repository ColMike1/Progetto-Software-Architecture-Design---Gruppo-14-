package com.example.progetto_sad_gruppo14_ah;

import com.example.Command.*;
import com.example.Model.Figura;
import com.example.Factory.*;
import com.example.Model.LavagnaModel;
import com.example.State.DisegnaRettangoloStato;
import com.example.View.LavagnaView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import com.example.State.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class HelloController{

    @FXML
    private AnchorPane lavagna;
    @FXML
    private TextField nRighe;
    @FXML
    private TextField nColonne;
    @FXML
    private ToggleButton zoom_in;
    @FXML
    private ToggleButton zoom_out;
    @FXML
    private Button resetZoomButton;
    @FXML
    private ToggleButton segmentoButton;
    @FXML
    private ToggleButton ellisseButton;
    @FXML
    private ToggleButton rettangoloButton;
    @FXML
    private ToggleButton grigliaButton;
    @FXML
    private MenuItem salvaConNome;
    @FXML
    private MenuItem caricaFile;
    @FXML
    private Button spostaSopraButton;
    @FXML
    private Button spostaSottoButton;
    @FXML
    private Button undoButton;
    @FXML
    private MenuItem Elimina;
    @FXML
    private Menu menuEdit;


    @FXML
    private ColorPicker strokeColorPicker;
    @FXML
    private ColorPicker fillColorPicker;


    private LavagnaModel lavagnaModel;
    private LavagnaView lavagnaView;
    private FiguraSelezionataManager figuraSelezionataManager = FiguraSelezionataManager.getInstance();

    private StatoManager statoManager = StatoManager.getInstance();




    @FXML
    private void initialize() {

        lavagnaModel = LavagnaModel.getInstance();
        lavagnaView = LavagnaView.getInstance(lavagna);
        Elimina.setDisable(true);

        Invoker.getInstance().executeCommand(new RimuoviGrigliaCommand());


        lavagna.heightProperty().addListener((observable, oldValue, newValue) -> {
            Command cmd = new AggiungiGrigliaCommand(Integer.parseInt(nRighe.getText()), Integer.parseInt(nColonne.getText()), lavagna.getWidth(), (double) newValue, Color.LIGHTGRAY);
            if(grigliaButton.isSelected()){
                cmd.execute();}
        });

        lavagna.widthProperty().addListener((observable, oldValue, newValue) -> {
            Command cmd = new AggiungiGrigliaCommand(Integer.parseInt(nRighe.getText()), Integer.parseInt(nColonne.getText()), (double) newValue, lavagna.getHeight(), Color.LIGHTGRAY);
            if(grigliaButton.isSelected()){
                cmd.execute();}
        });

        nRighe.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int nRighe = Integer.parseInt(newValue);
                Command cmd = new AggiungiGrigliaCommand( nRighe, Integer.parseInt(nColonne.getText()), lavagna.getWidth(), lavagna.getHeight(), Color.LIGHTGRAY);
                if(grigliaButton.isSelected()){
                    cmd.execute();}
            } catch (NumberFormatException e) {
                System.out.println("Valore non numerico");
            }
        });

        nColonne.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int nColonne = Integer.parseInt(newValue);
                Command cmd = new AggiungiGrigliaCommand(Integer.parseInt(nRighe.getText()), nColonne, lavagna.getWidth(), lavagna.getHeight(), Color.LIGHTGRAY);
                if(grigliaButton.isSelected()){
                    cmd.execute();}
            } catch (NumberFormatException e) {
                System.out.println("Valore non numerico");
            }
        });

        zoom_in.setOnAction((e) -> {
            if (zoom_in.isSelected())
                statoManager.setStato(new ZoomInStato(lavagnaView));
            else
                statoManager.setStato(new IdleStato());
        });

        zoom_out.setOnAction((e) -> {
            if (zoom_out.isSelected())
                statoManager.setStato(new ZoomOutStato(lavagnaView));
            else
                statoManager.setStato(new IdleStato());
        });

        resetZoomButton.setOnAction((event) -> {
            Invoker.getInstance().executeCommand(new ResetZoomCommand(lavagnaView));
        });

        rettangoloButton.setOnAction(e -> {
            if (rettangoloButton.isSelected()) {
                statoManager.setStato(new DisegnaRettangoloStato(lavagna, lavagnaModel, strokeColorPicker, fillColorPicker));
            } else {

                statoManager.setStato(new IdleStato());
            }
        });


        segmentoButton.setOnAction(e -> {
            if (segmentoButton.isSelected()) {
                statoManager.setStato(new DisegnaSegmentoStato(lavagna, lavagnaModel, strokeColorPicker, fillColorPicker));
            } else {

                statoManager.setStato(new IdleStato());
            }
        });


        ellisseButton.setOnAction(e -> {
            if (ellisseButton.isSelected()) {
                statoManager.setStato(new DisegnaEllisseStato(lavagna, lavagnaModel, strokeColorPicker, fillColorPicker));
            } else {
                System.out.println("ho deselezionato ellisse button");
                statoManager.setStato(new IdleStato());;
            }
        });

        grigliaButton.setOnAction(e -> {
            if(grigliaButton.isSelected())
                Invoker.getInstance().executeCommand(new AggiungiGrigliaCommand(Integer.parseInt(nRighe.getText()), Integer.parseInt(nColonne.getText()), lavagna.getWidth(), lavagna.getHeight(), Color.LIGHTGRAY));
            else
                Invoker.getInstance().executeCommand(new RimuoviGrigliaCommand());
            });


        lavagna.setOnMousePressed(event ->{
            statoManager.getStato().onMousePressed(event);
        });

        lavagna.setOnMouseDragged(event ->{
            statoManager.getStato().onMouseDragged(event);

        });

        lavagna.setOnMouseReleased(event ->{
            statoManager.getStato().onMouseReleased(event);
        });

        salvaConNome.setOnAction(e ->{

            Command cmd = new SalvaFiguraCommand(salvaConNome, lavagnaModel);

            Invoker.getInstance().executeCommand(cmd);

            System.out.println("FIGURA SALVATA");

        });

        caricaFile.setOnAction(e->{
            Command cmd = new CaricaCommand(lavagnaModel, caricaFile);

            Invoker.getInstance().executeCommand(cmd);
            Invoker.getInstance().svuotaStack();

            System.out.println("FILE CARICATO");
        });

        spostaSopraButton.setOnAction(e -> {
            if(figuraSelezionataManager.get() != null){
                Command cmd = new SpostaSopraCommand(lavagnaModel, figuraSelezionataManager.get());

                Invoker.getInstance().executeCommand(cmd);

                System.out.println("TO THE TOP");
            }
        });

        spostaSottoButton.setOnAction(e -> {
            if(figuraSelezionataManager.get() != null){
                Command cmd = new SpostaSottoCommand(lavagnaModel, figuraSelezionataManager.get());

                Invoker.getInstance().executeCommand(cmd);

                System.out.println("TO THE BACK");
            }
        });

        fillColorPicker.setOnAction(e -> {
            if(figuraSelezionataManager.get() != null){
                Command cmd = new CambiaColoreInternoCommand(lavagnaModel, figuraSelezionataManager.get(), fillColorPicker.getValue());

                Invoker.getInstance().executeCommand(cmd);

                System.out.println("CAMBIO COLORE INTERNO");

            }
        });

        strokeColorPicker.setOnAction(e -> {
            if(figuraSelezionataManager.get() != null){
                Command cmd = new CambiaColoreBordoCommand(lavagnaModel, figuraSelezionataManager.get(), strokeColorPicker.getValue());

                Invoker.getInstance().executeCommand(cmd);

                System.out.println("CAMBIO COLORE BORDO");
            }
        });

        undoButton.setOnAction(e -> {
            Invoker.getInstance().undo();
            figuraSelezionataManager.clear();
        });

        menuEdit.setOnShown(e -> {
            Figura figura = figuraSelezionataManager.get();
            if(figura != null) {
                Elimina.setDisable(false);
            }
        });

        menuEdit.setOnHidden(e -> {
            Elimina.setDisable(true);
        });

        Elimina.setOnAction(e->{
            Figura figura = figuraSelezionataManager.get();
            if(figura != null) {
                Command cmd = new EliminaCommand(lavagnaModel, figura);
                Invoker.getInstance().executeCommand(cmd);
                System.out.println("FIGURA ELIMINATA");
    }
        });

}
}