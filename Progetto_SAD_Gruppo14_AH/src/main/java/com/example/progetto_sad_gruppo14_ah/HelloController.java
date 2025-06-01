package com.example.progetto_sad_gruppo14_ah;

import com.example.Command.*;
import com.example.Model.Figura;
import com.example.Factory.*;
import com.example.Model.LavagnaModel;
import com.example.View.LavagnaView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import com.example.State.*;
import javafx.scene.paint.Color;


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
    private ToggleButton testoButton;
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
    private MenuItem cut;
    @FXML
    private MenuItem copy;
    @FXML
    private MenuItem paste;
    @FXML
    private Menu menuEdit;
    @FXML
    private Slider sliderRotazione;


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
        sliderRotazione.setMin(0);
        sliderRotazione.setMax(360);

        // disabilito menuItems
        /*Elimina.setDisable(true);
        cut.setDisable(true);
        copy.setDisable(true);*/

        copy.setAccelerator(new KeyCodeCombination(
                KeyCode.C,
                KeyCombination.CONTROL_DOWN
        ));

        Elimina.setAccelerator(new KeyCodeCombination(
                KeyCode.D,
                KeyCombination.CONTROL_DOWN
        ));

        cut.setAccelerator(new KeyCodeCombination(
                KeyCode.X,
                KeyCombination.CONTROL_DOWN
        ));
        paste.setAccelerator(new KeyCodeCombination(
                KeyCode.V,
                KeyCombination.CONTROL_DOWN
        ));


        lavagna.heightProperty().addListener((observable, oldValue, newValue) -> {
            Command cmd = new AggiungiGrigliaCommand(Integer.parseInt(nRighe.getText()), Integer.parseInt(nColonne.getText()), lavagna.getWidth(), (double) newValue, Color.LIGHTGRAY);
            if(grigliaButton.isSelected()){
                Invoker.getInstance().executeCommand(cmd);
            }
            else {
                cmd = new RimuoviGrigliaCommand();
                Invoker.getInstance().executeCommand(cmd);
            }

        });

        lavagna.widthProperty().addListener((observable, oldValue, newValue) -> {
            Command cmd = new AggiungiGrigliaCommand(Integer.parseInt(nRighe.getText()), Integer.parseInt(nColonne.getText()), (double) newValue, lavagna.getHeight(), Color.LIGHTGRAY);
            if(grigliaButton.isSelected()){
                Invoker.getInstance().executeCommand(cmd);
            }
            else {
                cmd = new RimuoviGrigliaCommand();
                Invoker.getInstance().executeCommand(cmd);
            }
        });

        nRighe.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int nRighe = Integer.parseInt(newValue);
                Command cmd = new AggiungiGrigliaCommand( nRighe, Integer.parseInt(nColonne.getText()), lavagna.getWidth(), lavagna.getHeight(), Color.LIGHTGRAY);
                if(grigliaButton.isSelected()){
                    Invoker.getInstance().executeCommand(cmd);}
            } catch (NumberFormatException e) {
                System.out.println("Valore non numerico");
            }
        });

        nColonne.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int nColonne = Integer.parseInt(newValue);
                Command cmd = new AggiungiGrigliaCommand(Integer.parseInt(nRighe.getText()), nColonne, lavagna.getWidth(), lavagna.getHeight(), Color.LIGHTGRAY);
                if(grigliaButton.isSelected()){
                    Invoker.getInstance().executeCommand(cmd);}
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
                statoManager.setStato(new DisegnaFiguraStato(new RettangoloFactory(), lavagna, lavagnaModel, strokeColorPicker, fillColorPicker));
                System.out.println("Selezionato rettangolo button\n");
            } else {
                statoManager.setStato(new IdleStato());
                System.out.println("Deselezionato rettangolo button\n");
            }
        });


        segmentoButton.setOnAction(e -> {
            if (segmentoButton.isSelected()) {
                statoManager.setStato(new DisegnaFiguraStato(new SegmentoFactory(),lavagna, lavagnaModel, strokeColorPicker, fillColorPicker));
                System.out.println("Selezionato segmento button\n");
            } else {
                System.out.println("Deselezionato segmento button\n");
                statoManager.setStato(new IdleStato());
            }
        });


        ellisseButton.setOnAction(e -> {
            if (ellisseButton.isSelected()) {
                statoManager.setStato(new DisegnaFiguraStato(new EllisseFactory(), lavagna, lavagnaModel, strokeColorPicker, fillColorPicker));
                System.out.println("Selezionato ellisse button\n");
            } else {
                System.out.println("Deselezionato ellisse button");
                statoManager.setStato(new IdleStato());;
            }
        });

        grigliaButton.setOnAction(event -> {
            try{
                if(grigliaButton.isSelected())
                    Invoker.getInstance().executeCommand(new AggiungiGrigliaCommand(Integer.parseInt(nRighe.getText()), Integer.parseInt(nColonne.getText()), lavagna.getWidth(), lavagna.getHeight(), Color.LIGHTGRAY));
                else
                    Invoker.getInstance().executeCommand(new RimuoviGrigliaCommand());
            }
            catch(Exception e){
                System.out.println("Numero righe (colonne) non valido\n");
            }
        });

        testoButton.setOnAction(e -> {
            if (testoButton.isSelected()) {
                statoManager.setStato(new InserisciTestoStato(lavagna, lavagnaModel, strokeColorPicker, fillColorPicker));
            } else {
                statoManager.setStato(new IdleStato());;
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

        sliderRotazione.setOnMousePressed(event ->{
            statoManager.setStato(new RuotaFiguraStato());
        });

        sliderRotazione.valueProperty().addListener((obs, oldVal, newVal) -> {
            statoManager.getStato().onSliderChanged(newVal.doubleValue());
        });

        sliderRotazione.setOnMouseReleased(e -> {
            statoManager.getStato().onSliderReleased(sliderRotazione.getValue());
        });

        salvaConNome.setOnAction(e ->{

            Command cmd = new SalvaFiguraCommand(salvaConNome, lavagnaModel);

            Invoker.getInstance().executeCommand(cmd);


        });

        caricaFile.setOnAction(e->{
            Command cmd = new CaricaCommand(lavagnaModel, caricaFile);

            Invoker.getInstance().executeCommand(cmd);
            Invoker.getInstance().svuotaStack();

        });

        spostaSopraButton.setOnAction(e -> {
            if(figuraSelezionataManager.get() != null){
                Command cmd = new SpostaSopraCommand(lavagnaModel, figuraSelezionataManager.get());

                Invoker.getInstance().executeCommand(cmd);


            }
        });

        spostaSottoButton.setOnAction(e -> {
            if(figuraSelezionataManager.get() != null){
                Command cmd = new SpostaSottoCommand(lavagnaModel, figuraSelezionataManager.get());

                Invoker.getInstance().executeCommand(cmd);


            }
        });

        fillColorPicker.setOnAction(e -> {
            if(figuraSelezionataManager.get() != null){
                Command cmd = new CambiaColoreInternoCommand(lavagnaModel, figuraSelezionataManager.get(), fillColorPicker.getValue());

                Invoker.getInstance().executeCommand(cmd);



            }
        });

        strokeColorPicker.setOnAction(e -> {
            if(figuraSelezionataManager.get() != null){
                Command cmd = new CambiaColoreBordoCommand(lavagnaModel, figuraSelezionataManager.get(), strokeColorPicker.getValue());

                Invoker.getInstance().executeCommand(cmd);

            }
        });

        undoButton.setOnAction(e -> {
            Invoker.getInstance().undo();
            figuraSelezionataManager.clear();
        });

        /*menuEdit.setOnShown(e -> {
            Figura figura = figuraSelezionataManager.get();
            if(figura != null) {
                Elimina.setDisable(false);
                cut.setDisable(false);
                copy.setDisable(false);
            }
        });*/

       /* menuEdit.setOnHidden(e -> {
            Elimina.setDisable(true);
            cut.setDisable(true);
            copy.setDisable(true);
        });*/

        Elimina.setOnAction(e->{
            Figura figura = figuraSelezionataManager.get();
            if(figura != null) {
                Command cmd = new EliminaCommand(lavagnaModel, figura);
                Invoker.getInstance().executeCommand(cmd);
            }
            else
                System.out.println("Nessuna figura selezionata");
        });

        cut.setOnAction(e->{
            Figura figura = figuraSelezionataManager.get();
            if(figura != null) {
                Command cmd = new CutCommand(lavagnaModel, figura);
                Invoker.getInstance().executeCommand(cmd);
            }
            else
                System.out.println("Nessuna figura selezionata");
        });


        copy.setOnAction(event->{
            Figura figura = figuraSelezionataManager.get();
            if(figura != null) {
                Command cmd = new CopyCommand(lavagnaModel, figura);
                Invoker.getInstance().executeCommand(cmd);
            }
            else
                System.out.println("Nessuna figura selezionata");

        });

        paste.setOnAction(event->{
            try{
                Command cmd = new PasteCommand(lavagnaModel);
                Invoker.getInstance().executeCommand(cmd);
            }
            catch(Exception e){
                System.out.println("Nessuna figura copiata");
            }
        });

}
}