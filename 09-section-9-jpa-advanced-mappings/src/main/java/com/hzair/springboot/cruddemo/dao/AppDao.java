package com.hzair.springboot.cruddemo.dao;

import java.util.List;

import com.hzair.springboot.cruddemo.entity.Course;
import com.hzair.springboot.cruddemo.entity.Instructor;
import com.hzair.springboot.cruddemo.entity.InstructorDetail;
import com.hzair.springboot.cruddemo.entity.Student;

public interface AppDao {
    void save(Instructor instructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    Instructor update(Instructor instructor);

    Course update(Course course);

    Course findCourseById(int theId);

    void deleteCourseById(int theId);

    void save(Course course);

    Course findCourseAndReviewsByCourseId(int theId);

    Course findCourseAndStudentsByCourseId(int theId);

    Student findStudentAndCoursesByStudentId(int theId);

    Student update(Student student);

    void deleteStudentById(int theId);

}
