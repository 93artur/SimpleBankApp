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

    public Client getClientByCountNumber(int countNumber) {
        Client client = null;
        ArrayList<Client> values = new ArrayList<>(clientsOfBank.values());
        for (int i = 0; i < values.size(); i++){
            if (values.get(i).getCountNumber() == countNumber){
                client = values.get(i);
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
