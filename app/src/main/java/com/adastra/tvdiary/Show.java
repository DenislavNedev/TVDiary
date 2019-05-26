package com.adastra.tvdiary;

import java.util.ArrayList;
import java.util.List;

public class Show {

    private String name;
    private ArrayList<String> genres;
    private Rating rating;
    private String summary;
    private Image image;

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


    public class Rating {
        private double average;

        public double getAverage() {
            return average;
        }
    }

    public class Image {
        private String medium;
        private String original;

        public String getMedium() {
            return medium;
        }

        public String getOriginal() {
            return original;
        }
    }
}
