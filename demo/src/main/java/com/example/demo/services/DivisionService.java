package com.example.demo.services;

import java.util.List;

//import org.springframework.stereotype.Service;

import com.example.demo.models.Division;

public interface DivisionService {
    public List<Division> getAll();
    public Division getById(Integer id);
    public Boolean save(Division division);
    public Boolean delete(Integer id);
}
