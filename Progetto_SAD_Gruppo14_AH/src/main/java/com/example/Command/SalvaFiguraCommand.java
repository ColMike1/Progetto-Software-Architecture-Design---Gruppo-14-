package com.example.Command;

import com.example.Model.*;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SalvaFiguraCommand implements Command {
    final private LavagnaModel lavagnaModel;
    MenuItem salvaConNome;

    public SalvaFiguraCommand(MenuItem bottone, LavagnaModel lavagna) {

        this.salvaConNome = bottone;
        this.lavagnaModel = lavagna;
    }

    public void execute() {
        List<Figura> figure = lavagnaModel.getFigure(); // Recupera le figure
        if(figure.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informazione");
            alert.setHeaderText(null); // Nessun header
            alert.setContentText("Non ci sono figure da salvare!");
            alert.showAndWait();
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salva file come...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File di testo (*.txt)", "*.txt"));

        File file = fileChooser.showSaveDialog(salvaConNome.getParentPopup().getOwnerWindow());
        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                //throw new IOException("Errore simulato"); //Per Simulare l'errore.



                for (Figura f : figure) {
                    String tipo = f.getClass().getSimpleName().toLowerCase(); // esempio: "rettangolo"
                    String figura = switch (tipo) {
                        case "ellisse", "rettangolo", "segmento", "testo"->
                             tipo + ";" +
                                    "x1=" + f.getX1() + ";" +
                                    "y1=" + f.getY1() + ";" +
                                    "x2=" + f.getX2() + ";" +
                                    "y2=" + f.getY2() + ";" +
                                    "stroke=" + f.getStrokeColor() + ";" +
                                    "fill=" + f.getFillColor() + ";" +
                                     "rotazione=" + f.getRotazione();
                        case "poligonoarbitrario" -> {
                            StringBuilder sb = new StringBuilder(tipo).append(";");
                            for (Double punto : ((PoligonoArbitrario) f).getPunti()) {
                                sb.append(punto).append(";");
                            }
                            sb.append("stroke=").append(f.getStrokeColor()).append(";")
                                    .append("fill=").append(f.getFillColor()).append(";").append("rotazione=").append(f.getRotazione());
                            yield sb.toString();
                    }
                        default -> null;
                    };
                    if (f.getClass() == Testo.class){
                        figura += ";fontSize=" + ((Testo) f).getFontSize();
                        figura += ";content=" + ((Testo) f).getContenuto();
                    }
                    figura += "\n"; // Aggiungi una nuova riga dopo ogni figura
                    writer.write(figura);
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Salvataggio completato");
                alert.setHeaderText(null); // Nessun header
                alert.setContentText("Il file è stato salvato correttamente!");
                alert.showAndWait();
                System.out.println("Lavagna Salvata\n");

            } catch (IOException e) {
                e.printStackTrace();

                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Errore");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Errore nel salvataggio del file!");
                errorAlert.showAndWait();
            }

        }

    }
    @Override
    public void undo() {
        return;
    }
    @Override
    public boolean isUndoable() {
        return false;
    }
}
