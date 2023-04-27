package com.moviebookingapp.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

class JwtResponseTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link JwtResponse#JwtResponse(Customer, String)}
     *   <li>{@link JwtResponse#setJwtToken(String)}
     *   <li>{@link JwtResponse#setUser(Customer)}
     *   <li>{@link JwtResponse#getJwtToken()}
     *   <li>{@link JwtResponse#getUser()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Customer user = new Customer();
        user.setConfirmPassword("iloveyou");
        user.setContactNumber("42");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRole(new HashSet<>());
        user.setUserName("janedoe");
        JwtResponse actualJwtResponse = new JwtResponse(user, "ABC123");
        actualJwtResponse.setJwtToken("ABC123");
        Customer user2 = new Customer();
        user2.setConfirmPassword("iloveyou");
        user2.setContactNumber("42");
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setRole(new HashSet<>());
        user2.setUserName("janedoe");
        actualJwtResponse.setUser(user2);
        assertEquals("ABC123", actualJwtResponse.getJwtToken());
        Customer user3 = actualJwtResponse.getUser();
        assertSame(user2, user3);
        assertEquals(user, user3);
    }
}

