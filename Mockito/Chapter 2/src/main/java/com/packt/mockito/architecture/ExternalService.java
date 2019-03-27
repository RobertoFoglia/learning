package com.packt.mockito.architecture;

public interface ExternalService {

	public String concat(String arg1, String arg2);
	
	public void someStrangeOperation(Object obj);
	
	public int divide(int a, int b);
}

