package com.arturobank.clientservice;

public class ExitCommandOperation implements Command {
    private ExitCommand exitCommand;
    public ExitCommandOperation(ExitCommand exitCommand){
        this.exitCommand = exitCommand;
    }
    @Override
    public void execute() {
       exitCommand.exit();
    }
}
