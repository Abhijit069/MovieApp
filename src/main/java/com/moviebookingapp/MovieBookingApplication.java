package com.moviebookingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MovieBookingApplication {

    public static void main(String[] args) {
        //System.out.println(movieTicket());
        SpringApplication.run(MovieBookingApplication.class, args);
    }



//    public static String movieTicket(){
//        Tickets t1 = new Tickets();
//        t1.setTicketId("1");
//        //t1.setMovieDate(LocalDate.of(2023,2,21));
//        t1.setMovieName("Movie1");
//        t1.setNumberofTickets(10);
//        t1.setSeatNumbers("1");
//        t1.setTheatreName("Theatre 1");
//
//        Tickets t2 = new Tickets();
//        t2.setTicketId("2");
//        //t2.setMovieDate(LocalDate.of(2023,2,22));
//        t2.setMovieName("Movie1");
//        t2.setNumberofTickets(12);
//        t2.setSeatNumbers("2");
//        t2.setTheatreName("Theatre 2");
//
//
//        List<Tickets> lst = List.of(t1, t2);
//
//        //Movie m = new Movie(1,"M1","Horror","123","Fr","Dont watch","2.7",lst);
//
//
//        return t1.toString();
//    }

}
