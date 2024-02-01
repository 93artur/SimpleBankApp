package com.arturobank;

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
        bank.clients.add(new Client("Egor", new Count(900)));
        bank.clients.add(new Client("Katy", new Count(1100)));
        bank.sendToClient(bank.clients.get(0), bank.clients.get(1), 355);
    }
}