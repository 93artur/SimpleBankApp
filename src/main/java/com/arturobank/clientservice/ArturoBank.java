package com.arturobank.clientservice;

import com.arturobank.datebase.AccountBase;
import com.arturobank.datebase.ClientBase;
import com.arturobank.viewlayer.ConsoleReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArturoBank {

    private Client myClient;
    AccountBase accountBase = new AccountBase();
    ClientBase clientBase = new ClientBase();
    private ClientOperation clientOperation = new ClientOperation();

    public String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return formatter.format(currentDateTime);
    }

    public static void main(String[] args) {
        System.out.println("*********** ARTURO BANK HAS STARTED ***********");
        ArturoBank bank = new ArturoBank();
        Client myClient = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ConsoleReader consoleReader = new ConsoleReader();
        bank.clientOperation.registerClient("Katy", 1, "Katy", bank.accountBase.createAccountNumber(), bank.clientBase, bank.accountBase);
        bank.clientOperation.registerClient("Egor", 2, "Egor", bank.accountBase.createAccountNumber(), bank.clientBase, bank.accountBase);
        ExitCommand exitCommand = new ExitCommand();
        ExitCommandOperation exitCommandOperation = new ExitCommandOperation(exitCommand);
        CommandsExecutor commandsExecutor;
        consoleReader.showStartCommands();
        int commandNumber;
        commandNumber = consoleReader.chooseStartCommand(reader);
        switch (commandNumber) {
            case 3:
                commandsExecutor = new CommandsExecutor(exitCommandOperation);
                commandsExecutor.executeCommand();
                break;
            case 2:
                consoleReader.registerClient(reader, bank.clientOperation, bank.clientBase, bank.accountBase);
                System.out.println("Log in!");
            case 1:
                myClient = consoleReader.logIn(reader, bank.clientBase);
                break;
        }
        if (myClient != null) {
            consoleReader.showClientCommands();
            commandNumber = consoleReader.chooseClientCommand(reader);
            switch (commandNumber) {
                case 2:
                    commandsExecutor = new CommandsExecutor(exitCommandOperation);
                    commandsExecutor.executeCommand();
                    break;
                case 1:
                    int fromCountNumber = myClient.getAccountNumber();
                    int toCountNumber = consoleReader.getRecipientAccountNumber(reader, bank.accountBase, myClient);
                    int amount = consoleReader.getAmountToSend(reader, myClient, bank.accountBase);
                    TransactionCommand transactionCommand = new TransactionCommand();
                    TransactionCommandOperation transactionCommandOperation =
                            new TransactionCommandOperation(transactionCommand, fromCountNumber, toCountNumber, amount,
                                    bank.clientBase, bank.accountBase);
                    commandsExecutor = new CommandsExecutor(transactionCommandOperation);
                    commandsExecutor.executeCommand();
            }
        }
        System.out.println("Good bye!");
    }
}