package org.furkanbilgin.obssjavastuff.example2;

public class Main {
    public static void main(String[] args) {
        Bus bus = new Bus(Destination.ARTVIN, 2);
        try {
            bus.insertPassenger(new Passenger(Destination.ARTVIN, "Ahmet"));
            bus.insertPassenger(new Passenger(Destination.ARTVIN, "Mahmut"));
            bus.insertPassenger(new Passenger(Destination.BURSA, "Furkan")); // Will throw an exception
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            bus.insertPassenger(new Passenger(Destination.ARTVIN, "Veli")); // Will also throw an exception
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
