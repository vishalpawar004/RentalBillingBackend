package com.vis.rental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vis.rental.entity.User;

public interface UserRespository extends JpaRepository<User, Integer>{

	@Query("""
	        SELECT u FROM User u
	        JOIN FETCH u.roles
	        WHERE u.username = :username
	    """)
	    Optional<User> findByUsername(@Param("username") String username);

		boolean existsByUsername(String username);
}
