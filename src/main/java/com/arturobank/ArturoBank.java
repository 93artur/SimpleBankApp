package com.arturobank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ArturoBank {
    private ClientBase clientBase = new ClientBase();
    private AccountBase accountBase = new AccountBase();
    private Client myClient;

    public void sendToClient(int countNumber, int sum) {
        accountBase.changeCount(myClient.getAccountNumber(), accountBase.getAccount(myClient.getAccountNumber()) - sum);
        accountBase.changeCount(countNumber, accountBase.getAccount(countNumber) + sum);
        myClient.addBill(getCurrentDateTime() + " sent " + sum);
        Client recipient = clientBase.getClientByAccountNumber(countNumber);
        recipient.addBill(getCurrentDateTime() + " credited " + sum);
        System.out.println("Operation was successfully completed! " + sum +
                " was sent to the account of " + recipient.getUserName());
        System.out.println(accountBase.getAccount(myClient.getAccountNumber()) +
                " left on the account of " + myClient.getUserName());
    }

    public String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return formatter.format(currentDateTime);
    }

    public void registerUser(BufferedReader reader) throws IOException {
        String userName;
        int password;
        System.out.println("Enter Username:");
        while (true) {
            userName = reader.readLine();
            if (userName != null && userName.matches("^[a-zA-Z]+$")) {
                myClient = clientBase.getClientByName(userName);
                if (myClient == null) {
                    break;
                }
                System.out.println("A user named " + userName + " exists");
            } else {
                System.out.println("Invalid name format");
            }
        }
        System.out.println("Enter password:");
        while (true) {
            try {
                password = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Use only Integers");
                continue;
            }
            break;
        }
        myClient = new Client(userName, password);
        int accountNumber = accountBase.getAccountsBase().size() + 1;
        myClient.setAccountNumber(accountNumber);
        clientBase.addClient(myClient);
        accountBase.addAccount(accountNumber, 1000);
        System.out.println("New user registered. Your account number " + accountNumber);
    }

    public void registerUser(String userName, int password, int accountNumber) {
        Client newClient = new Client(userName, password);
        newClient.setAccountNumber(accountNumber);
        clientBase.addClient(newClient);
        accountBase.addAccount(accountNumber, 1000);
    }

    public void executeTransaction(BufferedReader reader) throws IOException {
        int accountNumber;
        int amount;
        System.out.println("Enter account number:");
        while (true) {
            try {
                accountNumber = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Use only Integers");
                continue;
            }
            if (myClient.getAccountNumber() == accountNumber) {
                System.out.println("You can't choose your account");
                continue;
            }
            if (!accountBase.getAccountsBase().containsKey(accountNumber)) {
                System.out.println("Such bank account does not exist");
                continue;
            }
            break;
        }
        System.out.println("Amount:");
        while (true) {
            try {
                amount = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Use only Integers");
                continue;
            }
            if (amount > accountBase.getAccount(myClient.getAccountNumber())) {
                System.out.println("You don't have enough funds. You have only " +
                        accountBase.getAccount(myClient.getAccountNumber()));
                continue;
            }
            break;
        }
        sendToClient(accountNumber, amount);
    }

    public void showCommands() {
        System.out.println("Select a command from the list:");
        System.out.println("Execute transaction");
        System.out.println("Exit");
    }

    public static void main(String[] args) {
        System.out.println("*********** ARTURO BANK HAS STARTED ***********");
        ArturoBank bank = new ArturoBank();
        bank.registerUser("Katy", 1, 1);
        bank.registerUser("Egor", 2, 2);
        String userName;
        int password;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Username:");
            while (true) {
                userName = reader.readLine();
                if (userName != null && userName.matches("^[a-zA-Z]+$")) {
                    bank.myClient = bank.clientBase.getClientByName(userName);
                    if (bank.myClient != null) {
                        break;
                    } else {
                        System.out.println("Such client do not exist. Do you want register? Type \"Yes\" or \"No\"");
                        while (true) {
                            String answer = reader.readLine();
                            if (answer.equals("Yes")) {
                                bank.registerUser(reader);
                                System.out.println("Username: ");
                                break;
                            }
                            if (answer.equals("No")) {
                                System.out.println("Enter Username");
                                break;
                            } else {
                                System.out.println("There is no such command!");
                            }
                        }
                        continue;
                    }
                } else {
                    System.out.println("Invalid name format");
                    continue;
                }
            }
            System.out.println("Password:");
            while (true) {
                try {
                    password = Integer.parseInt(reader.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Use only Integers");
                    continue;
                } catch (NullPointerException e) {
                    System.out.println("Incorrect password");
                    continue;
                }
                if (bank.myClient.getPassword() != password) {
                    System.out.println("Incorrect password");
                    continue;
                }
                break;
            }
            bank.showCommands();
            while (true) {
                String command = reader.readLine();
                if (command.equals("Exit")) {
                    break;
                }
                if (command.equals("Execute transaction")) {
                    bank.executeTransaction(reader);
                } else {
                    System.out.println("There is no such command!");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}