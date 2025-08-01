package org.furkanbilgin.obssjavastuff.example2;

public class Passenger {
    private String name;
    private Destination destination;

    public Passenger(Destination destination, String name) {
        this.destination = destination;
        this.name = name;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
