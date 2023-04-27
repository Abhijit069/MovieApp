package com.moviebookingapp.dao;

import com.moviebookingapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, String> {
	
	@Query("from Role where roleName='Admin'")
	Role getAdmin();
}
