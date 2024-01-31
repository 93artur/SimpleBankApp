package com.arturobank;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private Count count;


    private List <String> checks = new ArrayList<>();

    public Client(String name, Count count) {
        this.name = name;
        this.count = count;
    }

    public Count getCount() {
        return count;
    }

    public void setCount(Count count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public List<String> getChecks() {
        return checks;
    }

    public void addCheck (String check){
        checks.add(check);
    }
}
