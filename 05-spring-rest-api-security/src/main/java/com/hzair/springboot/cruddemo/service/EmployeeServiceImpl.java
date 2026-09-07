package com.hzair.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzair.springboot.cruddemo.dao.EmployeeRepository;
import com.hzair.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {

        Optional<Employee> result = employeeRepository.findById(id);

        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            // * We can throw a RuntimeException if the employee is not found.
            throw new RuntimeException("Did not find employee id - " + id);
        }

        return theEmployee;
    }

    // * No need for @Transactional because Spring Data JPA handles transactions automatically.
    // @Transactional
    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    // * No need for @Transactional because Spring Data JPA handles transactions automatically.
    // @Transactional 
    @Override
    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

}
