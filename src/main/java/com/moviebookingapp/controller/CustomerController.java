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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "Login", description = "Login for all")
    @PostMapping({"/login"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        logger.info("Logging in User");
        return jwtService.createJwtToken(jwtRequest);
    }


    public CustomerController(CustomerService custService) {
        this.custService = custService;
    }


    @Operation(summary = "Register", description = "register for all")
    @PostMapping({"/register"})
    public ResponseEntity<Object> registerNewUser(@Valid @RequestBody Customer customer) throws UsernameAlreadyExists {
        logger.info("Registering user...");
        return new ResponseEntity<>(custService.saveCustomer(customer), HttpStatus.CREATED);

    }

    @Operation(summary = "Save movie", description = "Save Movie by Admin")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping({"/saveMovie"})
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Movie> saveMovie(@Valid @RequestBody MovieSaveDTO mt) throws UsernameAlreadyExists {
        logger.info("Saving Movie into Movie table");
        return new ResponseEntity<>(movieService.saveMovie(mt), HttpStatus.CREATED);

    }

    @Operation(summary = "Forgot password", description = "forget password by User")
    @SecurityRequirement(name = "Bearer Authentication")
    @ResponseBody
    @GetMapping("/forgot/{username}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<Map<String,String>>  forgotPassword(@PathVariable("username") String username) {
        logger.info("Forgot password initiated...");
        return new ResponseEntity<>( custService.forgotPassword(username), HttpStatus.FOUND);

    }
    @Operation(summary = "List all movies", description = "list all movies by User")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping({"/all"})
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<List<Movie>>  showMovies() {
        logger.info("Listing all the movies");
        return new ResponseEntity<>( movieService.finAllMovies(), HttpStatus.FOUND);
    }

    @Operation(summary = "Show All Customers", description = "list sll Shows by User")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping({"/show"})
    @PreAuthorize("hasRole('User')")
    public List<Customer> show() {
        return custService.showAllCustomer();
    }

    @Operation(summary = "Search movie", description = "Search movie by User")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping({"search/{moviename}"})
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<Movie>  searchByMovieName(@PathVariable("moviename") String moviename) throws CustomEcxeption {
        logger.info("Searching movieNaame in database");
        return new ResponseEntity<>(movieService.findByMoviename(moviename), HttpStatus.FOUND);
    }

    @Operation(summary = "Book a ticket", description = "Booking by User")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping({"{moviename}/add"})
    @PreAuthorize("hasRole('User')")
    public int bookTicket(@PathVariable("moviename") String moviename) throws CustomEcxeption {
        logger.info("bookTicket method triggerred, validating moviename");
        return ticketService.bookTickets(moviename);
        //return new ResponseEntity<>(movieService.findByMoviename(moviename), HttpStatus.FOUND);
    }

    @Operation(summary = "Update ticket numbers", description = "update ticket by Admin")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping({"{moviename}/update/{ticket}"})
    @PreAuthorize("hasRole('Admin')")
    public Movie updateTicket(@PathVariable("moviename") String moviename, @PathVariable("ticket") int ticketNo) throws CustomEcxeption {
        logger.info("UpdateTicket triggered, validating details");
        return ticketService.updateTickets(moviename, ticketNo);
    }


    @Operation(summary = "Delete movie", description = "Delete movie by Admin")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping({"{moviename}/delete"})
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> deleteMovie(@PathVariable("moviename") String moviename) throws CustomEcxeption {
        logger.info("deleteMovie triggered, validating movieName");
        movieService.deleteMovie(moviename);
        return new ResponseEntity<>("Movie deleted successfully", HttpStatus.OK);

    }



}
