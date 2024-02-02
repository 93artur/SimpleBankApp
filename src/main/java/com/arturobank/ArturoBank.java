package com.arturobank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArturoBank {
    private List<Client> clients = new ArrayList<>();

    public void sendToClient(Client sender, Client recipient, int sum) {
        int senderAccount = sender.getCount().getAccount();
        if (senderAccount >= sum) {
            sender.getCount().setAccount(senderAccount - sum);
            recipient.getCount().setAccount(recipient.getCount().getAccount() + sum);
            sender.addCheck(getCurrentDateTime() + " sent " + sum);
            recipient.addCheck(getCurrentDateTime() + " credited " + sum);
            System.out.println("Operation was successfully completed! " + sum + " was sent to the account of " + recipient.getName());
            System.out.println(sender.getCount().getAccount() + " left on the account of " + sender.getName());
        } else {
            System.out.println("You don't have enough funds");
        }
    }

    public String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return formatter.format(currentDateTime);
    }

    public Client isThereUser(String name) {
        Client client = null;
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getName().equals(name)) {
                client = clients.get(i);
                break;
            }
        }
        return client;
    }

    public static void main(String[] args) {
        Client sender;
        Client recipient;
        String senderName;
        String recipientName;
        int amount;
        System.out.println("*********** ARTURO BANK HAS STARTED ***********");
        ArturoBank bank = new ArturoBank();
        bank.clients.add(new Client("Egor", new Count(900)));
        bank.clients.add(new Client("Katy", new Count(1100)));
        bank.sendToClient(bank.clients.get(0), bank.clients.get(1), 355);
        System.out.println("User:");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                senderName = reader.readLine();
                if (senderName != null && senderName.matches("^[a-zA-Z]+$")) {
                    sender = bank.isThereUser(senderName);
                    if (sender != null) {
                        break;
                    } else {
                        System.out.println("There is no such user");
                    }
                } else {
                    System.out.println("Invalid name format");
                }
            }
            System.out.println("To:");
            while (true) {
                recipientName = reader.readLine();
                if (recipientName != null && recipientName.matches("^[a-zA-Z]+$")) {
                    recipient = bank.isThereUser(recipientName);
                    if (recipient != null) {
                        break;
                    } else {
                        System.out.println("There is no such user");
                    }
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