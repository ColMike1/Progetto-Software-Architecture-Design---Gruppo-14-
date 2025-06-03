/*
* Classe singleton 'Invoker' che gestisce l'esecuzione e l'annullamento dei comandi.
*
* Applica il pattern Command, eseguendo comandi tramite 'executeCommand()' e memorizzando
* quelli annullabili in uno stack per supportare l'operazione di undo.
*
* Autori:
* - Michele: crezione classe invoker con metodo executeCommand;
* - Kevin, Mirko: aggiornamento classe Invoker con operazioni di Undo e caricamento dei
*                 comandi nello stack dei comandi eseguiti.
*/

package com.example.Command;

import javafx.fxml.FXML;

import java.util.Stack;

public class Invoker {

    private static Invoker invokerInstance;
    private Stack<Command> undoStack = new Stack<>();

    public static Invoker getInstance(){
        if(invokerInstance == null){
            invokerInstance = new Invoker();
        }
        return invokerInstance;
    }

    public void undo(){
        if (!undoStack.isEmpty()) {
            Command cmd = undoStack.pop();
            cmd.undo();
        }
    }

    public void executeCommand(Command cmd){
        if(cmd.canExecute()) {
            cmd.execute();
            if(cmd.isUndoable())
                undoStack.push(cmd);
        }
    }

    public void svuotaStack(){
        undoStack.clear();
    }
}
