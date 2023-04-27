package com.moviebookingapp.dao;

import com.moviebookingapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository<Customer, String> {
    List<Customer> findByUserName(String loginId);


    Optional<Customer> findById(String loginId);

    List<Customer> findByEmail(String email);
}
