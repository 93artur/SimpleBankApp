package com.arturobank.clientservice;

public class CommandsExecutor {
    Command command;
    public CommandsExecutor(Command command){
        this.command = command;
    }
    public void executeCommand(){
        command.execute();
    }
}
