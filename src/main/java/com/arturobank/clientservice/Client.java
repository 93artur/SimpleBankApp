package com.arturobank.clientservice;

public class Client {
    private String clientName;
    private String userName;
    private int password;
    private int accountNumber;

    public Client(String userName, int password, String clientName, int accountNumber) {
        this.clientName = clientName;
        this.password = password;
        this.accountNumber = accountNumber;
        this.userName = userName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
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
}
