package com.example.Command;


public interface Command {
    public void execute();
    public void undo();
    public boolean isUndoable();
    default boolean canExecute() {
        return true; // di default tutti i comandi sono eseguibili
    }
}


