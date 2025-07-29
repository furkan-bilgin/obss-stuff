package org.furkanbilgin.obssjavastuff.example9;

import java.util.Date;

public class Data {
    private String data;
    private Date expiresAt;

    public Data(String data, Date expiresAt) {
        this.data = data;
        this.expiresAt = expiresAt;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

}
