package com.luv2code.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

    // ! Spring Data JPA will automatically create the code behind findAllByPrderByLastNameAsc()
    // ! No need to write code, just need to respect the specific format of the method name
    // ! findAllBy + OrderByLastNameAsc - And we can do that with all the Employee fields & ASC or DESC
    public List<Employee> findAllByOrderByLastNameAsc();

}
