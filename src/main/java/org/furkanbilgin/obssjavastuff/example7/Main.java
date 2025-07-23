package org.furkanbilgin.obssjavastuff.example7;

import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        var input = "+ Merhaba, nasılsın? - Merhaba, iyiyim, sen nasılsın?";
        var occurances = new TreeMap<String, Integer>();
        var split = input.split("( |,|\\?)+");
        for (var word : split) {
            word = word.toLowerCase();
            occurances.put(word, occurances.getOrDefault(word, 0) + 1);
        }
        for (var entry : occurances.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
