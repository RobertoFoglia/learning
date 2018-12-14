package com.packt.testdoubles.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentServiceMockObject {
	private Map<String, List<MethodInvocation>> invocationMap = new HashMap<String, List<MethodInvocation>>();

	void registerCall(MethodInvocation invocation) {
		List<MethodInvocation> list = invocationMap.get(invocation.getMethod());
		if (list == null) {
			list = new ArrayList<>();
		}
		if (!list.contains(invocation)) {
			list.add(invocation);
		}
		
		invocationMap.put(invocation.getMethod(), list);
	}
	
	public void verify(String methodName, int numberOfInvocation){
		int actual = invocation(methodName);
		if(actual != numberOfInvocation){
			throw new IllegalStateException(methodName+" was expected ["+numberOfInvocation+"] times but actaully invoked["+actual+"] times");
		}
	}
	public int invocation(String methodName){
		List<MethodInvocation> list = invocationMap.get(methodName);
		if(list == null){
			return 0;
		}
		
		return list.size();
	}
	
	public MethodInvocation arguments(String methodName, int invocationIndex){
		List<MethodInvocation> list = invocationMap.get(methodName);
		if(list == null || (invocationIndex > list.size())){
			return null;
		}
		
		return list.get(invocationIndex-1);
	}
	
}

class MethodInvocation {

	private List<Object> params = new ArrayList<>();
	private Object returnedValue = null;
	private String method;

	public List<Object> getParams() {
		return params;
	}

	public MethodInvocation addParam(Object parm){
		getParams().add(parm);
		return this;
	}
	
	public Object getReturnedValue() {
		return returnedValue;
	}

	public MethodInvocation setReturnedValue(Object returnedValue) {
		this.returnedValue = returnedValue;
		return this;
	}

	public String getMethod() {
		return method;
	}

	public MethodInvocation setMethod(String method) {
		this.method = method;
		return this;
	}
}
