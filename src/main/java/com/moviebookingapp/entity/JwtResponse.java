package com.moviebookingapp.entity;

public class JwtResponse {

//    private User user;
//    private String jwtToken;
//
//    public JwtResponse(User user, String jwtToken) {
//        this.user = user;
//        this.jwtToken = jwtToken;
//    }

    private Customer user;
    private String jwtToken;

    public JwtResponse(Customer user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
