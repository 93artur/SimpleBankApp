package com.arturobank.clientservice.model;

import java.util.Objects;

public class Client {
    private String clientName;
    private String userName;
    private int password;
    private long accountNumber;
    private long balance;

    public Client (String clientName, String userName, int password){
        this.clientName = clientName;
        this.userName = userName;
        this.password = password;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getPassword() {
        return password;
    }

    public String getClientName() {
        return clientName;
    }

    public String getUserName() {
        return userName;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Client named " + clientName + " registered!";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return password == client.password && accountNumber == client.accountNumber && balance == client.balance && Objects.equals(clientName, client.clientName) && Objects.equals(userName, client.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientName, userName, password, accountNumber, balance);
    }
}
