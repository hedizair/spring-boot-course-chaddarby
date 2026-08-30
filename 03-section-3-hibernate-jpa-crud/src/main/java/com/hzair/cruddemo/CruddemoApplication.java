package com.hzair.cruddemo;

import com.hzair.cruddemo.dao.StudentDAOImpl;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hzair.cruddemo.dao.StudentDAO;
import com.hzair.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

	private final StudentDAOImpl studentDAOImpl;

	CruddemoApplication(StudentDAOImpl studentDAOImpl) {
		this.studentDAOImpl = studentDAOImpl;
	}

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	// This code will run after the Spring Boot application starts and the spring
	// beans has been loaded
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {

			// createStudent(studentDAO);
			createMultipleStudent(studentDAO);
			// readStudent(studentDAO);
			// queryForStudent(studentDAO);
			// queryForStudentByLastName(studentDAO);
			// updateStudent(studentDAO);
			// deleteStudent(studentDAO);
			// deleteAllStudents(studentDAO);

		};
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating a new student ...");
		Student tempStudent = new Student("Hédi", "Zaïr", "hedi.marwan@outlook.fr");
		// save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		// display the id of the saved student
		System.out.println("Saved student. Generated id : " + tempStudent.getId());
	}

	private void createMultipleStudent(StudentDAO studentDAO) {
		// create multiple student object
		System.out.println("Creating a new students ...");
		Student tempStudent1 = new Student("Hédi", "Zaïr", "hedi.marwan@outlook.fr");
		Student tempStudent2 = new Student("Clara", "Oczkowski", "c.oczkowski@outlook.fr");
		Student tempStudent3 = new Student("Tina", "Oczkowski", "tina@outlook.fr");
		// save the student object
		System.out.println("Saving the students ...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

		// display the id of the saved student
		System.out.println(
				"Saved student. Generated ids : " + tempStudent1.getId() + tempStudent2.getId() + tempStudent3.getId());
	}

	private void readStudent(StudentDAO studentDAO) {
		// create a student object
		System.out.println("Creating the new student object ...");
		Student tempStudent = new Student("Peter", "Parker", "perter@outlook.com");

		// save the student
		System.out.println("Saving the new student ...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		Integer tempId = tempStudent.getId();
		System.out.println("Id of the created student : " + tempId);

		// retrieve the sudent based on the id
		System.out.println("Retrieving the student with the id : " + tempId);
		Student myStudent = studentDAO.findById(tempId);
		// display the student

		System.out.println("Found the student : " + myStudent.toString());

	}

	private void queryForStudent(StudentDAO studentDao) {
		System.out.println("Retrieving stundents");
		List<Student> studentList = studentDao.findAll();
		System.out.println("Students found : ");
		for (Student student : studentList) {
			System.out.println(student.toString());
		}
	}

	private void queryForStudentByLastName(StudentDAO studentDao) {
		System.out.println("Retrieving stundents by last name : Parker");
		List<Student> studentList = studentDao.findByLastName("Oczkowski");
		System.out.println("Students found : ");
		for (Student student : studentList) {
			System.out.println(student.toString());
		}
	}

	private void updateStudent(StudentDAO studentDao) {
		Integer stundentId = 1;

		System.out.println("Getting the student with id : " + stundentId);
		Student student = studentDao.findById(1);

		System.out.println("Updating student " + stundentId);
		student.setFirstName("Testing!");
		studentDao.update(student);

		System.out.println("Updated student: " + student.toString());

	}

	private void deleteStudent(StudentDAO studentDao) {
		Integer stundentId = 3000;

		System.out.println("Deleting the student with id : " + stundentId);
		studentDao.delete(stundentId);
	}

	private void deleteAllStudents(StudentDAO studentDao) {

		System.out.println("Deleting all students ");
		Integer nbLineDeleted = studentDao.deleteAll();
		System.out.println("Nb students deleted: " + nbLineDeleted);
	}

}
