package com.arturobank.clientservice;

import com.arturobank.clientservice.model.Client;
import com.arturobank.datebase.ClientBaseImpl;

public class ClientServiceIml implements ClientService {
    private ClientBaseImpl clientBase = new ClientBaseImpl();

    @Override
    public Client registerClient(Client client) {
        return clientBase.registerClient(client);
    }

    @Override
    public Client getClientByUserName(String userName) {
        return clientBase.getClientByUserName(userName);
    }

    @Override
    public Client sendMoney(Client sender, long accountNumber, int amount) {
        sender.setBalance(sender.getBalance() - amount);
        Client receiver = clientBase.getClientByAccountNumber(accountNumber);
        receiver.setBalance(receiver.getBalance() + amount);
        return receiver;
    }

    @Override
    public Client getClientByAccountNumber(long accountNumber) {
        return clientBase.getClientByAccountNumber(accountNumber);
    }
}
