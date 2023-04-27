package com.moviebookingapp.dao;

import com.moviebookingapp.entity.Movie;
import com.moviebookingapp.entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketsDao extends JpaRepository<Tickets, String> {
    //Movie findByMoviename(String moviename);

}
