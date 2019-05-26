package com.adastra.tvdiary;

import android.os.Parcelable;

import java.util.List;

public class Show {

    private String name;
    private List<String> genres;
    private Rating rating;
    private String summary;

    public String getName() {
        return name;
    }

    public List<String> getGenres() {
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

}
