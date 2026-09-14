package com.hzair.springboot.cruddemo.dao;

import java.util.List;

import com.hzair.springboot.cruddemo.entity.Course;
import com.hzair.springboot.cruddemo.entity.Instructor;
import com.hzair.springboot.cruddemo.entity.InstructorDetail;

public interface AppDao {
    public void save(Instructor instructor);

    public Instructor findInstructorById(int theId);

    public void deleteInstructorById(int theId);

    public InstructorDetail findInstructorDetailById(int theId);

    public void deleteInstructorDetailById(int theId);

    public List<Course> findCoursesByInstructorId(int theId);
    
    public Instructor findInstructorByIdJoinFetch(int theId);
    
    public Instructor update(Instructor instructor);

    public Course update(Course course);

    public Course findCourseById(int theId);

    public void deleteCourseById(int theId);

    public void save(Course course);

    public Course findCourseAndReviewsByCourseId(int theId);


}
