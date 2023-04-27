package com.moviebookingapp.service;

import com.moviebookingapp.dao.CustomerDao;
import com.moviebookingapp.dao.MovieDao;
import com.moviebookingapp.dao.TicketsDao;
import com.moviebookingapp.entity.Customer;
import com.moviebookingapp.entity.Movie;
import com.moviebookingapp.entity.Tickets;
import com.moviebookingapp.exception.CustomEcxeption;
import com.moviebookingapp.exception.UsernameAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
//import static com.moviebookingapp.service.MovieService;

@Service
@Transactional
public class TicketService {


    @Autowired
    private TicketsDao ticketsDao;

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private MovieService movieService;
    public int bookTickets(String moviename) throws CustomEcxeption {
        if(checkMovieAvailibility(moviename)){
            Tickets avlblTkts = ticketsDao.findById(moviename).get();
            if(checkTicket(avlblTkts.getNumberofTickets())){
                avlblTkts.setNumberofTickets(avlblTkts.getNumberofTickets()-1);
                avlblTkts.setSeatNumbers(avlblTkts.getSeatNumbers()+1);
                if(avlblTkts.getNumberofTickets()==0){
                    avlblTkts.setTicketStatus("SOLD OUT");
                    ticketsDao.save(avlblTkts);
                    return 0;
                }
                ticketsDao.save(avlblTkts);
                }else{
                avlblTkts.setTicketStatus("SOLD OUT");
                ticketsDao.save(avlblTkts);
                //throw new CustomEcxeption("Tickets Sold Out");
                return 0;
            }
            return 1;
        }else{
            return -1;
        }

    }

    public static boolean checkTicket(int tktNo){
        if(tktNo==0){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkMovieAvailibility(String moviename){
        List<Tickets> allTkts = ticketsDao.findAll();
        boolean ifExist=false;
        for(Tickets t : allTkts){
            if(t.getMoviename().equals(moviename)){
                ifExist=true;
                break;
            }
        }
        return ifExist;
    }

    public Movie updateTickets(String moviename, int ticketNo) throws CustomEcxeption {
        if(checkMovieAvailibility(moviename) && ticketNo>0){
            Movie movie = movieDao.findByMoviename(moviename);
            Tickets ticket = movieService.findByMoviename(moviename).getTicket();
            ticket.setNumberofTickets(ticketNo);
            ticketsDao.save(ticket);
            movieDao.save(movie);
            return movie;

        }else{
            throw new CustomEcxeption("Movie does not exist or ticket no is <0");
        }
    }
}
