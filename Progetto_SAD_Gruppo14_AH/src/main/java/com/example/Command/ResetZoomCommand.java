package com.example.Command;
import com.example.View.LavagnaView;
import javafx.scene.Group;

public class ResetZoomCommand implements Command {
    private Group figureInserite;

    public ResetZoomCommand(LavagnaView view) {
        this.figureInserite = view.getFigureZoomabili();
    }

    @Override
    public void execute() {
        figureInserite.setScaleX(1.0);
        figureInserite.setScaleY(1.0);
        figureInserite.setTranslateX(0);
        figureInserite.setTranslateY(0);
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