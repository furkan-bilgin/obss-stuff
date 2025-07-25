package org.furkanbilgin.obssjavastuff.example10;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        var values = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        var primes = values.stream()
                .filter(x -> x != 1) // edge case: 1 isn't a prime
                .filter(x -> {
                    if (x == 2) { // edge case: but 2 is
                        return true;
                    }
                    for (int i = 2; i < x; i++) { // from 2 to x, check divisibility
                        if (x % i == 0) {
                            return false;
                        }
                    }
                    return true;
                })
                .toList();

        for (var prime : primes) {
            System.err.println(Integer.toString(prime) + ", ");
        }
    }
}
