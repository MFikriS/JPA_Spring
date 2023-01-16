package com.example.demo.repositories;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import com.example.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    // @Query(value = "SELECT u.password FROM user u WHERE u.password = ?1", nativeQuery = true)
    // public String findPassword(String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user u SET u.password = :password WHERE u.id = :id", nativeQuery = true)
    public void changePassword(@Param("password") String password, @Param("id") Integer id);
}
