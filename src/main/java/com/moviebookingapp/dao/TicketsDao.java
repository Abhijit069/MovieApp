package com.moviebookingapp.dao;

import com.moviebookingapp.entity.Movie;
import com.moviebookingapp.entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketsDao extends MongoRepository<Tickets, String> {
    //Movie findByMoviename(String moviename);

}
