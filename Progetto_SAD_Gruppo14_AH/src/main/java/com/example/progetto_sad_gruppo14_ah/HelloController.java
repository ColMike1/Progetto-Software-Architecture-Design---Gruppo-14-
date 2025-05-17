package com.example.progetto_sad_gruppo14_ah;
import com.example.Factory.EllisseFactory;
import com.example.Factory.FiguraFactory;
import com.example.Factory.RettangoloFactory;
import com.example.Factory.SegmentoFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

public class HelloController{

    @FXML
    private AnchorPane lavagna;
    @FXML
    private ToggleButton segmentoButton;
    @FXML
    private ToggleButton ellisseButton;
    @FXML
    private ToggleButton rettangoloButton;



    private FiguraFactory figuraFactory;

    RettangoloFactory rettangoloFactory = new RettangoloFactory();
    EllisseFactory ellisseFactory = new EllisseFactory();
    SegmentoFactory segmentoFactory = new SegmentoFactory();


    @FXML
    private void initialize() {

        rettangoloButton.setOnAction(e -> {
            figuraFactory = rettangoloFactory;
        });

        segmentoButton.setOnAction(e -> {
           figuraFactory = segmentoFactory;
        });

        ellisseButton.setOnAction(e -> {
            figuraFactory = ellisseFactory;
        });

    }

}