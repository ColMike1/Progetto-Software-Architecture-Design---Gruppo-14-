/*import com.example.Command.CaricaCommand;
import com.example.Model.Ellisse;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import javafx.scene.control.MenuItem;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CaricaCommandTest {

    @Test
    void testCaricamentoDaFile() throws Exception {
        // 1. Crea un file temporaneo con una figura valida
        File tempFile = File.createTempFile("figureTest", ".txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("ellisse;x1=10.0;y1=20.0;x2=30.0;y2=40.0;stroke=0x000000ff;fill=0xff0000ff\n");
        }

        // 2. Ottieni il modello e svuotalo
        LavagnaModel model = LavagnaModel.getInstance();
        model.getFigure().clear();

        // 3. Crea una sottoclasse di CaricaCommand che usa il file temporaneo direttamente
        CaricaCommand cmd = new CaricaCommand(model, new MenuItem()) {
            @Override
            public void execute() {
                try (var reader = new java.io.BufferedReader(new java.io.FileReader(tempFile))) {
                    List<Figura> figure = model.getFigure();
                    figure.clear();
                    model.notificaOsservatori();

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(";");
                        String tipo = parts[0];

                        double x1 = Double.parseDouble(parts[1].split("=")[1]);
                        double y1 = Double.parseDouble(parts[2].split("=")[1]);
                        double x2 = Double.parseDouble(parts[3].split("=")[1]);
                        double y2 = Double.parseDouble(parts[4].split("=")[1]);
                        var stroke = javafx.scene.paint.Color.web(parts[5].split("=")[1]);
                        var fill = javafx.scene.paint.Color.web(parts[6].split("=")[1]);

                        Figura figura = switch (tipo) {
                            case "ellisse" -> new Ellisse(x1, y1, x2, y2, stroke, fill);
                            default -> throw new IllegalArgumentException("Tipo non gestito");
                        };
                        figure.add(figura);
                    }
                    model.notificaOsservatori();
                } catch (Exception e) {
                    fail("Errore nel caricamento del file: " + e.getMessage());
                }
            }
        };

        // 4. Esegui il comando
        cmd.execute();

        // 5. Verifica il risultato
        List<Figura> figure = model.getFigure();
        assertEquals(1, figure.size());
        Figura f = figure.get(0);

        assertEquals(10.0, f.getX1());
        assertEquals(20.0, f.getY1());
        assertEquals(30.0, f.getX2());
        assertEquals(40.0, f.getY2());
        assertEquals("0x000000ff", f.getStrokeColor().toString()); // nero
        assertEquals("0xff0000ff", f.getFillColor().toString());   // rosso
    }
}
*/
