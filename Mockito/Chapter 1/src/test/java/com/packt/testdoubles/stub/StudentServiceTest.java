package com.packt.testdoubles.stub;

import static org.junit.Assert.*;

import org.junit.Test;

public class StudentServiceTest {

	StudentService studentService;
	@Test
	public void when_connection_times_out_then_the_student_is_not_saved() {
		studentService = new StudentServiceImpl(new ConnectionTimedOutStudentDAOStub());
		String classNine = "IX";
		String johnSmith = "john Smith";
		CreateStudentResponse resp = studentService.create(johnSmith, classNine);
		assertFalse(resp.isSuccess());
	}

}
