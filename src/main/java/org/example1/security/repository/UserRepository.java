package org.example1.security.repository;

import org.example1.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select u from User u join fetch u.roles where u.email=:username")
    Optional<User> findByUsername(String username);


    @Query("select u from User u join fetch u.roles")
    List<User> findAllWithRoles();
}
