package com.packt.testdoubles.stub;

import com.packt.testdoubles.dummy.Student;

public interface StudentService {
	CreateStudentResponse create(String name, String studentOfclass);
}
