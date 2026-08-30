package com.hzair.cruddemo.dao;

import java.util.List;

import com.hzair.cruddemo.entity.Student;

public interface StudentDAO {
    void save(Student student);
    Student findById(Integer id);
    List<Student> findAll();
    List<Student> findByLastName(String theLastName);
    void update(Student student);
    void delete(Integer id);
    Integer deleteAll();
}
