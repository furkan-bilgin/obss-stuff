package org.furkanbilgin.obssjavastuff.example9;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

public class InMemoryDataStore implements DataStore {
    private final HashMap<String, Data> data = new HashMap<>();

    @Override
    public void put(String key, String value, int ttl) {
        var ttlDate = Date.from(Instant.now().plusSeconds(ttl));
        data.put(key, new Data(value, ttlDate));
    }

    @Override
    public String get(String key) {
        var dataInDb = data.get(key);
        if (dataInDb.getExpiresAt().before(new Date())) {
            data.remove(key);
            return null;
        }
        return dataInDb.getData();
    }

}
