package org.furkanbilgin.obssjavastuff.example9;

public class FileDataStore implements DataStore {
    private final java.io.File storageDir = new java.io.File("datastore");

    public FileDataStore() {
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
    }

    @Override
    public void put(String key, String value, int ttl) {
        try {
            java.io.File file = new java.io.File(storageDir, key);
            try (java.io.FileWriter writer = new java.io.FileWriter(file)) {
                // now + ttl = expiresAt
                long expiresAt = java.time.Instant.now().plusSeconds(ttl).toEpochMilli();
                writer.write(expiresAt + "\n" + value);
            }
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String get(String key) {
        java.io.File file = new java.io.File(storageDir, key);
        if (!file.exists()) {
            return null;
        }
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file))) {
            long expiresAt = Long.parseLong(reader.readLine());
            // Remove expired files
            if (expiresAt < java.time.Instant.now().toEpochMilli()) {
                file.delete();
                return null;
            }
            StringBuilder valueBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                valueBuilder.append(line).append("\n");
            }
            // Remove last newline
            if (valueBuilder.length() > 0) {
                valueBuilder.setLength(valueBuilder.length() - 1);
            }
            return valueBuilder.toString();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
}
