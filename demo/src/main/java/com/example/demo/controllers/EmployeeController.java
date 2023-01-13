package com.example.demo.controllers;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Employee;
import com.example.demo.models.User;
import com.example.demo.models.Role;
import com.example.demo.services.EmployeeService;
//import com.example.demo.models.User;
//import com.example.demo.services.DivisionService;
//import com.example.demo.services.EmployeeService;
//import com.example.demo.services.RoleService;
//import com.example.demo.services.UserService;
import com.example.demo.services.UserService;
import com.example.demo.services.RoleService;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    
    @Autowired
    private UserService userService;
    

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleService roleService;

    @GetMapping()
    public String index(Model model){
        
        model.addAttribute("users", userService.getAll());
        return "employee/index";
    }

    @GetMapping(value = {"form"})
    public String create(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("user", new User());
        //model.addAttribute("roles", roleService.getByIdLevel());
        //model.addAttribute("roles", roleService.getAll());
        //model.addAttribute("divisions", divisionService.getAll());
        return "employee/form";
    }

    /*
    @GetMapping(value = {"form", "form/{id}"})
    public String create(@PathVariable (required = false) Integer id, Model model){
        if(id != null){
            model.addAttribute("employee", employeeService.getById(id));
            //model.addAttribute("divisions", divisionService.getAll());
        } else {
            model.addAttribute("employee", new Employee());
            //model.addAttribute("divisions", divisionService.getAll());
        }
        return "employee/form";
    }
    */
    
    /*
    @PostMapping("save")
    public String save(@Nullable Employee employee, @Nullable User user){
        Boolean result, result2;
        result = employeeService.save(employee);
        result2 = userService.save(user);
        if(result && result2){
            return "redirect:/employee";
        } else {
            return "employee/form";
        }
    }
    */

    @PostMapping("save")
    public String save(Employee employee, User user){
        Boolean result;
        Boolean result2;

        result = employeeService.save(employee);

        user.setId(employeeService.findIdByEmail(employee.getEmail()));
        result2 = userService.save(user);
        if(result && result2){
            return "redirect:/employee";
        } else {
            return "employee/form";
        }
    }

    @GetMapping(value = {"login"})
    public String login(Model model){
        model.addAttribute("users", new User());
        return "employee/login";
    }

    @GetMapping(value = {"checkLogin"})
    public String cekLogin(Model model){
        model.addAttribute("users", new User());
        return "employee/login";
    }

}
