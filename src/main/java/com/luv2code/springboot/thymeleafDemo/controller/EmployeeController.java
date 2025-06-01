package com.luv2code.springboot.thymeleafDemo.controller;

import com.luv2code.springboot.thymeleafDemo.entity.Employee;
import com.luv2code.springboot.thymeleafDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;


    //constructor injection
    @Autowired
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    //add mapping for listing employees
    @GetMapping("/list")
    public String listEmployees(Model theModel){
        //get employees from db
        List<Employee> theEmployees = employeeService.findAll();

        theModel.addAttribute("employees", theEmployees);
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        //create model attribute to bind the data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        //save the employee
        employeeService.save(theEmployee);

        //use redirect to prevent multiple submissions
        return "redirect:/employees/list";

    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel){
        //get the employee id from the service
        Employee theEmployee = employeeService.findById(theId);

        //set the employee in the model to populate the form
        theModel.addAttribute("employee", theEmployee);


        //send over to form
        return "employees/employee-form";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId){
        //delete the employee
        employeeService.deleteById(theId);

        //redirect to employees/list
        return "redirect:/employees/list";
    }



}
