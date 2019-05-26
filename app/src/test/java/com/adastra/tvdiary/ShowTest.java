package com.adastra.tvdiary;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ShowTest {

    @Test
    public void create_image() {
        Show.Image image = new Show.Image("url1", "url2");
        assertNotNull(image);
    }

    @Test
    public void create_rating() {
        Show.Rating rating = new Show.Rating(7.8);
        assertNotNull(rating);
    }

    @Test
    public void get_average_rating_of_show() {
        Show.Image image = new Show.Image("url1", "url2");
        Show.Rating rating = new Show.Rating(7.8);

        ArrayList<String> genres = new ArrayList<>();
        genres.add("Comedy");
        genres.add("Drama");

        Show show = new Show("Glee", genres, rating, "Summary of show", image);
        assertEquals(show.getRating().getAverage(), 7.8, 0.001);
    }

    @Test
    public void create_show() {
        Show.Image image = new Show.Image("url1", "url2");
        Show.Rating rating = new Show.Rating(7.8);

        ArrayList<String> genres = new ArrayList<>();
        genres.add("Comedy");
        genres.add("Drama");

        Show show = new Show("Glee", genres, rating, "Summary of show", image);
        assertNotNull(show);
    }


    @Test
    public void get_name_of_show() {
        Show.Image image = new Show.Image("url1", "url2");
        Show.Rating rating = new Show.Rating(7.8);

        ArrayList<String> genres = new ArrayList<>();
        genres.add("Comedy");
        genres.add("Drama");

        Show show = new Show("Glee", genres, rating, "Summary of show", image);
        assertEquals(show.getName(), "Glee");
    }
}
