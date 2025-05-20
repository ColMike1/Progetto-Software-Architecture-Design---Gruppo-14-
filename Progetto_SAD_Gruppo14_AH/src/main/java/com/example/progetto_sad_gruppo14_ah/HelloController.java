package com.example.progetto_sad_gruppo14_ah;

import com.example.Command.*;
import com.example.Factory.EllisseFactory;
import com.example.Factory.FiguraFactory;
import com.example.Factory.RettangoloFactory;
import com.example.Factory.SegmentoFactory;
import com.example.Model.LavagnaModel;
import com.example.State.DisegnaRettangoloStato;
import com.example.View.LavagnaView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import com.example.State.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class HelloController{

    @FXML
    private AnchorPane lavagna;
    @FXML
    private ToggleButton segmentoButton;
    @FXML
    private ToggleButton ellisseButton;
    @FXML
    private ToggleButton rettangoloButton;
    @FXML
    private MenuItem salvaConNome;
    @FXML
    private MenuItem caricaFile;


    @FXML
    private ColorPicker strokeColorPicker;
    @FXML
    private ColorPicker fillColorPicker;


    private LavagnaModel lavagnaModel;
    private LavagnaView lavagnaView;

    private StatoManager statoManager = StatoManager.getInstance();




    @FXML
    private void initialize() {

        lavagnaModel = LavagnaModel.getInstance();
        lavagnaView = LavagnaView.getInstance(lavagnaModel, lavagna);

        rettangoloButton.setOnAction(e -> {
            if (rettangoloButton.isSelected()) {
                statoManager.setStato(new DisegnaRettangoloStato(lavagna, lavagnaModel, strokeColorPicker, fillColorPicker));

            } else {

                statoManager.setStato(null);
            }
        });

        segmentoButton.setOnAction(e -> {
            if (segmentoButton.isSelected()) {
                statoManager.setStato(new DisegnaSegmentoStato(lavagna, lavagnaModel, strokeColorPicker, fillColorPicker));

            } else {

                statoManager.setStato(null);
            }
        });


        ellisseButton.setOnAction(e -> {
            if (ellisseButton.isSelected()) {
                statoManager.setStato(new DisegnaEllisseStato(lavagna, lavagnaModel, strokeColorPicker, fillColorPicker));

            } else {
                System.out.println("ho deselezionato ellisse button");
                statoManager.setStato(null);;
            }
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

            System.out.println("FILE CARICATO");
        });


    }

}