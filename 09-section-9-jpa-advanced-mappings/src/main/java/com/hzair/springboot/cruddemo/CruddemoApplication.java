package com.hzair.springboot.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hzair.springboot.cruddemo.dao.AppDao;
import com.hzair.springboot.cruddemo.entity.Instructor;
import com.hzair.springboot.cruddemo.entity.InstructorDetail;

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
			findInstructorDetail(appDao);

		};
	}

	private void createInstructor(AppDao appDao) {

		// Instructor instructor = new Instructor("Chad", "Darby",
		// "darby@luv2code.com");

		// InstructorDetail instructorDetail = new
		// InstructorDetail("https://youtube.com/...", "Fooooot");

		// instructor.setInstructorDetail(instructorDetail);

		Instructor instructor = new Instructor("Madhu", "Patel", "madhu@luv2code.com");

		InstructorDetail instructorDetail = new InstructorDetail("https://youtube.com/...", "Guitar");

		instructor.setInstructorDetail(instructorDetail);

		System.out.println("Saving the instructor");

		// * This will ALSO save the InstructorDetail BECAUSE OF the CascadeType.ALL
		appDao.save(instructor);

		instructor.toString();

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

}
