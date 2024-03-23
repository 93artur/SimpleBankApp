package com.arturobank;

import com.arturobank.clientservice.model.Client;
import com.arturobank.viewlayer.ConsoleControllerImp;

public class ArturoBank {
    public static void main(String[] args) {
        System.out.println("*********** ARTURO BANK HAS STARTED ***********");
        ConsoleControllerImp consoleControllerImp = new ConsoleControllerImp();
        consoleControllerImp.showStartCommands();
        Client myClient = null;
        int commandNumber;
        commandNumber = consoleControllerImp.chooseStartCommand();
        switch (commandNumber) {
            case 3:
                consoleControllerImp.exitCommand();
                break;
            case 2:
                myClient = consoleControllerImp.registerClient();
                break;
            case 1:
                myClient = consoleControllerImp.logIn();
                break;
        }
        if (myClient != null) {
            consoleControllerImp.showClientCommands(myClient.getClientName());
            commandNumber = consoleControllerImp.chooseClientCommand();
            switch (commandNumber) {
                case 2:
                    consoleControllerImp.exitCommand();
                    break;
                case 1:
                   consoleControllerImp.sendMoney(myClient);
            }
        }
        System.out.println("Good bye!");
    }
}