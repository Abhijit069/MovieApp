package com.moviebookingapp.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviebookingapp.dto.MovieSaveDTO;
import com.moviebookingapp.entity.Customer;
import com.moviebookingapp.entity.JwtRequest;
import com.moviebookingapp.entity.JwtResponse;
import com.moviebookingapp.entity.Movie;
import com.moviebookingapp.entity.Tickets;
import com.moviebookingapp.service.CustomerService;
import com.moviebookingapp.service.JwtService;
import com.moviebookingapp.service.MovieService;
import com.moviebookingapp.service.TicketService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CustomerController.class})
@ExtendWith(SpringExtension.class)
class CustomerControllerTest {
    @Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private MovieService movieService;

    @MockBean
    private TicketService ticketService;

    /**
     * Method under test: {@link CustomerController#initRoleAndUser()}
     */
    @Test
    void testInitRoleAndUser() {
        doNothing().when(customerService).initRoleandAdmin();
        customerController.initRoleAndUser();
        verify(customerService).initRoleandAdmin();
        assertTrue(customerController.logger instanceof ch.qos.logback.classic.Logger);
    }

    /**
     * Method under test: {@link CustomerController#bookTicket(String)}
     */
    @Test
    void testBookTicket() throws Exception {
        when(ticketService.bookTickets(Mockito.<String>any())).thenReturn(1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/moviebooking/{moviename}/add",
                "Moviename");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link CustomerController#createJwtToken(JwtRequest)}
     */
    @Test
    void testCreateJwtToken() throws Exception {
        when(jwtService.createJwtToken(Mockito.<JwtRequest>any())).thenReturn(new JwtResponse(new Customer(), "ABC123"));

        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUserName("janedoe");
        jwtRequest.setUserPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(jwtRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/moviebooking/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"user\":{\"firstName\":null,\"lastName\":null,\"userName\":null,\"email\":null,\"password\":null,\"confirmPassword"
                                        + "\":null,\"contactNumber\":null,\"role\":null},\"jwtToken\":\"ABC123\"}"));
    }

    /**
     * Method under test: {@link CustomerController#findByEmail(String)}
     */
    @Test
    void testFindByEmail() throws Exception {
        when(customerService.findByEmail(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/moviebooking/findByEmail/{email}",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CustomerController#findByEmail(String)}
     */
    @Test
    void testFindByEmail2() throws Exception {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");

        ArrayList<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerService.findByEmail(Mockito.<String>any())).thenReturn(customerList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/moviebooking/findByEmail/{email}",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"userName\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\""
                                        + ":\"iloveyou\",\"confirmPassword\":\"iloveyou\",\"contactNumber\":\"42\",\"role\":[]}]"));
    }

    /**
     * Method under test: {@link CustomerController#saveMovie(MovieSaveDTO)}
     */
    @Test
    void testSaveMovie() throws Exception {
        Tickets ticket = new Tickets();
        ticket.setMoviename("Moviename");
        ticket.setNumberofTickets(10);
        ticket.setSeatNumbers(10);
        ticket.setTheatreName("Theatre Name");
        ticket.setTicketId("42");
        ticket.setTicketStatus("Ticket Status");

        Movie movie = new Movie();
        movie.setMovieDescription("Movie Description");
        movie.setMovieGenre("Movie Genre");
        movie.setMovieLanguage("en");
        movie.setMovieMins("Movie Mins");
        movie.setMovieRating("Movie Rating");
        movie.setMovieid("Movieid");
        movie.setMoviename("Moviename");
        movie.setTheatreName("Theatre Name");
        movie.setTicket(ticket);
        when(movieService.saveMovie(Mockito.<MovieSaveDTO>any())).thenReturn(movie);

        MovieSaveDTO movieSaveDTO = new MovieSaveDTO();
        movieSaveDTO.setMovieDescription("Movie Description");
        movieSaveDTO.setMovieGenre("Movie Genre");
        movieSaveDTO.setMovieLanguage("en");
        movieSaveDTO.setMovieMins("Movie Mins");
        movieSaveDTO.setMovieRating("Movie Rating");
        movieSaveDTO.setMoviename("Moviename");
        movieSaveDTO.setNumberofTickets(10);
        movieSaveDTO.setSeatNumbers(10);
        movieSaveDTO.setTheatreName("Theatre Name");
        String content = (new ObjectMapper()).writeValueAsString(movieSaveDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/moviebooking/saveMovie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"movieid\":\"Movieid\",\"moviename\":\"Moviename\",\"movieGenre\":\"Movie Genre\",\"movieMins\":\"Movie Mins\","
                                        + "\"movieLanguage\":\"en\",\"movieDescription\":\"Movie Description\",\"movieRating\":\"Movie Rating\",\"theatreName"
                                        + "\":\"Theatre Name\",\"ticket\":{\"ticketId\":\"42\",\"moviename\":\"Moviename\",\"theatreName\":\"Theatre Name\","
                                        + "\"numberofTickets\":10,\"seatNumbers\":10,\"ticketStatus\":\"Ticket Status\"}}"));
    }

    /**
     * Method under test: {@link CustomerController#forgotPassword(String)}
     */
    @Test
    void testForgotPassword() throws Exception {
        when(customerService.forgotPassword(Mockito.<String>any())).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/moviebooking/forgot/{username}",
                "janedoe");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link CustomerController#forgotPassword(String)}
     */
    @Test
    void testForgotPassword2() throws Exception {
        when(customerService.forgotPassword(Mockito.<String>any())).thenReturn(new HashMap<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/moviebooking/forgot/{username}",
                "janedoe");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }

    /**
     * Method under test: {@link CustomerController#deleteMovie(String)}
     */
    @Test
    void testDeleteMovie() throws Exception {
        doNothing().when(movieService).deleteMovie(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/moviebooking/{moviename}/delete",
                "Moviename");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Movie deleted succesfully"));
    }

    /**
     * Method under test: {@link CustomerController#registerNewUser(Customer)}
     */
    @Test
    void testRegisterNewUser() throws Exception {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");
        when(customerService.saveCustomer(Mockito.<Customer>any())).thenReturn(customer);

        Customer customer2 = new Customer();
        customer2.setConfirmPassword("iloveyou");
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(customer2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/moviebooking/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"userName\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\":"
                                        + "\"iloveyou\",\"confirmPassword\":\"iloveyou\",\"contactNumber\":\"42\",\"role\":[]}"));
    }

    /**
     * Method under test: {@link CustomerController#searchByMovieName(String)}
     */
    @Test
    void testSearchByMovieName() throws Exception {
        Tickets ticket = new Tickets();
        ticket.setMoviename("Moviename");
        ticket.setNumberofTickets(10);
        ticket.setSeatNumbers(10);
        ticket.setTheatreName("Theatre Name");
        ticket.setTicketId("42");
        ticket.setTicketStatus("Ticket Status");

        Movie movie = new Movie();
        movie.setMovieDescription("Movie Description");
        movie.setMovieGenre("Movie Genre");
        movie.setMovieLanguage("en");
        movie.setMovieMins("Movie Mins");
        movie.setMovieRating("Movie Rating");
        movie.setMovieid("Movieid");
        movie.setMoviename("Moviename");
        movie.setTheatreName("Theatre Name");
        movie.setTicket(ticket);
        when(movieService.findByMoviename(Mockito.<String>any())).thenReturn(movie);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/moviebooking/search/{moviename}",
                "Moviename");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"movieid\":\"Movieid\",\"moviename\":\"Moviename\",\"movieGenre\":\"Movie Genre\",\"movieMins\":\"Movie Mins\","
                                        + "\"movieLanguage\":\"en\",\"movieDescription\":\"Movie Description\",\"movieRating\":\"Movie Rating\",\"theatreName"
                                        + "\":\"Theatre Name\",\"ticket\":{\"ticketId\":\"42\",\"moviename\":\"Moviename\",\"theatreName\":\"Theatre Name\","
                                        + "\"numberofTickets\":10,\"seatNumbers\":10,\"ticketStatus\":\"Ticket Status\"}}"));
    }

    /**
     * Method under test: {@link CustomerController#show()}
     */
    @Test
    void testShow() throws Exception {
        when(customerService.showAllCustomer()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/moviebooking/show");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CustomerController#show()}
     */
    @Test
    void testShow2() throws Exception {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");

        ArrayList<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerService.showAllCustomer()).thenReturn(customerList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/moviebooking/show");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"userName\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"password\""
                                        + ":\"iloveyou\",\"confirmPassword\":\"iloveyou\",\"contactNumber\":\"42\",\"role\":[]}]"));
    }

    /**
     * Method under test: {@link CustomerController#showMovies()}
     */
    @Test
    void testShowMovies() throws Exception {
        when(movieService.finAllMovies()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/moviebooking/all");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CustomerController#showMovies()}
     */
    @Test
    void testShowMovies2() throws Exception {
        when(movieService.finAllMovies()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/moviebooking/all");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CustomerController#updateTicket(String, int)}
     */
    @Test
    void testUpdateTicket() throws Exception {
        Tickets ticket = new Tickets();
        ticket.setMoviename("Moviename");
        ticket.setNumberofTickets(10);
        ticket.setSeatNumbers(10);
        ticket.setTheatreName("Theatre Name");
        ticket.setTicketId("42");
        ticket.setTicketStatus("Ticket Status");

        Movie movie = new Movie();
        movie.setMovieDescription("Movie Description");
        movie.setMovieGenre("Movie Genre");
        movie.setMovieLanguage("en");
        movie.setMovieMins("Movie Mins");
        movie.setMovieRating("Movie Rating");
        movie.setMovieid("Movieid");
        movie.setMoviename("Moviename");
        movie.setTheatreName("Theatre Name");
        movie.setTicket(ticket);
        when(ticketService.updateTickets(Mockito.<String>any(), anyInt())).thenReturn(movie);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/moviebooking/{moviename}/update/{ticket}", "Moviename", 1);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"movieid\":\"Movieid\",\"moviename\":\"Moviename\",\"movieGenre\":\"Movie Genre\",\"movieMins\":\"Movie Mins\","
                                        + "\"movieLanguage\":\"en\",\"movieDescription\":\"Movie Description\",\"movieRating\":\"Movie Rating\",\"theatreName"
                                        + "\":\"Theatre Name\",\"ticket\":{\"ticketId\":\"42\",\"moviename\":\"Moviename\",\"theatreName\":\"Theatre Name\","
                                        + "\"numberofTickets\":10,\"seatNumbers\":10,\"ticketStatus\":\"Ticket Status\"}}"));
    }
}

