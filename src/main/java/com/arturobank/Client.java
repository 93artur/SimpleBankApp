package com.arturobank;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String userName;
    private int password;

    private int countNumber;
    private List<String> bills = new ArrayList<>();

    public Client(String userName, int password) {
        this.userName = userName;
        this.password = password;
    }

    public int getCountNumber() {
        return countNumber;
    }

    public void setCountNumber(int countNumber) {
        this.countNumber = countNumber;
    }

    public int getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public List<String> getBills() {
        return bills;
    }

    public void addBill(String bill) {
        bills.add(bill);
    }
}
