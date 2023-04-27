package com.moviebookingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Customer")
public class Customer {


    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Id
    @NotNull
    private String userName;
    @NotNull
    //@Column(unique = true)
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;
    @NotNull
    private String contactNumber;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "customer_role",
            joinColumns = {
                    @JoinColumn(name = "username")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            })
    private Set<Role> role;
}
