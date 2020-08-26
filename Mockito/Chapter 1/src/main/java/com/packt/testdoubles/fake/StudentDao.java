package com.packt.testdoubles.fake;

import java.util.List;

import com.packt.testdoubles.dummy.Student;

public interface StudentDao {
  public void batchUpdate(List<Student> students);
}
