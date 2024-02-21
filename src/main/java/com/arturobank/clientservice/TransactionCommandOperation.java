package com.arturobank.clientservice;

import com.arturobank.datebase.AccountBase;
import com.arturobank.datebase.ClientBase;

public class TransactionCommandOperation implements Command {
    private TransactionCommand transactionCommand;
    private int fromCountNumber;
    private int toCountNumber;
    private int sum;
    private ClientBase clientBase;
    private AccountBase accountBase;

    public TransactionCommandOperation(TransactionCommand transactionCommand, int fromCountNumber, int toCountNumber, int amount, ClientBase clientBase, AccountBase accountBase) {
        this.transactionCommand = transactionCommand;
        this.fromCountNumber = fromCountNumber;
        this.toCountNumber = toCountNumber;
        this.sum = amount;
        this.clientBase = clientBase;
        this.accountBase = accountBase;
    }

    @Override
    public void execute() {
        transactionCommand.sendToClient(fromCountNumber, toCountNumber, sum, clientBase, accountBase);
    }
}
