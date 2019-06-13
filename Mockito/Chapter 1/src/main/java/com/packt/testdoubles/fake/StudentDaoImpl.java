package com.packt.testdoubles.fake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.packt.testdoubles.dummy.Student;

public class StudentDaoImpl implements StudentDao {

	public StudentDaoImpl() {
	}

	@Override
	public void batchUpdate(List<Student> students) {
		
		List<Student> insertList = new ArrayList<>();
		List<Student> updateList = new ArrayList<>();

		for (Student student : students) {
			if (student.getRoleNumber() == null) {
				insertList.add(student);
			} else {
				updateList.add(student);
			}
		}

		int rowsInserted = 0;
		int rowsUpdated = 0;
		if (!insertList.isEmpty()) {
			List<Map<String, Object>> paramList = new ArrayList<>();
			for (Student std : insertList) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("name", std.getName());
				paramList.add(param);
			}
			int[] rowCount = update("insert", paramList);
			rowsInserted = sum(rowCount);
		}

		if (!updateList.isEmpty()) {
			List<Map<String, Object>> paramList = new ArrayList<>();
			for (Student std : updateList) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("roleId", std.getRoleNumber());
				param.put("name", std.getName());
				paramList.add(param);
			}
			int[] rowCount = update("update", paramList);
			rowsUpdated = sum(rowCount);
		}

		if (students.size() != (rowsInserted + rowsUpdated)) {
			throw new IllegalStateException("Database update error, expected "
					+ students.size() + " updates but actual "
					+ (rowsInserted + rowsUpdated));
		}

	}

	int[] update(String sql, List<Map<String, Object>> params) {
		return new JdbcSupport().batchUpdate(sql, params);
	}

	private int sum(int[] rows) {
		int sum = 0;
		for (int val : rows) {
			sum += val;
		}
		return sum;
	}
}
