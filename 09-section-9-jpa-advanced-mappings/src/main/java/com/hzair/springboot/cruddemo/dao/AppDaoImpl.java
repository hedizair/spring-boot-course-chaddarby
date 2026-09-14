package com.hzair.springboot.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hzair.springboot.cruddemo.entity.Course;
import com.hzair.springboot.cruddemo.entity.Instructor;
import com.hzair.springboot.cruddemo.entity.InstructorDetail;
import com.hzair.springboot.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class AppDaoImpl implements AppDao {

    private EntityManager entityManager;

    @Autowired
    public AppDaoImpl(EntityManager entityManger) {
        this.entityManager = entityManger;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        Instructor instructorToDelete = entityManager.find(Instructor.class, theId);

        // We need to remove the association between instructor & course
        for (Course tempCourse : instructorToDelete.getCourses()) {
            tempCourse.setInstructor(null);
        }

        entityManager.remove(instructorToDelete);
    }

    // * By default, jpa will do a eager load (and not lazy so) :
    // * load all the subressources associated to the entity (so Instructor +
    // InstructorDetail)
    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {

        InstructorDetail instructorDetailToDelete = entityManager.find(InstructorDetail.class, theId);

        instructorDetailToDelete.getInstructor().setInstructorDetail(null); // ! Comprend pas pk on doit faire ça étant
                                                                            // donné que c'est deja en cascade

        entityManager.remove(instructorDetailToDelete);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        System.out.println("Before query");
        // ! FROM {EntityName} !!!!!!!
        TypedQuery<Course> query = entityManager.createQuery("FROM Course WHERE instructor.id=:data", Course.class);

        query.setParameter("data", theId);

        List<Course> courses = query.getResultList();
        System.out.println("After query");

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {

        TypedQuery<Instructor> query = entityManager.createQuery("SELECT i FROM Instructor i "
                + "JOIN FETCH i.courses "
                + "JOIN FETCH i.instructorDetail  "
                + "WHERE i.id = :data", Instructor.class);

        query.setParameter("data", theId);

        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public Instructor update(Instructor instructor) {
        return entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public Course update(Course course) {
        return entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        Course courseToDelete = entityManager.find(Course.class, theId);

        entityManager.remove(courseToDelete);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c " +
                        "JOIN FETCH c.reviews " +
                        "WHERE c.id = :data",
                Course.class);

        query.setParameter("data", theId);

        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c " +
                        "JOIN FETCH c.students " +
                        "WHERE c.id = :data",
                Course.class);

        query.setParameter("data", theId);

        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s FROM Student s " +
                        "JOIN FETCH s.courses " +
                        "WHERE s.id = :data",
                Student.class);

        query.setParameter("data", theId);

        Student student = query.getSingleResult();

        return student;
    }

    @Override
    @Transactional
    public Student update(Student student) {
        return entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {

        Student student = entityManager.find(Student.class, theId);

        if (student != null) {
            List<Course> courses = student.getCourses();
            for (Course course : courses) {
                course.getStudents().remove(student);
            }

            entityManager.remove(student);
        }

    }

}
