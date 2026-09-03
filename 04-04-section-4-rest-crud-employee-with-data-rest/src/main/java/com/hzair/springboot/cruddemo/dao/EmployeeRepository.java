package com.hzair.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hzair.springboot.cruddemo.entity.Employee;

// * JpaRepository is the implementation of Spring Data JPA
// * It provides CRUD operations for the Employee entity Autmmatically
// * We don't need to implement DAO code anymore .

// * @RepositoryRestResource(path="members") // * This annotation is used to customize the REST endpoint path for the Employee entity.
public interface EmployeeRepository extends JpaRepository<Employee, Integer> { // * We need to specify the entity type "Employee" and the primary key type "Integer".
    
}
