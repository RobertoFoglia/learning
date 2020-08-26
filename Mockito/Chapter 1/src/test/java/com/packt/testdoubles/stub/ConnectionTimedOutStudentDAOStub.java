package com.packt.testdoubles.stub;

import java.sql.SQLException;

public class ConnectionTimedOutStudentDAOStub implements StudentDao {

	public String create(String name, String className) throws SQLException {
		throw new SQLException("DB connection timed out");
	}

}
