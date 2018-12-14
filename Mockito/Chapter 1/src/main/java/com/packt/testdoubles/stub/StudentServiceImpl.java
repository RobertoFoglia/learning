package com.packt.testdoubles.stub;

import java.sql.SQLException;

import com.packt.testdoubles.dummy.Student;

public class StudentServiceImpl implements StudentService {
	private final StudentDao studentDAO;

	public StudentServiceImpl(StudentDao studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	public CreateStudentResponse create(String name, String studentOfclass) {
		CreateStudentResponse response = null;
		try{
			String roleNum= studentDAO.create(name, studentOfclass);
			response = new CreateStudentResponse(null, new Student(roleNum, name));
		}catch(SQLException e){
			response = new CreateStudentResponse(e.getMessage(), null);
		}catch (Exception e) {
			response = new CreateStudentResponse(e.getMessage(), null);
		}
		return response;
	}

}
