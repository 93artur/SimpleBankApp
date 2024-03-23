package com.arturobank.viewlayer;

import com.arturobank.clientservice.ClientServiceIml;
import com.arturobank.clientservice.model.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleControllerImp implements ConsoleController {
    private ClientServiceIml clientServiceIml = new ClientServiceIml();
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private boolean consistsOfLetters(String line) {
        if (line != null && line.matches("^[a-zA-Z]+$")) {
            return true;
        } else {
            System.out.println("Invalid format");
            return false;
        }
    }

    public void showClientCommands(String clientName) {
        System.out.printf("Welcome %s! Select a command from the list:\n", clientName);
        System.out.println("1. Execute transaction");
        System.out.println("2. Exit");
    }

    public void showStartCommands() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.println("Enter your choice:");
    }

    @Override
    public void exitCommand() {
        System.out.println("Good bye!");
        System.exit(1);
    }

    @Override
    public Client registerClient() {
        String userName;
        String clientName;
        int password;
        Client client = null;
        System.out.println("Enter Username:");
        while (true) {
            try {
                userName = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (consistsOfLetters(userName)) {
                client = clientServiceIml.getClientByUserName(userName);
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
        client = new Client(clientName, userName, password);
        clientServiceIml.registerClient(client);
        System.out.println(client.toString());
        return client;
    }

    @Override
    public Client logIn() {
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
            client = clientServiceIml.getClientByUserName(userName);
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
            if (client.getPassword() != password) {
                System.out.println("Wrong password.");
                continue;
            }
            System.out.println("Login done!");
            break;
        }
        return client;
    }

    private long getRecipientAccountNumber(Client sender) {
        long accountNumber = 0;
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
            if (clientServiceIml.getClientByAccountNumber(accountNumber) == null) {
                System.out.println("Recipient account number does not exist. Try again:");
                continue;
            }
            if (sender.getAccountNumber() == accountNumber) {
                System.out.println("Don't choose your account number");
                continue;
            }
            break;
        }
        return accountNumber;
    }

    private int getAmountToSend(Client sender) {
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
            if (amount > sender.getBalance()) {
                System.out.println("You don't have enough funds");
                continue;
            }
            break;
        }
        return amount;
    }

    @Override
    public void sendMoney(Client sender) {
        long recipientAccountNumber = getRecipientAccountNumber(sender);
        int amount = getAmountToSend(sender);
        clientServiceIml.sendMoney(sender, recipientAccountNumber, amount);
        System.out.printf("Operation was successfully completed! %d was sent to the account %d\n", amount, recipientAccountNumber);
    }

    public int chooseStartCommand() {
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

    public int chooseClientCommand() {
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
}

