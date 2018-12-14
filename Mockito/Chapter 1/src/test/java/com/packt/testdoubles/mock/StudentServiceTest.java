package com.packt.testdoubles.mock;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.packt.testdoubles.dummy.Student;

public class StudentServiceTest {
	StudentService service = new StudentService();
	StudentServiceMockObject mockObject = new StudentServiceMockObject();

	@Test
	public void enrolls_students() throws Exception {
		//create a student
		Student bob = new Student("001", "Robert Anthony");
		Student roy = new Student("002", "Roy Noon");
		//set spy
		service.setMock(mockObject);

		//invoke method
		service.enrollToCourse("english", bob);
		service.enrollToCourse("history", roy);
		
		//assert that the method was invoked twice
		assertEquals(2, mockObject.invocation("enrollToCourse"));
		
		//verify
		mockObject.verify("enrollToCourse", 2);

	}

}
