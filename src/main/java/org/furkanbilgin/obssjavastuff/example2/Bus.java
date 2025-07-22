package org.furkanbilgin.obssjavastuff.example2;

public class Bus {
    private Destination destination;
    private final Passenger[] passengers;

    public Bus(Destination destination, int capacity) {
        this.passengers = new Passenger[capacity];
        this.destination = destination;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Passenger[] getPassengers() {
        return passengers;
    }

    public void insertPassenger(Passenger passenger) throws IllegalArgumentException, Exception {
        if (passenger.getDestination() != destination) {
            throw new IllegalArgumentException(
                    "Destinations do not match for the passenger " + passenger.getName() + "!");
        }
        for (int i = 0; i < passengers.length; i++) {
            if (passengers[i] == null) {
                passengers[i] = passenger;
                System.out.println("Passenger " + passenger.getName() + " added.");
                return;
            }
        }
        throw new Exception("Bus is full for the passenger " + passenger.getName() + "!");
    }
}
