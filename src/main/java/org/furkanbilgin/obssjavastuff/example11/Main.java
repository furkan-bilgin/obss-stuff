package org.furkanbilgin.obssjavastuff.example11;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        var cities = Arrays.asList(
                new City("Istanbul", 15519267),
                new City("Ankara", 5639076),
                new City("Izmir", 4320519),
                new City("Bursa", 3120216),
                new City("Antalya", 2557904));
        Map<String, List<City>> grouppedCitiesWithList = cities.stream()
                .collect(Collectors.groupingBy(x -> x.getCityName()));
        Map<String, Integer> grouppedCities = grouppedCitiesWithList.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getFirst().getPopulation()));

        for (var entry : grouppedCities.entrySet()) {
            System.err.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
