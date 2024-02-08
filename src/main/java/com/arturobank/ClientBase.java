package com.arturobank;

import java.util.ArrayList;
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

    public Client getClientByAccountNumber(int accountNumber) {
        Client client = null;
        for(Client client1 : clientsOfBank.values()){
            if (client1.getAccountNumber() == accountNumber){
                client = client1;
                break;
            }
        }
        return client;
    }
    public Client getClientByName(String name){
        return clientsOfBank.get(name);
    }

    public void addClient(String name, int password) {
        clientsOfBank.put(name, new Client(name,password));
    }

    public void addClient(Client client){
        clientsOfBank.put(client.getUserName(), client);
    }
}
