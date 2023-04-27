package com.moviebookingapp.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TicketsTest {
    /**
     * Method under test: {@link Tickets#setNumberofTickets(int)}
     */
    @Test
    void testSetNumberofTickets() {
        Tickets tickets = new Tickets();
        tickets.setNumberofTickets(10);
        assertEquals("Book ASAP", tickets.getTicketStatus());
        assertEquals(10, tickets.getNumberofTickets());
    }

}

