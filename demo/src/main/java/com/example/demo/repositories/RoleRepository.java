package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

    @Query(value = "SELECT r.id FROM Role r WHERE r.level = (SELECT MAX(r.level) FROM Role r)", nativeQuery = true)
    public Integer findIdByLevelMax();
    /*
    @Query("SELECT r.id FROM Role r WHERE r.id = (SELECT max(d.id) FROM Role d)")
    */
    //public Role max();
    

}
