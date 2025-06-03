/* Verificare che creaNodoJavaFX():
*        - Ritorni un Group contenente il numero corretto di linee.
*        - Assegni correttamente lo strokeColor.
*        - Imposti this come userData del Group.
*/

package com.example.Model;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrigliaTest {

    @Test
    void testCreaNodoJavaFX_creaGrigliaCorretta() {
        // Arrange
        double nRighe = 4;
        double nColonne = 3;
        double larghezza = 300;
        double altezza = 200;
        Color coloreLinea = Color.GRAY;

        Griglia griglia = new Griglia(nRighe, nColonne, larghezza, altezza, coloreLinea);

        // Act
        Node node = griglia.creaNodoJavaFX();

        // Assert
        assertNotNull(node);
        assertTrue(node instanceof Group);

        Group group = (Group) node;
        // nRighe = 4 → 3 linee orizzontali (da 1 a 3)
        // nColonne = 3 → 2 linee verticali (da 1 a 2)
        int expectedLines = (int) (nRighe - 1 + nColonne - 1);
        assertEquals(expectedLines, group.getChildren().size());

        for (Node child : group.getChildren()) {
            assertTrue(child instanceof Line);
            Line line = (Line) child;
            assertEquals(coloreLinea, line.getStroke());
            assertEquals(1.0, line.getStrokeWidth());
        }

        assertEquals(griglia, group.getUserData());
    }
}

