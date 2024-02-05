package com.arturobank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArturoBank {
    private ClientBase clientBase = new ClientBase();

    public void sendToClient(Client sender, Client recipient, int sum) {
        int senderAccount = sender.getAccount().getCount();
        if (senderAccount >= sum) {
            sender.getAccount().setCount(senderAccount - sum);
            recipient.getAccount().setCount(recipient.getAccount().getCount() + sum);
            sender.addBill(getCurrentDateTime() + " sent " + sum);
            recipient.addBill(getCurrentDateTime() + " credited " + sum);
            System.out.println("Operation was successfully completed! " + sum + " was sent to the account of " + recipient.getName());
            System.out.println(sender.getAccount().getCount() + " left on the account of " + sender.getName());
        } else {
            System.out.println("You don't have enough funds");
        }
    }

    public String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return formatter.format(currentDateTime);
    }

    public void addUser(BufferedReader reader) throws IOException {
        String clientName;
        Client client;
        System.out.println("Enter Username");
        while (true) {
            clientName = reader.readLine();
            if (clientName != null && clientName.matches("^[a-zA-Z]+$")) {
                client = clientBase.getClient(clientName);
                if (client == null) {
                    clientBase.addClient(clientName, new Account(1000));
                    System.out.println("User " + clientName + " is added");
                    break;
                }
                System.out.println("A user named " + clientName + " exists");
            } else {
                System.out.println("Invalid name format");
            }
        }
    }

    public void executeTransaction(BufferedReader reader) throws IOException {
        Client sender;
        Client recipient;
        String senderName;
        String recipientName;
        int amount;
        System.out.println("User:");
        while (true) {
            senderName = reader.readLine();
            if (senderName != null && senderName.matches("^[a-zA-Z]+$")) {
                sender = clientBase.getClient(senderName);
                if (sender != null) {
                    break;
                }
                System.out.println("There is no client named " + senderName);
            } else {
                System.out.println("Invalid name format");
            }
        }
        System.out.println("To:");
        while (true) {
            recipientName = reader.readLine();
            if (recipientName != null && recipientName.matches("^[a-zA-Z]+$")) {
                recipient = clientBase.getClient(recipientName);
                if (recipient != null) {
                    break;
                }
                System.out.println("There is no client named " + recipientName);
            } else {
                System.out.println("Invalid name format");
            }
        }
        System.out.println("Amount:");
        while (true) {
            try {
                amount = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Use only Integers");
                continue;
            }
            break;
        }
        sendToClient(sender, recipient, amount);
    }

    public void showCommands() {
        System.out.println("Select a command from the list:");
        System.out.println("Add user");
        System.out.println("Execute transaction");
        System.out.println("Exit");
    }

    public static void main(String[] args) {
        System.out.println("*********** ARTURO BANK HAS STARTED ***********");
        ArturoBank bank = new ArturoBank();
        bank.clientBase.addClient("Egor", new Account(1000));
        bank.clientBase.addClient("Katy", new Account(1000));
        bank.showCommands();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String command = reader.readLine();
                if (command.equals("Exit")) {
                    break;
                }
                if (command.equals("Execute transaction")) {
                    bank.executeTransaction(reader);
                }
                if (command.equals("Add user")) {
                    bank.addUser(reader);
                } else {
                    System.out.println("There is no such command!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}