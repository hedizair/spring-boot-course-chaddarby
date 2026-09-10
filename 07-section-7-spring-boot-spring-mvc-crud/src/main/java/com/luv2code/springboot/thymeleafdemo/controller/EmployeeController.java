package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired // * Optionnal if only one constructor */
    public EmployeeController(EmployeeService employeeService) { // * We define the constructor because we need the
                                                                 // automatic injection for the EmployeeService
        this.employeeService = employeeService;

    }

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        List<Employee> employeeList = employeeService.findAll();

        theModel.addAttribute("employees", employeeList);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        model.addAttribute("employee", new Employee());

        return "employees/employees-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int employeeId, Model model) {

        Employee theEmployee = employeeService.findById(employeeId);

        model.addAttribute("employee", theEmployee);

        return "employees/employees-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
        employeeService.save(theEmployee);

        return "redirect:/employees/list"; // ! redirect is for security to prevent duplicate submission (idempotency)
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int theId) {

        employeeService.deleteById(theId);

        return "redirect:/employees/list";
    }

}
