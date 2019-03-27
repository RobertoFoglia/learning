package com.packt.mockito.advanced;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mockingDetails;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockDetailsTest {
 @Spy Dependency1 dep1;
 @Mock Dependency1 dep11;
 @Mock Dependency2 dep2;
 
 @InjectMocks
 ServiceImpl service;
 
 @Test
public void when_determing_type() throws Exception {
	assertNotNull(service);
	 assertTrue(mockingDetails(service.getDependency2()).isMock());
	 assertTrue(mockingDetails(dep1).isSpy());
}
 
}

class ServiceImpl{
	private final Dependency1 dependency1;
	private final Dependency2 dependency2;
	public ServiceImpl(Dependency1 dependency1, Dependency2 dependency2) {
		this.dependency1 = dependency1;
		this.dependency2 = dependency2;
	}
	public Dependency1 getDependency1() {
		return dependency1;
	}
	public Dependency2 getDependency2() {
		return dependency2;
	}
	
}

class Dependency1{
	
}
class Dependency2{
	
}