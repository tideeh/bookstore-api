package com.example.bookstoreapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bookstoreapi.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Query é feita no objeto, e nao no banco
    @Query("SELECT u FROM User u WHERE u.userName=:userName")
    User findByUsername(@Param("userName") String userName);
}