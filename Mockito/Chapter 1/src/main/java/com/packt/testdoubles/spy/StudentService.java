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
	
	public void enrollToCourse(String courseName, Student student) {
		MethodInvocation invocation = new MethodInvocation();
		invocation.addParam(courseName).addParam(student).setMethod("enrollToCourse");
		spy.registerCall(invocation);
		
		List<Student> list = studentCouseMap.get(courseName);
		if (list == null) {
			list = new ArrayList<>();
		}
		if (!list.contains(student)) {
			list.add(student);
		}
		
		studentCouseMap.put(courseName, list);
	}

}
