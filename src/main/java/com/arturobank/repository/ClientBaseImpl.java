package com.arturobank.repository;

import com.arturobank.clientservice.model.Client;

import java.util.HashMap;
import java.util.Map;

public class ClientBaseImpl implements ClientBase {
    private Map<Long, Client> clientsOfBank = new HashMap<>();
    private Long accountNumber = 1L;

    @Override
    public Client registerClient(Client client) {
        Long clientAccountNumber = accountNumber++;
        client.setAccountNumber(clientAccountNumber);
        client.setBalance(1000);
        clientsOfBank.put(client.getAccountNumber(), client);
        return client;
    }

    @Override
    public Client getClientByAccountNumber(long accountNumber) {
        return clientsOfBank.get(accountNumber);
    }

    @Override
    public Client getClientByUserName(String userName) {
        Client client = null;
        for (Client client1 : clientsOfBank.values()) {
            if (client1.getUserName().equals(userName)){
                client = client1;
                break;
            }
        }
        return client;
    }

    {
        Client client = new Client("Ar", "Ar", 1);
        this.registerClient(client);
    }
}
