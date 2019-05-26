package com.adastra.tvdiary;

import java.util.ArrayList;

public class Show {

    private String name;
    private ArrayList<String> genres;
    private Rating rating;
    private String summary;
    private Image image;

    public Show(String name, ArrayList<String> genres, Rating rating, String summary, Image image) {
        this.name = name;
        this.genres = genres;
        this.rating = rating;
        this.summary = summary;
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }


    public Rating getRating() {
        return rating;
    }

    public String getSummary() {
        return summary;
    }


    public static class Rating {
        private double average;

        public Rating(double average) {
            this.average = average;
        }

        public double getAverage() {
            return average;
        }
    }

    public static class Image {
        private String medium;
        private String original;

        public Image(String medium, String original) {
            this.medium = medium;
            this.original = original;
        }

        public String getMedium() {
            return medium;
        }

        public String getOriginal() {
            return original;
        }
    }
}
