package com.example.Command;
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
