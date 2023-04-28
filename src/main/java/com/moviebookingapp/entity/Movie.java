package com.moviebookingapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "movie")
@DynamicUpdate
public class Movie {
    @Id
   // @GeneratedValue
    private String movieid;
    @NotNull
    private String moviename;
    @NotNull
    private String movieGenre;
    @NotNull
    private String movieMins;
    @NotNull
    private String movieLanguage;

    public Movie(String movieid,String moviename, String movieGenre, String movieMins, String movieLanguage, String movieDescription, String movieRating, String theatreName) {

        this.movieid=movieid;
        this.moviename = moviename;
        this.movieGenre = movieGenre;
        this.movieMins = movieMins;
        this.movieLanguage = movieLanguage;
        this.movieDescription = movieDescription;
        this.movieRating = movieRating;
        this.theatreName = theatreName;
    }

    @NotNull
    private String movieDescription;
    @NotNull
    private String movieRating;

    @NotNull
    private String theatreName;


//    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY,
//            cascade = CascadeType.MERGE)
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "moviename", referencedColumnName = "moviename",insertable=false, updatable=false)

    private Tickets ticket;


}
