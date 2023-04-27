package com.moviebookingapp.service;

import com.moviebookingapp.dao.MovieDao;
import com.moviebookingapp.dao.TicketsDao;
import com.moviebookingapp.dto.MovieSaveDTO;
import com.moviebookingapp.entity.Movie;
import com.moviebookingapp.entity.Tickets;
import com.moviebookingapp.exception.CustomEcxeption;
import com.moviebookingapp.exception.UsernameAlreadyExists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class MovieService {

    private String STATUS;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private TicketsDao ticketsDao;

    Logger logger = LoggerFactory.getLogger(MovieService.class);

    public void deleteMovie(String moviename) throws CustomEcxeption {
        logger.info("Entering deleteMovie method");
        if(checkMovieAvailibility(moviename)){
            logger.info("movieName is present in the database");
            movieDao.deleteByMoviename(moviename);
            logger.info("deletion successful");
            logger.info("exiting deleteMovie");
        }else{
            logger.error("movieName is not present in Movie table");
            throw new CustomEcxeption("Movie doesn't exist");
        }
    }

    public Movie saveMovie(MovieSaveDTO mtd) throws UsernameAlreadyExists {
        logger.info("Entering saveMovie");
        if(mtd.getNumberofTickets()>0){
            STATUS="BOOK ASAP";
        }else{
            STATUS = "SOLD OUT";
        }
        Tickets t = new Tickets(UUID.randomUUID().toString(),
                mtd.getMoviename(),
                mtd.getTheatreName(),
                mtd.getNumberofTickets(),
                mtd.getSeatNumbers(),STATUS
                );

        Movie m = new Movie(UUID.randomUUID().toString(),mtd.getMoviename(),
                mtd.getMovieGenre(),
                mtd.getMovieMins(),
                mtd.getMovieLanguage(),
                mtd.getMovieDescription(),
                mtd.getMovieRating(),
                mtd.getTheatreName()
                );
        m.setTicket(t);
        ticketsDao.save(t);
        movieDao.save(m);
        logger.info("Movie saved into Movie table. Exitting saveMovie method");
        return m;
    }
    public List<Movie> finAllMovies() {
        logger.info("Executing findAllMovies Method");
        return movieDao.findAll();
    }

    public Movie findByMoviename(String moviename)throws CustomEcxeption {
        logger.info("Executing findByMoviename Method");
        return movieDao.findByMoviename(moviename);
    }

    public boolean checkMovieAvailibility(String moviename){
        List<Movie> movies = movieDao.findAll();
        boolean ifExist=false;
        for(Movie m : movies){
            if(m.getMoviename().equals(moviename)){
                ifExist=true;
                break;
            }
        }
        return ifExist;
    }

}
