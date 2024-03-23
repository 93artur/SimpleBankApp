package com.arturobank.clientservice;

import com.arturobank.clientservice.model.Client;

public interface ClientService {
    Client registerClient(Client client);
    Client getClientByUserName(String userName);
    Client sendMoney(Client client, long accountNumber, int amount);

    Client getClientByAccountNumber(long accountNumber);
}
