package com.arturobank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArturoBank {
    List<Client> clients = new ArrayList<>();

    public void sentToClient(Client Sender, Client Recipient, int sum) {
        int totalSender = Sender.getCount().getTotal();
        if (totalSender >= sum) {
            int temp1 = totalSender - sum;
            Sender.getCount().setTotal(temp1);
            int temp2 = Recipient.getCount().getTotal() + sum;
            Recipient.getCount().setTotal(temp2);
            Sender.addCheck(getCurrentDateTime() + " списано " + sum);
            Recipient.addCheck(getCurrentDateTime() + " начислено " + sum);

            System.out.println("Операция прошла успешно! На счет клиента " + Recipient.getName() + " зачислено " + sum);
            System.out.println("У клиента " + Sender.getName() + " на счету осталось " + Sender.getCount().getTotal());

        } else {
            System.out.println("У Вас недостаточно средств");
        }
    }


    public String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return formatter.format(currentDateTime);
    }


    public static void main(String[] args) {
        ArturoBank bank = new ArturoBank();
        bank.clients.add(new Client("Егор", new Count(900)));
        bank.clients.add(new Client("Катя", new Count(1100)));
        bank.sentToClient(bank.clients.get(0), bank.clients.get(1), 355);
    }
}