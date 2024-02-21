package com.arturobank.datebase;

import com.arturobank.clientservice.Client;

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

}
