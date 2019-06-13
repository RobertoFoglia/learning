package com.packt.mockito.advanced;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
public class InlineStubbingTest {

	Bar globalBar =  when(mock(Bar.class).getTar()).
			thenReturn(new Tar()).getMock();
	
	@Test
	public void when_stubbing_inline() throws Exception {
		assertNotNull(globalBar);
		assertNotNull(globalBar.getTar());
	}

}
