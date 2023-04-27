package com.moviebookingapp.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class MovieTest {
    /**
     * Method under test: {@link Movie#Movie(String, String, String, String, String, String, String, String)}
     */
    @Test
    void testConstructor() {
        Movie actualMovie = new Movie("Movieid", "Moviename", "Movie Genre", "Movie Mins", "en", "Movie Description",
                "Movie Rating", "Theatre Name");

        assertEquals("Movie Description", actualMovie.getMovieDescription());
        assertNull(actualMovie.getTicket());
        assertEquals("Theatre Name", actualMovie.getTheatreName());
        assertEquals("Moviename", actualMovie.getMoviename());
        assertEquals("Movieid", actualMovie.getMovieid());
        assertEquals("Movie Rating", actualMovie.getMovieRating());
        assertEquals("Movie Mins", actualMovie.getMovieMins());
        assertEquals("en", actualMovie.getMovieLanguage());
        assertEquals("Movie Genre", actualMovie.getMovieGenre());
    }
}

