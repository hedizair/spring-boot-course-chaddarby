package com.hzair.springboot.cruddemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hzair.springboot.cruddemo.dao.AppDao;
import com.hzair.springboot.cruddemo.entity.Course;
import com.hzair.springboot.cruddemo.entity.Instructor;
import com.hzair.springboot.cruddemo.entity.InstructorDetail;
import com.hzair.springboot.cruddemo.entity.Review;
import com.hzair.springboot.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	// * Executed after the spring beans has been loaded
	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao) { // * AutoInjected
		return runner -> {
			System.out.println("Hello world");
			// createInstructor(appDao);
			// findInstructor(appDao);
			// deleteInstructor(appDao);
			// findInstructorDetail(appDao);
			// deleteInstructorDetail(appDao);
			// createInstructorWithCourses(appDao);
			// findCoursesForInstructor(appDao);
			// findInstructorWithCoursesJoinFetch(appDao);
			// updateInstructor(appDao);
			// updateCourse(appDao);
			// deleteCourse(appDao);
			// createCourseAndReview(appDao);
			// findCourseAndReviews(appDao);
			// deleteCourseAndReviews(appDao);
			// createCourseAndStudents(appDao);
			// findCourseAndStuents(appDao);
			// findStudentAndCourses(appDao);
			// addMoreCoursesForStudent(appDao);
			// deleteCourse(appDao);
			deleteStudent(appDao);
		};
	}

	private void createInstructor(AppDao appDao) {

		Instructor instructor1 = new Instructor("Chad", "Darby",
				"darby@luv2code.com");

		InstructorDetail instructorDetail1 = new InstructorDetail("https://youtube.com/...", "Fooooot");

		instructor1.setInstructorDetail(instructorDetail1);

		appDao.save(instructor1);

		Instructor instructor2 = new Instructor("Madhu", "Patel", "madhu@luv2code.com");

		InstructorDetail instructorDetail2 = new InstructorDetail("https://youtube.com/...", "Guitar");

		instructor2.setInstructorDetail(instructorDetail2);

		System.out.println("Saving the instructors");

		// * This will ALSO save the InstructorDetail BECAUSE OF the CascadeType.ALL
		appDao.save(instructor2);

		instructor2.toString();

	}

	private void findInstructor(AppDao appDao) {

		int theid = 2;
		System.out.println("Finding the instructor id: " + theid);

		Instructor tempInstructor = appDao.findInstructorById(theid);

		System.out.println("The instructor: " + tempInstructor);
		System.out.println("The asspcoate instructorDetail only: " + tempInstructor.getInstructorDetail());
	}

	private void deleteInstructor(AppDao appDao) {
		int theId = 1;

		System.out.println("Deleting the instructor id: " + theId);

		appDao.deleteInstructorById(theId);

	}

	private void findInstructorDetail(AppDao appDao) {

		int theid = 2;
		System.out.println("Finding the instructor detail id: " + theid);

		InstructorDetail tempInstructorDetail = appDao.findInstructorDetailById(theid);

		System.out.println("The instructor detail: " + tempInstructorDetail);
		System.out.println("The associate instructor only: " + tempInstructorDetail.getInstructor());
	}

	private void deleteInstructorDetail(AppDao appDao) {

		int theId = 4;
		System.out.println("Deleting the instructor detail id: " + theId);

		appDao.deleteInstructorDetailById(theId);

	}

	private void createInstructorWithCourses(AppDao appDao) {

		Instructor instructor1 = new Instructor("Susan", "Public",
				"susan@luv2code.com");

		InstructorDetail instructorDetail1 = new InstructorDetail("https://youtube.com/...", "Volleeyyy");
		instructor1.setInstructorDetail(instructorDetail1);

		Course course1 = new Course("JavaScript");
		instructor1.addCourse(course1);
		Course course2 = new Course("Golang");
		instructor1.addCourse(course2);
		Course course3 = new Course("Java");
		instructor1.addCourse(course3);

		System.out.println("Saving the instructor" + instructor1);
		System.out.println("The courses : " + instructor1.getCourses());

		appDao.save(instructor1);

		System.out.println("Done");

	}

	private void findCoursesForInstructor(AppDao appDao) {
		int theId = 1;

		System.out.println("Finding the courses by instructor id: " + theId);

		List<Course> courses = appDao.findCoursesByInstructorId(theId);

		System.out.println("Courses: " + courses);

	}

	private void findInstructorWithCoursesJoinFetch(AppDao appDao) {
		int theId = 1;

		System.out.println("Finding the instructor by instructor id: " + theId);

		Instructor instructor = appDao.findInstructorByIdJoinFetch(theId);

		System.out.println("Instructor: " + instructor);
		System.out.println("His courses: " + instructor.getCourses());

	}

	private void updateInstructor(AppDao appDao) {

		int instructorId = 1;

		System.out.println("Finding instructor id:" + instructorId);

		Instructor instructor = appDao.findInstructorById(instructorId);

		instructor.setLastName("Nasus");
		instructor.setEmail("nasus@luv2code.com");

		System.out.println("Updating: ");

		Instructor updatedInstructor = appDao.update(instructor);

		System.out.println("Instructor updated: " + updatedInstructor);

	}

	private void updateCourse(AppDao appDao) {

		int courseId = 10;

		System.out.println("Finding course id:" + courseId);

		Course course = appDao.findCourseById(courseId);

		course.setTitle("SQL");

		System.out.println("Updating course: ");

		Course updatedCourse = appDao.update(course);

		System.out.println("Course updated: " + updatedCourse);

	}

	private void deleteCourse(AppDao appDao) {
		int theId = 10;

		System.out.println("Deleting the course id: " + theId);

		appDao.deleteCourseById(theId);

		System.out.println("Done");

	}

	private void createCourseAndReview(AppDao appDao) {

		Course course = new Course("Network & System");

		course.addReview(new Review("Not too bad bro"));
		course.addReview(new Review("Really bad ...."));
		course.addReview(new Review("Mid tier"));

		System.out.println("Creating the course...");

		appDao.save(course);

		System.out.println("Course created");

	}

	private void findCourseAndReviews(AppDao appDao) {

		int courseId = 10;

		System.out.println("Retreiving course....");

		Course course = appDao.findCourseAndReviewsByCourseId(courseId);

		System.out.println("The course: " + course);

		System.out.println("His reviews: " + course.getReviews());

	}

	private void deleteCourseAndReviews(AppDao appDao) {

		int courseId = 10;

		System.out.println("Deleting course and reviews....");

		appDao.deleteCourseById(courseId);

	}

	private void createCourseAndStudents(AppDao appDao) {

		Course course = new Course("Project management");

		Student student1 = new Student("Peter", "Parker", "peter@luv2code.com");
		Student student2 = new Student("Tony", "Stark", "tony@luv2code.com");

		course.addStudent(student1);
		course.addStudent(student2);

		System.out.println("Saving the course and the students");
		System.out.println("The course: " + course);
		System.out.println("The students: " + course.getStudents());

		appDao.save(course);

		System.out.println("Done");

	}

	private void findCourseAndStuents(AppDao appDao) {

		int courseId = 10;

		System.out.println("Retreiving course and students....");

		Course course = appDao.findCourseAndStudentsByCourseId(courseId);

		System.out.println("The course: " + course);

		System.out.println("His students: " + course.getStudents());

	}

	private void findStudentAndCourses(AppDao appDao) {

		int studentId = 1;

		System.out.println("Retreiving student and courses....");

		Student student = appDao.findStudentAndCoursesByStudentId(studentId);

		System.out.println("The student: " + student);

		System.out.println("His courses: " + student.getCourses());

	}


	private void addMoreCoursesForStudent(AppDao appDao) {

		int studentId = 2;

		System.out.println("Retreiving Student...");

		Student student = appDao.findStudentAndCoursesByStudentId(studentId);

		Course course1 = new Course("Maths");
		Course course2 = new Course("French");

		student.addCourse(course1);
		student.addCourse(course2);

		System.out.println("Updating student with more courses");


		Student updatedStudent = appDao.update(student);

		System.out.println("The student updated: " + updatedStudent);
		System.out.println("His courses: " + updatedStudent.getCourses());

	}

	private void deleteStudent(AppDao appDao) {

		int studentId = 2;

		System.out.println("Deleting a student id: " + studentId);

		appDao.deleteStudentById(studentId);

		System.out.println("Done");
	}

}
