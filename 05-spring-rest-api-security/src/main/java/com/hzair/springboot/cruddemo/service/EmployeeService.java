package com.hzair.springboot.cruddemo.service;

import java.util.List;

import com.hzair.springboot.cruddemo.entity.Employee;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    void delete(int id);

}
