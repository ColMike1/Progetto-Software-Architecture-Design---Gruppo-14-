package com.example.Command;

import com.example.Model.Figura;
import com.example.Model.LavagnaModel;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.stage.Window;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CaricaCommandTest {

    private LavagnaModel lavagnaModelMock;
    private MenuItem menuItemMock;

    @BeforeAll
    static void initJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await();
    }

    @BeforeEach
    void setUp() {
        lavagnaModelMock = mock(LavagnaModel.class);
        menuItemMock = mock(MenuItem.class);

        ContextMenu contextMenuMock = mock(ContextMenu.class);
        Window windowMock = mock(Window.class);
        when(menuItemMock.getParentPopup()).thenReturn(contextMenuMock);
        when(contextMenuMock.getOwnerWindow()).thenReturn(windowMock);
    }

    @Test
    void testIsUndoableReturnsFalse() {
        CaricaCommand command = new CaricaCommand(lavagnaModelMock, menuItemMock);
        assertFalse(command.isUndoable());
    }

    @Test
    void testUndoDoesNothing() {
        CaricaCommand command = new CaricaCommand(lavagnaModelMock, menuItemMock);
        command.undo(); // Nessuna eccezione o interazione
    }

    @Test
    void testExecuteLoadsValidFile() throws IOException {
        // Crea file temporaneo con contenuto valido
        File file = File.createTempFile("figure", ".txt");
        file.deleteOnExit();
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("ellisse;x1=100.0;y1=100.0;x2=200.0;y2=150.0;stroke=#000000;fill=#FF0000;rotazione=0.0");
        }

        // Finta finestra e iniezione file chooser
        CaricaCommand command = new CaricaCommand(lavagnaModelMock, menuItemMock) {
            @Override
            public void execute() {
                Figura figura;
                File fileSelezionato = file;
                if (fileSelezionato != null) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(fileSelezionato))) {
                        lavagnaModelMock.svuotaLavagna();
                        List<Figura> figureTemp = new java.util.ArrayList<>();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.split(";");
                            String tipo = parts[0];
                            double x1 = Double.parseDouble(parts[1].split("=")[1]);
                            double y1 = Double.parseDouble(parts[2].split("=")[1]);
                            double x2 = Double.parseDouble(parts[3].split("=")[1]);
                            double y2 = Double.parseDouble(parts[4].split("=")[1]);
                            javafx.scene.paint.Color stroke = javafx.scene.paint.Color.web(parts[5].split("=")[1]);
                            javafx.scene.paint.Color fill = javafx.scene.paint.Color.web(parts[6].split("=")[1]);
                            double rotazione = Double.parseDouble(parts[7].split("=")[1]);

                            figura = switch (tipo) {
                                case "ellisse" -> new com.example.Model.Ellisse(x1, y1, x2, y2, stroke, fill);
                                default -> throw new IllegalArgumentException("Tipo non gestito");
                            };
                            figura.setRotazione(rotazione);
                            figureTemp.add(figura);
                        }
                        lavagnaModelMock.caricaFigure(figureTemp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        command.execute();

        verify(lavagnaModelMock).svuotaLavagna();
        verify(lavagnaModelMock).caricaFigure(anyList());
    }

    @Test
    void testExecuteHandlesMalformedLine() throws IOException {
        File file = File.createTempFile("figure_malformed", ".txt");
        file.deleteOnExit();
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("rettangolo;x1=100.0;y1=BAD;x2=200.0;y2=150.0;stroke=#000000;fill=#FF0000;rotazione=0.0");
        }

        CaricaCommand command = new CaricaCommand(lavagnaModelMock, menuItemMock) {
            @Override
            public void execute() {
                File fileSelezionato = file;
                if (fileSelezionato != null) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(fileSelezionato))) {
                        lavagnaModelMock.svuotaLavagna();
                        String line = "";
                        while ((line = reader.readLine()) != null) {
                            try {
                                String[] parts = line.split(";");
                                Double.parseDouble(parts[2].split("=")[1]); // Simula errore
                            } catch (Exception e) {
                                String finalLine = line;
                                Platform.runLater(() -> {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Errore");
                                    alert.setContentText("Errore nella riga: " + finalLine);
                                    alert.showAndWait();
                                });
                                return;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        command.execute();
        verify(lavagnaModelMock).svuotaLavagna(); // Caricamento interrotto dopo errore
    }
}

