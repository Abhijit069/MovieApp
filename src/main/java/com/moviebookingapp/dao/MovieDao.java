package com.moviebookingapp.dao;

import com.moviebookingapp.entity.Customer;
import com.moviebookingapp.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDao extends JpaRepository<Movie, String> {
    Movie findByMoviename(String moviename);

    @Modifying
    @Query("delete from Movie where moviename=?1")
    int deleteByMoviename(String moviename);

}
