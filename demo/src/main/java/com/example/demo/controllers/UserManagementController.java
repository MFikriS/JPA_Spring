package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Employee;
import com.example.demo.models.User;
import com.example.demo.models.Role;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("userManagement")
public class UserManagementController {
    @Autowired
    private UserService userService;
    

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = {"register"})
    public String create(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("user", new User());
        return "userManagement/register";
    }

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

    @GetMapping(value = {"login"})
    public String login(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("user", new User());
        return "userManagement/login";
    }

    @PostMapping(value = {"checkLogin"})
    public String checkLogin(Employee employee, User user){
        String cekLogin = employeeService.findEmailAndPassword(employee.getEmail(), user.getPassword());
        if(cekLogin != null){
            //model.addAttribute("employee", employeeService.getByEmail(employee.getEmail()));
            return "userManagement/dashboard";
        }else{
            return "userManagement/login";
        }
    }
}
