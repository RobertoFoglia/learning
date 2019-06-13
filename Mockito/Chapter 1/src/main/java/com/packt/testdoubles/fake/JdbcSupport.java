package com.packt.testdoubles.fake;

import java.util.List;
import java.util.Map;

public class JdbcSupport {
   public int[] batchUpdate(String sql, List<Map<String, Object>> params){
	   //original db access code go here
	   return null;
   }
}
