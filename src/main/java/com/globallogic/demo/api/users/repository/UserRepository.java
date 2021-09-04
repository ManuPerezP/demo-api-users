package com.globallogic.demo.api.users.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globallogic.demo.api.users.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>  {

    @Query("Select u from User u Where u.email=?1")
    List<User> findByUserEmail(String mail);
    
    @Query("Select u from User u Where u.id=?1")
    Optional<User> findByUserId(UUID id);
}
