/*import com.example.Command.SalvaFiguraCommand;
import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class SalvaFiguraCommandTest{
    @Test
    void testSalvataggioSenzaDialog() throws Exception {
        // Prepara modello con una figura concreta (crea una sottoclasse concreta di Figura)
        LavagnaModel lavagnaModel = LavagnaModel.getInstance();
        lavagnaModel.getFigure().clear();
        lavagnaModel.getFigure().add(new FiguraFinta(10, 20, 30, 40, Color.BLACK, Color.RED));

        // File temporaneo per il test
        File tempFile = File.createTempFile("testSalva", ".txt");
        tempFile.deleteOnExit();

        SalvaFiguraCommand cmd = new SalvaFiguraCommand(null, lavagnaModel) {
            @Override
            public void execute() {
                try (var writer = new java.io.FileWriter(tempFile)) {
                    for (Figura f : lavagnaModel.getFigure()) {
                        String tipo = f.getClass().getSimpleName().toLowerCase();
                        String line = tipo + ";" +
                                "x1=" + f.getX1() + ";" +
                                "y1=" + f.getY1() + ";" +
                                "x2=" + f.getX2() + ";" +
                                "y2=" + f.getY2() + ";" +
                                "stroke=" + f.getStrokeColor() + ";" +
                                "fill=" + f.getFillColor() + "\n";
                        writer.write(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    fail("Errore nel salvataggio file");
                }
            }
        };

        // Esegui il comando
        cmd.execute();

        // Leggi e verifica il contenuto
        String contenuto = Files.readString(tempFile.toPath());

        assertTrue(contenuto.contains("x1=10"));
        assertTrue(contenuto.contains("y1=20"));
        assertTrue(contenuto.contains("x2=30"));
        assertTrue(contenuto.contains("y2=40"));
        assertTrue(contenuto.contains("stroke=0x000000ff"));  // colore nero in formato CSS
        assertTrue(contenuto.contains("fill=0xff0000ff"));    // colore rosso in formato CSS
    }

}
*/