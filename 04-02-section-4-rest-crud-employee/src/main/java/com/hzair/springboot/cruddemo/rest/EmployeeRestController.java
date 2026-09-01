package com.hzair.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hzair.springboot.cruddemo.dao.EmployeeDAO;
import com.hzair.springboot.cruddemo.entity.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    
    private EmployeeDAO employeeDAO;

    public EmployeeRestController(EmployeeDAO employeeDAO) { // * Constructor injection of the EmployeeDAO dependency - Because of the @Autowired annotation on EmployeeDAOImpl
        this.employeeDAO = employeeDAO;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

}
