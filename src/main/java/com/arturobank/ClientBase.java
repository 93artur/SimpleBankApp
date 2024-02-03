package com.arturobank;

import java.util.HashMap;
import java.util.Map;

public class ClientBase {
    private Map<String, Client> clientsOfBank = new HashMap<>();

    public Map<String, Client> getClientsOfBank() {
        return clientsOfBank;
    }

    public void setClientsOfBank(Map<String, Client> clientsOfBank) {
        this.clientsOfBank = clientsOfBank;
    }

    public Client getClient(String name) {
        return clientsOfBank.get(name);
    }

    public void addClient(String name, Account count) {
        clientsOfBank.put(name, new Client(name, count));
    }
}
