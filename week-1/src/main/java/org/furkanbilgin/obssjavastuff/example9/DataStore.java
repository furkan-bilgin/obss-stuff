package org.furkanbilgin.obssjavastuff.example9;

public interface DataStore {
    void put(String key, String value, int ttl);

    String get(String key);
}
