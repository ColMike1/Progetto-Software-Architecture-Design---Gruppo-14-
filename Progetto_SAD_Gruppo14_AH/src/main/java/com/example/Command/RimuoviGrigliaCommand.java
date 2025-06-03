/*
* Comando che rimuove la griglia dalla vista della lavagna.
*
* Agisce direttamente sulla LavagnaView per eliminare la griglia attualmente visualizzata.
* Non supporta l'operazione di annullamento ('undo').
*
* Autori:
*  -Michele
*/
package com.example.Command;

import com.example.Factory.FiguraFactory;
import com.example.Model.Griglia;
import com.example.Model.LavagnaModel;
import com.example.View.LavagnaView;
import javafx.scene.paint.Color;

public class RimuoviGrigliaCommand implements Command{



    public void execute(){
        LavagnaView.getInstance().rimuoviGriglia();
        System.out.println("Rimossa griglia\n");
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
