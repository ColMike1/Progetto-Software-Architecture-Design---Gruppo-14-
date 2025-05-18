package com.example.Command;

public class Invoker {

    private static Invoker invokerInstance;

    public static Invoker getInstance(){
        if(invokerInstance == null){
            invokerInstance = new Invoker();
        }
        return invokerInstance;
    }


    public void executeCommand(Command cmd){
        cmd.execute();
    }

}
