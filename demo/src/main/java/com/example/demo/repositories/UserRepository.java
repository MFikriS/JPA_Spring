package com.example.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    /*
    @Query("SELECT t FROM Thing t WHERE t.fooIn = ?1 AND t.bar = ?2")
    User findByFooInAndBar(String fooIn, String bar);
    List<User> findByEmail(String email);

    List<User> findByPassword(String password);
    */
}
