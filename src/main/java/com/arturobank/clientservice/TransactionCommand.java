package com.arturobank.clientservice;

import com.arturobank.datebase.AccountBase;
import com.arturobank.datebase.ClientBase;

public class TransactionCommand {
    private ClientOperation clientOperation = new ClientOperation();
    public void sendToClient(int fromCountNumber, int toCountNumber, int amount, ClientBase clientBase, AccountBase accountBase) {
        accountBase.getAccountsBase().replace(fromCountNumber, accountBase.getAccountsBase().get(fromCountNumber) - amount);
        accountBase.getAccountsBase().replace(toCountNumber, accountBase.getAccountsBase().get(toCountNumber) + amount);
        System.out.println("Operation was successfully completed! " + amount +
                " was sent to the account of " + clientOperation.getClientByAccountNumber(toCountNumber, clientBase).getClientName());
        System.out.println(accountBase.getAccountsBase().get(fromCountNumber) +
                " left on the account of " + clientOperation.getClientByAccountNumber(fromCountNumber, clientBase).getClientName());
    }
}
