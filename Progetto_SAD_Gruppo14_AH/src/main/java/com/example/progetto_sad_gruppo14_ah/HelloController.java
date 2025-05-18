package com.example.progetto_sad_gruppo14_ah;

import com.example.Command.*;
import com.example.Factory.EllisseFactory;
import com.example.Factory.FiguraFactory;
import com.example.Factory.RettangoloFactory;
import com.example.Factory.SegmentoFactory;
import com.example.Model.LavagnaModel;
import com.example.View.LavagnaView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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

    private double x1, y1;



    private FiguraFactory figuraFactory;

    RettangoloFactory rettangoloFactory = new RettangoloFactory();
    EllisseFactory ellisseFactory = new EllisseFactory();
    SegmentoFactory segmentoFactory = new SegmentoFactory();


    @FXML
    private void initialize() {

        lavagnaModel = LavagnaModel.getInstance();
        lavagnaView = LavagnaView.getInstance(lavagnaModel, lavagna);

        rettangoloButton.setOnAction(e -> {
            if (rettangoloButton.isSelected()) {
                figuraFactory = rettangoloFactory;

            } else {
                figuraFactory = null;
            }
        });

        segmentoButton.setOnAction(e -> {
            if (segmentoButton.isSelected()) {
                figuraFactory = segmentoFactory;

            } else {
                figuraFactory = null;
            }
        });


        ellisseButton.setOnAction(e -> {
            if (ellisseButton.isSelected()) {
                figuraFactory = ellisseFactory;

            } else {
                figuraFactory = null;
            }
        });

        lavagna.setOnMousePressed(event ->{
            x1 = event.getX();
            y1 = event.getY();
        });


        lavagna.setOnMouseReleased(event ->{
            double x2 = event.getX();
            double y2 = event.getY();

            if(figuraFactory!=null) {

                Command cmd = new AggiungiFiguraCommand(lavagnaModel, figuraFactory, x1, y1, x2, y2, strokeColorPicker.getValue(), fillColorPicker.getValue());

                Invoker.getInstance().executeCommand(cmd);

                System.out.println("FIGURA CREATA");
            }
        });

        salvaConNome.setOnAction(e ->{

            Command cmd = new SalvaFiguraCommand(salvaConNome, lavagnaModel);

            Invoker.getInstance().executeCommand(cmd);

            System.out.println("FIGURA SALVATA");

        });


    }

}