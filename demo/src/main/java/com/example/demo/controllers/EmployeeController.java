package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.UserService;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    
    @Autowired
    private UserService userService;
    

    @GetMapping()
    public String index(Model model){
        model.addAttribute("users", userService.getAll());
        return "employee/index";
    }

    /*
    @GetMapping(value = {"form"})
    public String create(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("user", new User());
        //model.addAttribute("roles", roleService.getByIdLevel());
        //model.addAttribute("roles", roleService.getAll());
        //model.addAttribute("divisions", divisionService.getAll());
        return "employee/form";
    }
    */

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

    /*
    @PostMapping("save")
    public String save(Employee employee, User user){
        Boolean result;
        Boolean result2;

        result = employeeService.save(employee);

        //System.out.println("id employee : " + employeeService.findIdByEmail(employee.getEmail()));
        //user.setId(employee.getId());

        user.setId(employeeService.findIdByEmail(employee.getEmail()));
        //Integer object = employeeService.findIdByEmail(employee.getEmail());
        Role role = new Role();
        role.setId(roleService.getByIdLevel());
        user.setRole(role);
        result2 = userService.save(user);
        if(result && result2){
            return "redirect:/employee";
        } else {
            return "employee/form";
        }
    }
    */

}
