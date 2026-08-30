package com.hzair.cruddemo.dao;

import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hzair.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private EntityManager entityManager;

    @Autowired // optional if one constructor
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        this.entityManager.persist(student);
    }

    @Override // No need transactional for getting data
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);
        // TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student ORDER
        // BY lastName DESC", Student.class); :: Can be sorted with order by
        List<Student> resultList = theQuery.getResultList();
        // return query result
        return resultList;
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        // Create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName = :theData",
                Student.class);

        // Set query params
        theQuery.setParameter("theData", theLastName);

        // return query result
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Student studentToDelete = entityManager.find(Student.class, id);
        entityManager.remove(studentToDelete);
    }

    @Override
    @Transactional
    public Integer deleteAll() {
        // Create query
        Query theQuery = entityManager.createQuery("DELETE FROM Student");

        // Execute query and retrieve nb lines affected
        Integer nbRowsDeleted = theQuery.executeUpdate();

        return nbRowsDeleted;
    }
}
