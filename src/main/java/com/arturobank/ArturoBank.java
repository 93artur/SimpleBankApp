package com.arturobank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArturoBank {
    private ClientBase clientBase = new ClientBase();

    public void sendToClient(Client sender, Client recipient, int sum) {
        int senderAccount = sender.getClientAccount().getAccount();
        if (senderAccount >= sum) {
            sender.getClientAccount().setAccount(senderAccount - sum);
            recipient.getClientAccount().setAccount(recipient.getClientAccount().getAccount() + sum);
            sender.addBill(getCurrentDateTime() + " sent " + sum);
            recipient.addBill(getCurrentDateTime() + " credited " + sum);
            System.out.println("Operation was successfully completed! " + sum + " was sent to the account of " + recipient.getName());
            System.out.println(sender.getClientAccount().getAccount() + " left on the account of " + sender.getName());
        } else {
            System.out.println("You don't have enough funds");
        }
    }

    public String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return formatter.format(currentDateTime);
    }

    public static void main(String[] args) {
        Client sender;
        Client recipient;
        String senderName;
        String recipientName;
        int amount;
        System.out.println("*********** ARTURO BANK HAS STARTED ***********");
        ArturoBank bank = new ArturoBank();
        bank.clientBase.addClient("Egor", new Account(1000));
        bank.clientBase.addClient("Katy", new Account(1000));
        System.out.println("User:");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                senderName = reader.readLine();
                if (senderName != null && senderName.matches("^[a-zA-Z]+$")) {
                    sender = bank.clientBase.getClient(senderName);
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
                    recipient = bank.clientBase.getClient(recipientName);
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
            bank.sendToClient(sender, recipient, amount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}