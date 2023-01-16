package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("registrasi")
    public String registrasi(Employee employee, User user){
        Boolean result;
        Boolean result2;
        String email;

        
        email = employeeService.findEmail(employee.getEmail());
        if(email == null){
            result = employeeService.save(employee);
            user.setId(employeeService.findIdByEmail(employee.getEmail()));
            //Integer object = employeeService.findIdByEmail(employee.getEmail());

            Role role = new Role();
            role.setId(roleService.getIdByMaxLevel());
            user.setRole(role);
            result2 = userService.save(user);
            if(result && result2){
                return "redirect:/employee";
            } else {
                return "userManagement/register";
            }
        } else {
            return "userManagement/register";
        }

        //System.out.println("id employee : " + employeeService.findIdByEmail(employee.getEmail()));
        //user.setId(employee.getId());
        
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
        System.out.println("Cek Login : " + employeeService.findEmailAndPassword(employee.getEmail(), user.getPassword()));
        if(cekLogin != null){
            //model.addAttribute("employee", employeeService.getByEmail(employee.getEmail()));
            return "userManagement/dashboard";
        }else{
            return "userManagement/login";
        }
    }

    @GetMapping(value = {"changePassword/{id}"})
    public String create(@PathVariable(required = false) Integer id, Model model){
        model.addAttribute("user", userService.getById(id));
        return "userManagement/changePassword";
    }

    @PostMapping(value = {"updatePassword"})
    public String updatePassword(User user){
        Boolean result;
        result = userService.changePassword(user.getPassword(), user.getId());
        if(result){
            return "redirect:/employee";
        } else {
            return "userManagement/changePassword";
        }
    }

    @GetMapping(value = {"forgotPassword"})
    public String forgotPassword(Model model){
        model.addAttribute("employee", new Employee());
        return "userManagement/forgotPassword";
    }

    @PostMapping(value = {"checkEmail"})
    public String checkEmail(Employee employee, Model model){
        Integer id;
        id = employeeService.findIdByEmail(employee.getEmail());
        if(id != null){
            model.addAttribute("user", userService.getById(id));
            return "userManagement/changePassword";
        } else {
            return "userManagement/forgotPassword";
        }
    }
}
