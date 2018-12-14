package com.packt.testdoubles.spy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.packt.testdoubles.dummy.Student;

public class StudentService {

	private HashMap<String, List<Student>> studentCouseMap = new HashMap<>();
	
	private StudentServiceSpy spy;

	public void setSpy(StudentServiceSpy spy) {
		this.spy = spy;
	}

	/**
	 *
	 * if no student is enrolled,
	 * then it creates a collection of students, adds the student to the collection, and
	 * puts the collection back in the map. If a student has previously enrolled for
	 * the course, then the map already contains a Student collection
	 *
	 * @param courseName
	 * @param student
	 */
	public void enrollToCourse(String courseName, Student student) {

		/************************************************
		 * Code for spy
		 */
		MethodInvocation invocation = new MethodInvocation();
		invocation.addParam(courseName).addParam(student).setMethod("enrollToCourse");
		spy.registerCall(invocation);
		/**
		 *
		 ************************************************/
		
		List<Student> list = studentCouseMap.get(courseName);
		if (list == null) {
			list = new ArrayList<>();
			studentCouseMap.put(courseName, list);
		}
		if (!list.contains(student)) {
			list.add(student);
		}
	}

}
