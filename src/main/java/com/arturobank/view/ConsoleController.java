package com.arturobank.view;

import com.arturobank.clientservice.model.Client;

public interface ConsoleController {
    Client registerClient();
    void exitCommand();
    Client logIn();
    void sendMoney(Client sender);
}
