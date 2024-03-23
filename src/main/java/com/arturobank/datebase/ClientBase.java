package com.arturobank.datebase;

import com.arturobank.clientservice.model.Client;

public interface ClientBase {
    Client registerClient(Client client);
    Client getClientByAccountNumber(long accountNumber);
    Client getClientByUserName(String userName);
}
