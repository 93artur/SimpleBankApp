package com.arturobank;

import java.util.HashMap;
import java.util.Map;

public class CountsBase {
    private Map<Integer, Integer> counts = new HashMap<>();

    public Map<Integer, Integer> getCountsBase() {
        return counts;
    }

    public void addCount(int countNumber, int count) {
        counts.put(countNumber, count);
    }

    public int getCount(int countNumber) {
        return counts.get(countNumber);
    }

    public void changeCount(int countNumber, int count) {
        counts.replace(countNumber, count);
    }

    public int createCountNumber(){
        int max = 9999;
        int min = 1111;
        return (int) (Math.random() * ++max) + min;
    }
}
