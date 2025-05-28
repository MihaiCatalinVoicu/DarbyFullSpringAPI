package com.dev.cruddemo;

import com.dev.cruddemo.dao.StudentDAO;
import com.dev.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {

			//createStudent(studentDAO);

			createMultipleStudents(studentDAO);

			//readStudent(studentDAO);

			//queryForStudents(studentDAO);

//			queryForStudentsByLastName(studentDAO);

//			updateStudent(studentDAO);

//			deleteStudent(studentDAO);

//			deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {

		System.out.println("Deleting all students.");
		int numberOfRows = studentDAO.deleteAll();
		System.out.println("Deleted " + numberOfRows + " students.");
	}

	private void deleteStudent(StudentDAO studentDAO) {

		int studentId = 3;
		System.out.println("Deleting student with id: " + studentId);
		studentDAO.delete(studentId);

	}

	private void updateStudent(StudentDAO studentDAO) {

		int studentId = 1;
		System.out.println("Getting student with id: " + studentId);

		Student student = studentDAO.findById(studentId);
		System.out.println("Existing student: " + student);

		System.out.println("Updating student...");
		student.setFirstName("darian");

		studentDAO.update(student);
		System.out.println("Updated student: " + student);

	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {

		List<Student> theStudents = studentDAO.findByLastName("fochista");

		for (Student student: theStudents) {
			System.out.println(student);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {

		List<Student> students = studentDAO.findAll();

		for(Student temp: students) {
			System.out.println(temp);
		}
	}

	private void readStudent(StudentDAO studentDAO) {

		System.out.println("Creating new student object...");
		Student student = new Student("cristina", "fochista", "cris.foc@gmail.com");

		System.out.println("Saving the student..");
		studentDAO.save(student);
		System.out.println("Saved student with id: " + student.getId());

		System.out.println("Retreiving student with id: " + student.getId());
		Student student1 = studentDAO.findById(student.getId());
		System.out.println("Fond student: " + student1);


	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		System.out.println("Creating 3 students objects...");
		Student student1 = new Student("mihai", "ilie", "mihai.ilie@yaho.com");
		Student student2 = new Student("ana", "nicu", "ana.nicu@yaho.com");
		Student student3 = new Student("cornel", "stanciu", "cornel.stanciu@yaho.com");

		System.out.println("Svaing the students ...");
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);
	}

	private void createStudent(StudentDAO studentDAO) {

		Student student1 = new Student("marcel", "laie", "marcel.laie@yaho.com");

		System.out.println("Saving the student...");
		studentDAO.save(student1);
		System.out.println("Saved student with id: " + student1.getId());
	}

}
