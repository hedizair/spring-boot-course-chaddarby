package com.hzair.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hzair.springboot.cruddemo.entity.Employee;

// * JpaRepository is the implementation of Spring Data JPA
// * It provides CRUD operations for the Employee entity Autmmatically
// * We don't need to implement DAO code anymore .
public interface EmployeeRepository extends JpaRepository<Employee, Integer> { // * We need to specify the entity type "Employee" and the primary key type "Integer".
    
}
