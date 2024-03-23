package com.arturobank.datebase;

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
        Client client = null;
        for (Long key : clientsOfBank.keySet()) {
            if (key == accountNumber) {
                client = clientsOfBank.get(key);
                break;
            }
        }
        return client;
    }

    @Override
    public Client getClientByUserName(String userName) {
        Client client = null;
        for (Long key : clientsOfBank.keySet()) {
            if (clientsOfBank.get(key).getUserName().equals(userName)){
                client = clientsOfBank.get(key);
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
