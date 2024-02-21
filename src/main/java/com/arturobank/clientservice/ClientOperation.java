package com.arturobank.clientservice;

import com.arturobank.datebase.*;

public class ClientOperation {

    public void registerClient(String userName, int password, String clientName, int accountNumber, ClientBase clientBase, AccountBase accountBase) {
        Client client = new Client(userName, password, clientName, accountNumber);
        clientBase.getClientsOfBank().put(userName, client);
        accountBase.getAccountsBase().put(accountNumber, 1000);
        System.out.println("New user registered.");
    }

    public Client getClientByAccountNumber(int accountNumber, ClientBase clientBase) {
        Client client = null;
        for (Client client1 : clientBase.getClientsOfBank().values()) {
            if (accountNumber == client1.getAccountNumber()) {
                client = client1;
                break;
            }
        }
        return client;
    }
}
