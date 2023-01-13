package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    @Query(value = "SELECT e.id FROM Employee e WHERE e.Email = ?1)", nativeQuery = true)
    public Integer findIdByEmail(String Email);
}
