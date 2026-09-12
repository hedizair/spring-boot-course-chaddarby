package com.hzair.springboot.cruddemo.dao;

import com.hzair.springboot.cruddemo.entity.Instructor;
import com.hzair.springboot.cruddemo.entity.InstructorDetail;

public interface AppDao {
    public void save(Instructor instructor);

    public Instructor findInstructorById(int theId);

    public void deleteInstructorById(int theId);

    public InstructorDetail findInstructorDetailById(int theId);

}
