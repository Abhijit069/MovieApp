package com.moviebookingapp.controller;

import com.moviebookingapp.dto.MovieSaveDTO;
import com.moviebookingapp.entity.Customer;
import com.moviebookingapp.entity.JwtRequest;
import com.moviebookingapp.entity.JwtResponse;
import com.moviebookingapp.entity.Movie;
import com.moviebookingapp.exception.CustomEcxeption;
import com.moviebookingapp.exception.UsernameAlreadyExists;
import com.moviebookingapp.service.CustomerService;
import com.moviebookingapp.service.JwtService;
import com.moviebookingapp.service.MovieService;
import com.moviebookingapp.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "*")
@RestController
@CrossOrigin
@RequestMapping("/moviebooking")
public class CustomerController {

    @Autowired
    private CustomerService custService;

    @Autowired
    private MovieService movieService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private JwtService jwtService;

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostConstruct
    public void initRoleAndUser() {
        custService.initRoleandAdmin();
    }
    @PostMapping({"/login"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        logger.info("Logging in User");
        return jwtService.createJwtToken(jwtRequest);
    }


    public CustomerController(CustomerService custService) {
        this.custService = custService;
    }

    @PostMapping({"/register"})
    public ResponseEntity<Object> registerNewUser(@Valid @RequestBody Customer customer) throws UsernameAlreadyExists {
        logger.info("Registering user...");
        return new ResponseEntity<>(custService.saveCustomer(customer), HttpStatus.CREATED);

    }
    @PostMapping({"/findByEmail/{email}"})
    public List<Customer> findByEmail(@PathVariable("email") String email) {
        return custService.findByEmail(email);
    }

//    @PostMapping({"/saveMovie"})
//    public ResponseEntity<Object> saveMovie(@Valid @RequestBody Movie movie) throws UsernameAlreadyExists {
//        return new ResponseEntity<>(movieService.saveMovie(movie), HttpStatus.CREATED);
//
//    }
    @PostMapping({"/saveMovie"})
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Movie> saveMovie(@Valid @RequestBody MovieSaveDTO mt) throws UsernameAlreadyExists {
        logger.info("Saving Movie into Movie table");
        return new ResponseEntity<>(movieService.saveMovie(mt), HttpStatus.CREATED);

    }
    @ResponseBody
    @GetMapping("/forgot/{username}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<Map<String,String>>  forgotPassword(@PathVariable("username") String username) {
        logger.info("Forgot password initiated...");
        return new ResponseEntity<>( custService.forgotPassword(username), HttpStatus.FOUND);

    }
    @GetMapping({"/all"})
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<List<Movie>>  showMovies() {
        logger.info("Listing all the movies");
        return new ResponseEntity<>( movieService.finAllMovies(), HttpStatus.FOUND);
    }

    @GetMapping({"/show"})
    @PreAuthorize("hasRole('User')")
    public List<Customer> show() {
        return custService.showAllCustomer();
    }
    @PostMapping({"search/{moviename}"})
    public ResponseEntity<Movie>  searchByMovieName(@PathVariable("moviename") String moviename) throws CustomEcxeption {
        logger.info("Searching movieNaame in database");
        return new ResponseEntity<>(movieService.findByMoviename(moviename), HttpStatus.FOUND);
    }
    //<moviename>/add
    @PostMapping({"{moviename}/add"})
    @PreAuthorize("hasRole('User')")
    public int bookTicket(@PathVariable("moviename") String moviename) throws CustomEcxeption {
        logger.info("bookTicket method triggerred, validating moviename");
        return ticketService.bookTickets(moviename);
        //return new ResponseEntity<>(movieService.findByMoviename(moviename), HttpStatus.FOUND);
    }

    ///<moviename>/update/<ticket>
    @GetMapping({"{moviename}/update/{ticket}"})
    @PreAuthorize("hasRole('Admin')")
    public Movie updateTicket(@PathVariable("moviename") String moviename, @PathVariable("ticket") int ticketNo) throws CustomEcxeption {
        logger.info("UpdateTicket triggered, validating details");
        return ticketService.updateTickets(moviename, ticketNo);
    }
    //<moviename>/delete/<id>

    @DeleteMapping({"{moviename}/delete"})
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> deleteMovie(@PathVariable("moviename") String moviename) throws CustomEcxeption {
        logger.info("deleteMovie triggered, validating movieName");
        movieService.deleteMovie(moviename);
        return new ResponseEntity<>("Movie deleted succesfully", HttpStatus.OK);

    }



}
