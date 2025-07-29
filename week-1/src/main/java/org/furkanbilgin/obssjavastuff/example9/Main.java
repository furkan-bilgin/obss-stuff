package org.furkanbilgin.obssjavastuff.example9;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var memStore = new InMemoryDataStore();
        var fileStore = new FileDataStore();

        memStore.put("key1", "value1", 2);
        System.out.println("InMemoryDataStore get key1: " + memStore.get("key1")); // Should be value1
        Thread.sleep(2500);
        System.out.println("InMemoryDataStore get key1 after expiry: " + memStore.get("key1")); // Should be null

        fileStore.put("key2", "value2", 2);
        System.out.println("FileDataStore get key2: " + fileStore.get("key2")); // Should be value2
        Thread.sleep(2500);
        System.out.println("FileDataStore get key2 after expiry: " + fileStore.get("key2")); // Should be null
    }
}
