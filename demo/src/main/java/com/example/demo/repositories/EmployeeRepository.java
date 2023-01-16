package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.loginDTO;
import com.example.demo.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    @Query(value = "SELECT e.id FROM Employee e WHERE e.email = ?1", nativeQuery = true)
    public Integer findIdByEmail(String Email);

    @Query(value = "SELECT e.email, u.password FROM employee e JOIN user u ON e.id = u.id WHERE e.email = ?1 && password = ?2", nativeQuery = true)
    public String findEmailAndPassword(String email, String password);

    @Query(value = "SELECT e.email FROM employee e WHERE e.email = ?1", nativeQuery = true)
    public String findEmail(String email);

    @Query(value = "SELECT new com.example.demo.dto.loginDTO"
                    + "(u.id, u.password, u.employee.id, u.employee.fullname, u.employee.email, u.role.rolename)"
                    + " FROM user u WHERE u.employee.email = ?1 && u.password = ?2", nativeQuery = true)
    public loginDTO logindto(String email, String password);

    //@Query(value = "SELECT e.id FROM Employee e WHERE e.email = ?1", nativeQuery = true)
    //public Employee getByEmail(String email);
}
