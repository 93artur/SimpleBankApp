package com.arturobank.viewlayer;

import com.arturobank.clientservice.*;
import com.arturobank.datebase.*;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleReader {

    public boolean consistsOfLetters(String line) {
        if (line != null && line.matches("^[a-zA-Z]+$")) {
            return true;
        } else {
            System.out.println("Invalid format");
            return false;
        }
    }

    public void showClientCommands() {
        System.out.println("1. Execute transaction");
        System.out.println("2. Exit");
        System.out.println("Select a command from the list:");
    }

    public void showStartCommands() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.println("Enter your choice:");
    }

    public void registerClient(BufferedReader reader, ClientOperation clientOperation, ClientBase
            clientBase, AccountBase accountBase) {
        String userName;
        String clientName;
        int password;
        System.out.println("Enter Username:");
        while (true) {
            try {
                userName = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (consistsOfLetters(userName)) {
                Client client = clientBase.getClientsOfBank().get(userName);
                if (client == null) {
                    break;
                } else {
                    System.out.println("Username already exists. Set again:");
                }
            }
        }
        System.out.println("Enter password:");
        while (true) {
            String line;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                password = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Use only Integers");
                continue;
            }
            break;
        }
        System.out.println("Enter First Name:");
        while (true) {
            try {
                clientName = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (consistsOfLetters(clientName)) {
                break;
            }
        }
        Client client = new Client(userName, password, clientName, accountBase.createAccountNumber());
        clientBase.getClientsOfBank().put(userName, client);
        accountBase.getAccountsBase().put(client.getAccountNumber(), 1000);
        System.out.println("Client named " + clientName + " registered!");
    }

    public Client logIn(BufferedReader reader, ClientBase clientBase) {
        Client client = null;
        System.out.println("Enter Username:");
        String userName;
        while (true) {
            try {
                userName = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (!consistsOfLetters(userName)) {
                continue;
            }
            client = clientBase.getClientsOfBank().get(userName);
            if (client == null) {
                System.out.println("Username doesn't exist.");
                continue;
            }
            break;
        }
        System.out.println("Enter password:");
        int password;
        while (true) {
            try {
                password = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                System.out.println("Use only Integers");
                continue;
            }
            if (client.getAccountNumber() != password) {
                System.out.println("Wrong password.");
                continue;
            }
            System.out.println("Login done!");
            break;
        }
        return client;
    }

    public int chooseStartCommand(BufferedReader reader) {
        int commandNumber;
        while (true) {
            String line;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                commandNumber = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Use only Integers");
                continue;
            }
            if (commandNumber < 1 || commandNumber > 3) {
                System.out.println("Wrong number of command!");
                continue;
            }
            break;
        }
        return commandNumber;
    }

    public int chooseClientCommand(BufferedReader reader) {
        int commandNumber;
        while (true) {
            String line;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                commandNumber = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Use only Integers");
                continue;
            }
            if (commandNumber < 1 || commandNumber > 2) {
                System.out.println("Wrong number of command!");
                continue;
            }
            break;
        }
        return commandNumber;
    }

    public int getRecipientAccountNumber(BufferedReader reader, AccountBase accountBase, Client client) {
        int accountNumber = 0;
        System.out.println("Enter recipient account number:");
        while (true) {
            String line;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                accountNumber = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Use only Integers");
                continue;
            }
            if (!accountBase.getAccountsBase().containsKey(accountNumber)) {
                System.out.println("Recipient account number does not exist. Try again:");
                continue;
            }
            if (client.getAccountNumber() == accountNumber) {
                System.out.println("Don't choose your account number");
                continue;
            }
            break;
        }
        return accountNumber;
    }

    public int getAmountToSend(BufferedReader reader, Client client, AccountBase accountBase) {
        int amount = 0;
        System.out.println("Enter amount:");
        while (true) {
            String line;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                amount = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Use only Integers");
                continue;
            }
            if (amount > accountBase.getAccountsBase().get(client.getAccountNumber())) {
                System.out.println("You don't have enough funds");
                continue;
            }
            break;
        }
        return amount;
    }
}

