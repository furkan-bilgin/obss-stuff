package com.furkanbilgin.finalproject.movieportal.dto.imdbApi;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TitleDTO {

    private String id;
    private String type;
    private boolean isAdult;
    private String primaryTitle;
    private String originalTitle;
    private Image primaryImage;
    private int startYear;
    private int endYear;
    private int runtimeSeconds;
    private List<String> genres;
    private Rating rating;
    private Metacritic metacritic;
    private String plot;
    private List<Person> directors;
    private List<Person> writers;
    private List<Person> stars;
    private List<OriginCountry> originCountries;
    private List<SpokenLanguage> spokenLanguages;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Image {
        private String url;
        private int width;
        private int height;
        private String type;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Rating {
        private double aggregateRating;
        private int voteCount;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Metacritic {
        private String url;
        private int score;
        private int reviewCount;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Person {
        private String id;
        private String displayName;
        private List<String> alternativeNames;
        private Image primaryImage;
        private List<String> primaryProfessions;
        private String biography;
        private String birthName;
        private BirthDate birthDate;
        private String birthLocation;
        private DeathDate deathDate;
        private String deathLocation;
        private String deathReason;
        private MeterRanking meterRanking;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BirthDate {
        private int year;
        private int month;
        private int day;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeathDate {
        private int year;
        private int month;
        private int day;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MeterRanking {
        private int currentRank;
        private String changeDirection;
        private int difference;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OriginCountry {
        private String code;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SpokenLanguage {
        private String code;
        private String name;
    }
}
