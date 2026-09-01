package com.hzair.springboot.cruddemo.dao;

import java.util.List;

import com.hzair.springboot.cruddemo.entity.Employee;

public interface EmployeeDAO {
    List<Employee> findAll();
    // Employee findById(int id);
    // void save(Employee employee);
    // void delete(int id);
    
}
