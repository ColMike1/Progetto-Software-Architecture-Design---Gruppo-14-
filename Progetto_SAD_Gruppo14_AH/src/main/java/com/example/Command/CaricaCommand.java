package com.example.Command;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.Model.*;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;


public class CaricaCommand implements Command {

    private LavagnaModel lavagnaModel;
    private MenuItem apriFile;


    public CaricaCommand(LavagnaModel lavagnaModel, MenuItem apriFile) {
        this.lavagnaModel = lavagnaModel;
        this.apriFile = apriFile;
    }

    @Override
    public void execute() {
        Figura figura;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleziona un file .txt");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showOpenDialog(apriFile.getParentPopup().getOwnerWindow());
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                List<Figura> figure = lavagnaModel.getFigure();
                figure.clear();
                lavagnaModel.notificaOsservatori();
                List<Figura> figureTemp = new ArrayList<>();
                boolean fileVuoto = true;
                String line;
                while ((line = reader.readLine()) != null) {
                    fileVuoto = false;

                    try {
                        String[] parts = line.split(";");
                        String tipo = parts[0];

                        double x1 = Double.parseDouble(parts[1].split("=")[1]);
                        double y1 = Double.parseDouble(parts[2].split("=")[1]);
                        double x2 = Double.parseDouble(parts[3].split("=")[1]);
                        double y2 = Double.parseDouble(parts[4].split("=")[1]);
                        Color stroke = Color.web(parts[5].split("=")[1]);
                        Color fill = Color.web(parts[6].split("=")[1]);

                        figura = switch (tipo) {
                            case "ellisse" -> new Ellisse(x1, y1, x2, y2, stroke, fill);
                            case "rettangolo" -> new Rettangolo(x1, y1, x2, y2, stroke, fill);
                            case "segmento" -> new Segmento(x1, y1, x2, y2, stroke, fill);
                            default -> throw new IllegalArgumentException("Tipo di tipo non valido"+tipo);
                        };

                        figureTemp.add(figura);

                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Errore di caricamento");
                        alert.setHeaderText("Formattazione del file non valida!");

                        alert.showAndWait();
                        return;
                    }

                }
                if (fileVuoto) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("File vuoto");
                    alert.setHeaderText("Il file selezionato è vuoto");
                    alert.setContentText("Nessuna figura è stata caricata.");
                    alert.showAndWait();
                } else {

                    figure.addAll(figureTemp);
                    lavagnaModel.notificaOsservatori();
                }

            } catch (IOException e) {
                e.printStackTrace();
                lavagnaModel.getFigure().clear();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore di lettura");
                alert.setHeaderText("Impossibile leggere il file");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }
}

