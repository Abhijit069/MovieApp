package com.moviebookingapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")
@DynamicUpdate
public class Tickets {

//    public Tickets(String moviename, String theatreName, int numberofTickets, String seatNumbers) {
//        this.moviename = moviename;
//        this.theatreName = theatreName;
//        this.numberofTickets = numberofTickets;
//        this.seatNumbers = seatNumbers;
//    }

    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private String ticketId;

    @Id
    private String moviename;

    @NotNull
    private String theatreName;

    @NotNull
    private int numberofTickets;

    @NotNull
    private int seatNumbers;

    @NotNull
    String ticketStatus;

    public void setNumberofTickets(int numberofTickets) {
        this.numberofTickets = numberofTickets;
        setTicketStatus("Book ASAP");
    }

    //    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize(using = LocalDateSerializer.class)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private LocalDate movieDate;
}
