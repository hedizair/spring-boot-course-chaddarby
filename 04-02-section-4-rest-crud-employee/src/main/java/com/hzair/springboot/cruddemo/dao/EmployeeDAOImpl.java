package com.hzair.springboot.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hzair.springboot.cruddemo.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        // * TypeQuery because we are returning a list of employees (if we return
        // something, we need to specify the type of the return value)

        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class);

        List<Employee> employees = theQuery.getResultList();

        return employees;

    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }

    // No @Transactional, because it will be handled by the service layer
    // (EmployeeServiceImpl)
    @Override
    public Employee save(Employee employee) {

        // If the employee.id = 0, then merge(...) will create a new emplyee
        // If the employee.id > 0, then merge(...) will update the existing employee
        Employee dbEmployee = entityManager.merge(employee);
        return dbEmployee;
    }

    // No @Transactional, because it will be handled by the service layer
    // (EmployeeServiceImpl)
    @Override
    public void delete(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
    }
}
