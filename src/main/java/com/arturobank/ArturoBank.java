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

    public static void main(String[] args) {
        ArturoBank bank = new ArturoBank();
        Client sender;
        Client recipient;
        String senderName;
        String recipientName;
        int amount;
        System.out.println("*********** ARTURO BANK HAS STARTED ***********");
        System.out.println("User:");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                senderName = reader.readLine();
                if (senderName != null && senderName.matches("^[a-zA-Z]+$")) {
                    sender = new Client(senderName, new Count(1000));
                    break;
                } else {
                    System.out.println("Invalid name format");
                }
            }
            System.out.println("To:");
            while (true) {
                recipientName = reader.readLine();
                if (recipientName != null && recipientName.matches("^[a-zA-Z]+$")) {
                    recipient = new Client(recipientName, new Count(1000));
                    break;
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