package com.moviebookingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieSaveDTO {


    private String moviename;
    private String movieGenre;
    private String movieMins;
    private String movieLanguage;
    private String movieDescription;
    private String movieRating;
    private String theatreName;
    private int numberofTickets;
private int seatNumbers;
}
